package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class LogicalOrNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public LogicalOrNode()
    {
        pins[0] = new NodePin(this, "A", false, Datatype.Bool);
        pins[1] = new NodePin(this, "B", false, Datatype.Bool);
        pins[2] = new NodePin(this, "Result", true, Datatype.Bool);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Logical Or";
    }
}
