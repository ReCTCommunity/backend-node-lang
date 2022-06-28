package languageNodes;

import nodes.EventNode;
import nodes.NodePin;

public class RequestEventNode extends EventNode {
    private final NodePin[] pins = new NodePin[1];
    public RequestEventNode() {
        super();
        pins[0] = new NodePin("Parameters", true, false);
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
