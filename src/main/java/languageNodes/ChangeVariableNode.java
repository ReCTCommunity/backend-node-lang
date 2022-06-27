package languageNodes;

import nodes.Node;
import nodes.NodePin;

public class ChangeVariableNode extends Node {
    private final NodePin[] pins;
    public ChangeVariableNode(){
        super();
        pins = new NodePin[3];
        pins[0] = new NodePin("Variable", true);
        pins[1] = new NodePin("By(value)", true);
        pins[2] = new NodePin("", true);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Change Variable Node";
    }
}
