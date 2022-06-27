package languageNodes;

import nodes.Node;
import nodes.NodePin;

public class TestNode extends Node {
    private final NodePin[] pins;
    public TestNode(){
        super();
        pins = new NodePin[2];
        pins[0] = new NodePin("hello", false);
        pins[1] = new NodePin("hi!", true);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "TestNode";
    }
}
