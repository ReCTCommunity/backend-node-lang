package languageNodes.devNodes;

import types.DataType;
import nodes.DevNode;
import nodes.NodePin;

public class PrintNode extends DevNode {
    private final NodePin[] pins = new NodePin[1];

    public PrintNode() {
        super();
        pins[0] = new NodePin(this, "Message", false, DataType.String);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Print Node";
    }
}
