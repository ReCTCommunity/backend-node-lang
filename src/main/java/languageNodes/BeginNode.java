package languageNodes;

import nodes.Node;
import nodes.NodePin;

public class BeginNode extends Node {
    private final NodePin[] pins;
    public BeginNode(){
        super();
        pins = new NodePin[1];
        pins[0] = new NodePin("Run", false);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Main/Begin Node";
    }
}
