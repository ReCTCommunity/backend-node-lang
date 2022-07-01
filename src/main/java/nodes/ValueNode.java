package nodes;

public abstract class ValueNode extends Node{
    @Override
    public NodePin[] getExecutionPins() {
        return new NodePin[0];
    }

    @Override
    public NodeType getType() {
        return NodeType.Value;
    }

    public abstract ValueType getValueType();
    public String[] getDropdownValues() { return new String[0]; }
    public int getCurrentValue() { return 0; }
    public void OnValueChanged(Object value) { }
}
