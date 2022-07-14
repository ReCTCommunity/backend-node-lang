package languageNodes.mathNodes;

import types.DataType;
import nodes.MathNode;
import nodes.NodePin;

public class ConcatNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public ConcatNode()
    {
        pins[0] = new NodePin(this, "A", false, DataType.String);
        pins[1] = new NodePin(this, "B", false, DataType.String);
        pins[2] = new NodePin(this, "Result", true, DataType.String);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "String Concat Node";
    }
}
