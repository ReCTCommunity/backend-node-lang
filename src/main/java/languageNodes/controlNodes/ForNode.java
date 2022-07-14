package languageNodes.controlNodes;

import nodes.ControlNode;
import types.DataType;
import nodes.NodePin;

public class ForNode extends ControlNode {
    private final NodePin[] pins = new NodePin[5];

    public ForNode() {
        super();
        pins[0] = new NodePin(this, "Starting From", false, DataType.Int);
        pins[1] = new NodePin(this, "Counting To", false, DataType.Int);
        pins[2] = new NodePin(this, "Body", true, true);
        pins[3] = new NodePin(this, "Index", true, DataType.Int);
        pins[4] = new NodePin(this, "End", true, true);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "While Node";
    }
}
