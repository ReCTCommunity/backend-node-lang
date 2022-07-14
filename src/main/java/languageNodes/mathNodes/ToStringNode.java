package languageNodes.mathNodes;

import types.DataType;
import nodes.MathNode;
import nodes.NodePin;

public class ToStringNode extends MathNode {
    private final NodePin[] pins = new NodePin[2];
    public ToStringNode()
    {
        pins[0] = new NodePin(this, "A", false, DataType.Any);
        pins[1] = new NodePin(this, "Result", true, DataType.String);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "To String Node";
    }
}
