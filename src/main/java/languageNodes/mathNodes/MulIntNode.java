package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class MulIntNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public MulIntNode()
    {
        pins[0] = new NodePin("A", false, Datatype.Int);
        pins[1] = new NodePin("B", false, Datatype.Int);
        pins[2] = new NodePin("Result", true, Datatype.Int);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Multiply Int";
    }
}
