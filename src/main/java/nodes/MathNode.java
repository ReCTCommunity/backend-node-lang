package nodes;

public abstract class MathNode extends Node {
    @Override
    public NodePin[] getExecutionPins() {
        return new NodePin[0];
    }

    @Override
    public NodeType getType() {
        return NodeType.Math;
    }
}
