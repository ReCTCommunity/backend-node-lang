package nodes;

public abstract class OperationNode extends Node{
    private final NodePin[] executionPins = new NodePin[2];

    public OperationNode()
    {
        executionPins[0] = new NodePin(this, "in", false, true);
        executionPins[1] = new NodePin(this, "out", true, true);
    }
    @Override
    public NodePin[] getExecutionPins() {
        return executionPins;
    }

    @Override
    public NodeType getType() {
        return NodeType.Operation;
    }
}
