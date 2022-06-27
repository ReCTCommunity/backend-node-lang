package nodes;

public class Node {
    private final int nodeId;
    private final NodePin[] pins;

    public Node(final int nodeId, final NodePin[] pins) {
        this.nodeId = nodeId;
        this.pins = pins;
    }

    public NodePin[] getPins() {
        return pins;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getName() {
        return "Node " + (char) (64 + nodeId);
    }
}
