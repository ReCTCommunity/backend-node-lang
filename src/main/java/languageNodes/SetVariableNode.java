package languageNodes;

import nodes.*;

public class SetVariableNode extends OperationNode {
    private final NodePin[] pins;
    public SetVariableNode(){
        super();
        pins = new NodePin[3];
        pins[0] = new NodePin(this, "Variable", false, Datatype.Variable);
        pins[1] = new NodePin(this, "Value", false, Datatype.Any);
        pins[2] = new NodePin(this, "Pass Through", true, Datatype.Any);
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
