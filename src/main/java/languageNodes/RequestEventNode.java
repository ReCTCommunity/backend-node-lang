package languageNodes;

import nodes.Datatype;
import nodes.EventNode;
import nodes.NodePin;

public class RequestEventNode extends EventNode {
    private final NodePin[] pins = new NodePin[2];
    public RequestEventNode() {
        super();
        pins[0] = new NodePin(this, "Parameters", true, Datatype.Dictionary);
        pins[1] = new NodePin(this, "Test", true, true);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "RequestEvent";
    }
}
