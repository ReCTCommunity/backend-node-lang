package nodes;

import types.NodeType;

public abstract class DevNode extends Node {
    private final NodePin[] executionPins = new NodePin[1];
    public DevNode() {
        executionPins[0] = new NodePin(this, "in", false, true);
    }
    @Override
    public NodePin[] getExecutionPins() {
        return executionPins;
    }

    @Override
    public NodeType getType() {
        return NodeType.Dev;
    }
}
