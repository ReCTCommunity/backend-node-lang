package languageNodes.controlNodes;

import nodes.ControlNode;
import types.DataType;
import nodes.NodePin;

public class WhileNode extends ControlNode {
    private final NodePin[] pins = new NodePin[3];

    public WhileNode() {
        super();
        pins[0] = new NodePin(this, "Condition", false, DataType.Bool);
        pins[1] = new NodePin(this, "Body", true, true);
        pins[2] = new NodePin(this, "End", true, true);
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
