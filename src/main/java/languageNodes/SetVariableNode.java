package languageNodes;

import nodes.Node;
import nodes.NodePin;

public class SetVariableNode extends Node {
    private final NodePin[] pins;
    public SetVariableNode(){
        super();
        pins = new NodePin[3];
        pins[0] = new NodePin("Variable", true);
        pins[1] = new NodePin("Value", true);
        pins[2] = new NodePin("", true);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Set Variable Node";
    }
}
