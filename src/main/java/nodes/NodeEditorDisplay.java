package nodes;

import imgui.ImColor;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.extension.imnodes.ImNodes;
import imgui.extension.imnodes.ImNodesContext;
import imgui.extension.imnodes.flag.ImNodesColorStyle;
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation;
import imgui.extension.imnodes.flag.ImNodesPinShape;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiMouseButton;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import imgui.type.ImString;
import languageNodes.*;
import languageNodes.controlNodes.ForNode;
import languageNodes.controlNodes.IfNode;
import languageNodes.controlNodes.SequenceNode;
import languageNodes.controlNodes.WhileNode;
import languageNodes.devNodes.PrintNode;
import languageNodes.mathNodes.*;
import types.DataType;
import types.NodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        put("Add Int", AddIntNode.class);
        put("Subtract Int", SubIntNode.class);
        put("Multiply Int", MulIntNode.class);
        put("Divide Int", DivIntNode.class);

        put("Cast Any to Type", CastAnyToTypeNode.class);
        put("Type Literal", TypeLiteralNode.class);

        put("Get Collection Length", GetCollectionLenghNode.class);
        put("Get Collection Item", GetCollectionItemNode.class);
        put("Get Dictionary Item", GetDictionaryItemNode.class);
        put("Get Dictionary Keys", GetDictionaryKeysNode.class);
        put("Get Dictionary Values", GetDictionaryValuesNode.class);

        put("Equals", EqualsNode.class);

        put("Logical And", LogicalAndNode.class);
        put("Logical Or", LogicalOrNode.class);
        put("Logical Not", LogicalNotNode.class);

        put("If", IfNode.class);
        put("Sequence", SequenceNode.class);
        put("While Loop", WhileNode.class);
        put("For Loop", ForNode.class);

        put("String Concat", ConcatNode.class);
        put("To String", ToStringNode.class);

        put("Debug Print", PrintNode.class);
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

            // set the title text
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

            // for value nodes: draw dropdown (or text box but that's not implemented yet)
            if (node instanceof ValueNode) {
                ValueNode v = (ValueNode)node;

                ImGui.pushItemWidth(150);

                ImInt selection = new ImInt(v.getCurrentValue());
                if(ImGui.combo("", selection, v.getDropdownValues())) {
                    v.OnValueChanged(selection.get());
                }

                ImGui.popItemWidth();
            }

            for (NodePin pin : node.getPins()) {
                // set pin color
                if (pin.isExecution())
                    ImNodes.pushColorStyle(ImNodesColorStyle.Pin , ImColor.floatToColor(1,1,1));
                else
                    ImNodes.pushColorStyle(ImNodesColorStyle.Pin, pin.getDatatype().PinColor);

                if (pin.isOutput()) {
                    // set pin shape
                    if (pin.isExecution())
                        ImNodes.beginOutputAttribute(pin.getId(), ImNodesPinShape.Triangle);
                    else
                        ImNodes.beginOutputAttribute(pin.getId(), ImNodesPinShape.CircleFilled);

                    ImVec2 size = new ImVec2();
                    ImGui.calcTextSize(size, pin.getName());
                    ImGui.indent(128);
                    ImGui.textUnformatted(pin.getName());
                    ImNodes.endOutputAttribute();
                } else {
                    // set pin shape
                    if (pin.isExecution())
                        ImNodes.beginInputAttribute(pin.getId(), ImNodesPinShape.Triangle);
                    else
                        ImNodes.beginInputAttribute(pin.getId(), ImNodesPinShape.CircleFilled);

                    ImGui.textUnformatted(pin.getName());
                    ImNodes.endInputAttribute();

                    if (!pin.isExecution() && !pin.Connected) {
                        PinInputField(pin);
                    }
                }
                ImNodes.popColorStyle();
            }

            ImNodes.endNode();

            // unload this node's color changes
            for (int i = 0; i < colorChanges; i++) {
                ImNodes.popColorStyle();
            }

        }

        for(NodeLink l : nodeLinks){
            if (l.isExecution())
                ImNodes.pushColorStyle(ImNodesColorStyle.Link, ImColor.floatToColor(1,1,1));
            else {
                ImNodes.pushColorStyle(ImNodesColorStyle.Link, l.getDatatype().PinColor);
            }
            ImNodes.link(l.getId(), l.getStart(), l.getEnd());
            ImNodes.popColorStyle();
        }

        final boolean isEditorHovered = ImNodes.isEditorHovered();

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.BottomRight);

        ImNodes.endNodeEditor();

        ImInt start_attr = new ImInt();
        ImInt end_attr = new ImInt();
        if (ImNodes.isLinkCreated(start_attr, end_attr))
        {
            NodePin startPin = NodePin.AllPins.get(start_attr.get());
            NodePin endPin = NodePin.AllPins.get(end_attr.get());

            // mmmmm cant if-guard in an if statment aaaaaaaaaaaaa, welp time to stack ifs
            // -Red

            if ((startPin.isExecution() && endPin.isExecution()) ||
                (startPin.getDatatype() == endPin.getDatatype()) ||
                (!startPin.isExecution() && endPin.getDatatype() == DataType.Any))
            {
                NodeLink link;// = new NodeLink(start_attr.get(), end_attr.get(), );

                if (startPin.isExecution())
                    link = new NodeLink(start_attr.get(), end_attr.get(), true);
                else
                    link = new NodeLink(start_attr.get(), end_attr.get(), startPin.getDatatype());

                startPin.Connected = true;
                endPin.Connected = true;

                nodeLinks.add(link);
                startPin.getNode().OnOutputHookup(link);
                endPin.getNode().OnInputHookup(link);
            }
        }

        ImInt link_id = new ImInt();
        if (ImNodes.isLinkDestroyed(link_id))
        {
            System.out.println("Removing link " + link_id);
            for(var l : nodeLinks){
                if(l.getId() == link_id.get()) {
                    RemoveLink(l);
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
                for(var l : nodeLinks) {
                    if(l.getId() == hoveredLink) {
                        RemoveLink(l);
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
                    Node nodeToDelete = nodes.get(targetNode);
                    nodes.remove(targetNode);

                    // look through all Links if one needs to be removed from this node
                    // (this deletion is done in two steps so java won't cry that I modified the collection while looping)
                    List<NodeLink> toRemove = new ArrayList<>();
                    for (NodeLink l : nodeLinks) {
                        if (NodePin.AllPins.get(l.getStart()).getNode().getNodeId() == targetNode ||
                            NodePin.AllPins.get(l.getEnd()).getNode().getNodeId() == targetNode)
                            toRemove.add(l);
                    }

                    for (NodeLink l : toRemove) {
                        RemoveLink(l);
                    }

                    // remove the node's pins from the global pin storage
                    for (NodePin p : nodeToDelete.getPins()) {
                        NodePin.AllPins.remove(p);
                    }

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

    void PinInputField(NodePin pin)
    {
        if (!pin.getDatatype().CanBeLiteral) return;

        ImGui.sameLine();
        ImGui.pushItemWidth(150);
        ImGui.pushStyleColor(ImGuiCol.FrameBg, ImColor.rgbToColor("#575757"));
        ImGui.pushStyleColor(ImGuiCol.Button, ImColor.rgbToColor("#575757"));

        if (pin.getDatatype() == DataType.Bool) {
            ImBoolean v = new ImBoolean((boolean)pin.LiteralValue);
            if (ImGui.checkbox("##" + pin.getId(), v)) {
                pin.LiteralValue = v.get();
            }
        }
        else if (pin.getDatatype() == DataType.Int) {
            ImInt v = new ImInt((int)pin.LiteralValue);
            if (ImGui.inputInt("##" + pin.getId(), v)) {
                pin.LiteralValue = v.get();
            }
        }
        else if (pin.getDatatype() == DataType.Float) {
            ImFloat v = new ImFloat((float)pin.LiteralValue);
            if (ImGui.inputFloat("##" + pin.getId(), v)) {
                pin.LiteralValue = v.get();
            }
        }
        else if (pin.getDatatype() == DataType.String) {
            ImString v = new ImString();
            v.set(pin.LiteralValue);
            if (ImGui.inputText("##" + pin.getId(), v)) {
                pin.LiteralValue = v.get();
            }
        }

        ImGui.popStyleColor();
        ImGui.popStyleColor();
        ImGui.popItemWidth();
    }

    void RemoveLink(NodeLink l) {
        nodeLinks.remove(l);

        NodePin startPin = NodePin.AllPins.get(l.getStart());
        NodePin endPin = NodePin.AllPins.get(l.getEnd());

        startPin.Connected = false;
        endPin.Connected = false;

        startPin.getNode().OnOutputRemoval(l);
        endPin.getNode().OnInputRemoval(l);

        System.out.println("Removed link " + l.getId());
    }
}
