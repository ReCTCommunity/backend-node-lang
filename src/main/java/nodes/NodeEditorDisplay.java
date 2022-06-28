package nodes;

import imgui.ImColor;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.app.Color;
import imgui.extension.imnodes.ImNodes;
import imgui.extension.imnodes.ImNodesContext;
import imgui.extension.imnodes.flag.ImNodesColorStyle;
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation;
import imgui.extension.imnodes.flag.ImNodesPinShape;
import imgui.extension.imnodes.flag.ImNodesStyleVar;
import imgui.extension.nodeditor.NodeEditor;
import imgui.extension.nodeditor.NodeEditorConfig;
import imgui.extension.nodeditor.NodeEditorContext;
import imgui.extension.nodeditor.flag.NodeEditorPinKind;
import imgui.flag.ImGuiMouseButton;
import imgui.type.ImInt;
import languageNodes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class NodeEditorDisplay {
    private static final ImNodesContext CONTEXT = new ImNodesContext();
    static {
        ImNodes.createContext();
    }

    private final Map<Integer, Node> nodes = new HashMap<>();
    private final ArrayList<NodeLink> nodeLinks = new ArrayList<>();
    private final NodeFactory nodeFactory = new NodeFactory();
    private final Map<String, Class<?>> existingNodes = new HashMap<String, Class<?>>(){{
        put("Begin Event", BeginEventNode.class);
        put("Request Event", RequestEventNode.class);
        put("Set Variable", SetVariableNode.class);
        put("Change Variable", ChangeVariableNode.class);
    }};

    public Node createGraphNode(Class<?> type) {
        Node node = nodeFactory.getNode(type);
        this.nodes.put(node.getNodeId(), node);
        return node;
    }

    public void drawEditor(){
        ImNodes.editorContextSet(CONTEXT);
        ImNodes.beginNodeEditor();
        for (Node node : nodes.values()) {
            // get the NodeType of the current node, this type stores color info, base connections, etc...
            NodeType thisNode = node.getType();

            // load node colors
            int colorChanges = 3;
            ImNodes.pushColorStyle(ImNodesColorStyle.TitleBar, thisNode.HeadColor);
            ImNodes.pushColorStyle(ImNodesColorStyle.TitleBarSelected, thisNode.SelectedColor);
            ImNodes.pushColorStyle(ImNodesColorStyle.TitleBarHovered, thisNode.SelectedColor);

            // start building this node
            ImNodes.beginNode(node.getNodeId());

            // start building the node title bar
            ImNodes.beginNodeTitleBar();

            // set the title textf
            ImGui.text(node.getName());

            // generate the node's execution pins (the cool triangle ones)
            // -----------------------------------------------------------

            // set the pin color to white
            ImNodes.pushColorStyle(ImNodesColorStyle.Pin, ImColor.floatToColor(1,1,1));

            // generate the pins themselves
            NodePin[] executionPins = node.getExecutionPins();
            for (int i = 0; i < executionPins.length; i++)
            {
                NodePin p = executionPins[i];

                if (p.isOutput()) {
                    ImNodes.beginOutputAttribute(p.getId(), ImNodesPinShape.Triangle);
                    ImNodes.endInputAttribute();
                } else {
                    ImNodes.beginInputAttribute(p.getId(), ImNodesPinShape.Triangle);
                    ImNodes.endInputAttribute();
                }

                if (i < executionPins.length - 1)
                    ImGui.sameLine();
            }

            // reset the pin color
            ImNodes.popColorStyle();


            ImNodes.endNodeTitleBar();

            ImGui.newLine();

            for (NodePin pin : node.getPins()) {
                if (pin.isOutput()) {
                    ImNodes.beginOutputAttribute(pin.getId());
                    ImVec2 size = new ImVec2();
                    ImGui.calcTextSize(size, pin.getName());
                    ImGui.indent(128);
                    ImGui.textUnformatted(pin.getName());
                    ImNodes.endOutputAttribute();
                } else {
                    ImNodes.beginInputAttribute(pin.getId(), ImNodesPinShape.CircleFilled);
                    ImGui.textUnformatted(pin.getName());
                    ImNodes.endInputAttribute();
                }

            }

            ImNodes.endNode();

            // unload this node's color changes
            for (int i = 0; i < colorChanges; i++) {
                ImNodes.popColorStyle();
            }

        }

        for(NodeLink l : nodeLinks){
            if (l.isExecution()) ImNodes.pushColorStyle(ImNodesColorStyle.Link, ImColor.floatToColor(1,1,1));
            ImNodes.link(l.getId(), l.getStart(), l.getEnd());
            if (l.isExecution()) ImNodes.popColorStyle();
        }

        final boolean isEditorHovered = ImNodes.isEditorHovered();

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.BottomRight);

        ImNodes.endNodeEditor();

        ImInt start_attr = new ImInt();
        ImInt end_attr = new ImInt();
        if (ImNodes.isLinkCreated(start_attr, end_attr))
        {
            NodeLink link = new NodeLink(start_attr.get(), end_attr.get(), NodePin.AllPins.get(start_attr.get()).isExecution());
            nodeLinks.add(link);
        }

        ImInt link_id = new ImInt();
        if (ImNodes.isLinkDestroyed(link_id))
        {
            System.out.println("Removing link " + link_id);
            for(var l : nodeLinks){
                if(l.getId() == link_id.get()) {
                    nodeLinks.remove(l);
                    System.out.println("Removed link " + link_id);
                    break;
                }

            }
        }

        if (ImGui.isMouseClicked(ImGuiMouseButton.Right)) {
            final int hoveredNode = ImNodes.getHoveredNode();
            final int hoveredLink = ImNodes.getHoveredLink();
            if (hoveredNode != -1) {
                ImGui.openPopup("node_context");
                ImGui.getStateStorage().setInt(ImGui.getID("delete_node_id"), hoveredNode);
            } else if(hoveredLink != -1) {
                for(var l : nodeLinks){
                    if(l.getId() == hoveredLink) {
                        nodeLinks.remove(l);
                        System.out.println("Removed link " + hoveredLink);
                        break;
                    }

                }
            }
            else if (isEditorHovered) {
                ImGui.openPopup("node_editor_context");
            }
        }

        if (ImGui.isPopupOpen("node_context")) {
            final int targetNode = ImGui.getStateStorage().getInt(ImGui.getID("delete_node_id"));
            if (ImGui.beginPopup("node_context")) {
                if (ImGui.button("Delete " + nodes.get(targetNode).getName())) {
                    nodes.remove(targetNode);
                    ImGui.closeCurrentPopup();
                }
                ImGui.endPopup();
            }
        }

        if (ImGui.beginPopup("node_editor_context")) {
            /*if (ImGui.button("Create New Node")) {
                final Node node = createGraphNode();
                ImNodes.setNodeScreenSpacePos(node.getNodeId(), ImGui.getMousePosX(), ImGui.getMousePosY());
                ImGui.closeCurrentPopup();
            }*/

            ImInt selectedItem = new ImInt();

            Set<String> keys = existingNodes.keySet();
            String[] keysArray = keys.toArray(new String[keys.size()]);

            ImGui.text("New Node");

            if(ImGui.combo(" ", selectedItem, keysArray))
            {
                final Node node = createGraphNode(existingNodes.get(keysArray[selectedItem.get()]));
                ImNodes.setNodeScreenSpacePos(node.getNodeId(), ImGui.getMousePosX(), ImGui.getMousePosY());
                ImGui.closeCurrentPopup();
            }

            ImGui.endPopup();
        }
    }
}
