package nodes;

public abstract class OperationNode extends Node{
    private final NodePin[] executionPins = new NodePin[2];

    public OperationNode()
    {
        executionPins[0] = new NodePin("in", false, true);
        executionPins[1] = new NodePin("out", true, true);
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
