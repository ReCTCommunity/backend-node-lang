package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class LogicalNotNode extends MathNode {
    private final NodePin[] pins = new NodePin[2];
    public LogicalNotNode()
    {
        pins[0] = new NodePin(this, "A", false, Datatype.Bool);
        pins[1] = new NodePin(this, "Result", true, Datatype.Bool);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Logical Not";
    }
}
