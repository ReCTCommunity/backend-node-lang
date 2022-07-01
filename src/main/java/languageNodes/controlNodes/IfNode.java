package languageNodes.controlNodes;

import nodes.ControlNode;
import nodes.Datatype;
import nodes.NodePin;

public class IfNode extends ControlNode {
    private final NodePin[] pins = new NodePin[3];

    public IfNode() {
        super();
        pins[0] = new NodePin(this, "Condition", false, Datatype.Bool);
        pins[1] = new NodePin(this, "True", true, true);
        pins[2] = new NodePin(this, "False", true, true);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "If Node";
    }
}
