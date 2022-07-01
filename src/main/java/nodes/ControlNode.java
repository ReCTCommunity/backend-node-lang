package nodes;

public abstract class ControlNode extends Node {
    private final NodePin[] executionPins = new NodePin[1];

    public ControlNode() {
        executionPins[0] = new NodePin(this, "in", false, true);
    }

    @Override
    public NodePin[] getExecutionPins() {
        return executionPins;
    }

    @Override
    public NodeType getType() {
        return NodeType.Control;
    }
}
