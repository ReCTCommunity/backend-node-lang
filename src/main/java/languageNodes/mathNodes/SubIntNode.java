package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class SubIntNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public SubIntNode()
    {
        pins[0] = new NodePin(this, "A", false, Datatype.Int);
        pins[1] = new NodePin(this, "B", false, Datatype.Int);
        pins[2] = new NodePin(this, "Result", true, Datatype.Int);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Subtract Int";
    }
}
