package languageNodes;

import nodes.Node;
import nodes.NodePin;
import nodes.NodeType;
import nodes.OperationNode;

public class ChangeVariableNode extends OperationNode {
    private final NodePin[] pins;
    public ChangeVariableNode(){
        super();
        pins = new NodePin[2];
        pins[0] = new NodePin("Variable", false, false);
        pins[1] = new NodePin("By(value)", false, false);
        //pins[2] = new NodePin("", true, false);
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Change Variable Operation";
    }
}
