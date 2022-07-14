package nodes;

import types.NodeType;

public abstract class Node {
    private final int nodeId;

    private static int nextNodeID = 1;

    public Node() {
        nodeId = nextNodeID++;
    }

    public int getNodeId() {
        return nodeId;
    }

    public abstract NodePin[] getPins();

    public abstract NodePin[] getExecutionPins();

    public abstract String getName();

    public abstract NodeType getType();

    public void OnInputHookup(NodeLink link) {}
    public void OnOutputHookup(NodeLink link) {}
    public void OnInputRemoval(NodeLink link) {}
    public void OnOutputRemoval(NodeLink link) {}
}
