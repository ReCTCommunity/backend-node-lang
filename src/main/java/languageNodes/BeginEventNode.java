package languageNodes;

import nodes.EventNode;
import nodes.NodePin;

public class BeginEventNode extends EventNode {

    public BeginEventNode() {
        super();
    }

    @Override
    public NodePin[] getPins() {
        return new NodePin[0];
    }

    @Override
    public String getName() {
        return "BeginEvent";
    }
}
