package nodes;

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

    public abstract String getName();
}
