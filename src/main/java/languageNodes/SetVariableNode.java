package languageNodes;

import nodes.Node;
import nodes.NodePin;
import nodes.NodeType;
import nodes.OperationNode;

public class SetVariableNode extends OperationNode {
    private final NodePin[] pins;
    public SetVariableNode(){
        super();
        pins = new NodePin[3];
        pins[0] = new NodePin("Variable", true, false);
        pins[1] = new NodePin("Value", true, false);
        pins[2] = new NodePin("", true, false);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Set Variable Operation";
    }
}
