package languageNodes;

import nodes.Datatype;
import nodes.NodePin;
import nodes.ValueNode;
import nodes.ValueType;

public class TypeLiteralNode extends ValueNode {
    private final NodePin[] pins = new NodePin[1];
    private final String[] literalOptions;
    private int currentSelection;
    public TypeLiteralNode()
    {
        pins[0] = new NodePin(this, "Type", true, Datatype.values()[0]);
        literalOptions = new String[Datatype.values().length];
        for (int i = 0; i < Datatype.values().length; i++) {
            literalOptions[i] = Datatype.values()[i].toString();
        }
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Type Literal";
    }

    @Override
    public ValueType getValueType() {
        return ValueType.Dropdown;
    }

    @Override
    public String[] getDropdownValues() {
        return literalOptions;
    }

    @Override
    public int getCurrentValue() {
        return currentSelection;
    }

    @Override
    public void OnValueChanged(Object value) {
        pins[0].setDatatype(Datatype.values()[(Integer)value]);
        currentSelection = (Integer)value;
    }
}
