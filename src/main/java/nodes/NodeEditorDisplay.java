package nodes;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.extension.imnodes.ImNodes;
import imgui.extension.imnodes.ImNodesContext;
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation;
import imgui.extension.imnodes.flag.ImNodesPinShape;
import imgui.extension.nodeditor.NodeEditor;
import imgui.extension.nodeditor.NodeEditorConfig;
import imgui.extension.nodeditor.NodeEditorContext;
import imgui.extension.nodeditor.flag.NodeEditorPinKind;
import imgui.flag.ImGuiMouseButton;
import imgui.type.ImInt;
import languageNodes.TestNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeEditorDisplay {
    private static final ImNodesContext CONTEXT = new ImNodesContext();
    static {
        ImNodes.createContext();
    }

    private final Map<Integer, Node> nodes = new HashMap<>();
    private final ArrayList<NodeLink> nodeLinks = new ArrayList<>();

    public Node createGraphNode() {
        TestNode node = new TestNode();
        this.nodes.put(node.getNodeId(), node);
        return node;
    }

    public void drawEditor(){
        ImNodes.editorContextSet(CONTEXT);
        ImNodes.beginNodeEditor();
        for (Node node : nodes.values()) {
            ImNodes.beginNode(node.getNodeId());

            ImNodes.beginNodeTitleBar();
            ImGui.text(node.getName());
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

        }

        for(NodeLink l : nodeLinks){
            ImNodes.link(l.getId(), l.getStart(), l.getEnd());
        }

        final boolean isEditorHovered = ImNodes.isEditorHovered();

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.BottomRight);

        ImNodes.endNodeEditor();

        ImInt start_attr = new ImInt();
        ImInt end_attr = new ImInt();
        if (ImNodes.isLinkCreated(start_attr, end_attr))
        {
            NodeLink link = new NodeLink(start_attr.get(), end_attr.get());
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
            if (ImGui.button("Create New Node")) {
                final Node node = createGraphNode();
                ImNodes.setNodeScreenSpacePos(node.getNodeId(), ImGui.getMousePosX(), ImGui.getMousePosY());
                ImGui.closeCurrentPopup();
            }
            ImGui.endPopup();
        }
    }
}
