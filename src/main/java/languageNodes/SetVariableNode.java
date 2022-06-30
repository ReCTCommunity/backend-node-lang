package languageNodes;

import nodes.*;

public class SetVariableNode extends OperationNode {
    private final NodePin[] pins;
    public SetVariableNode(){
        super();
        pins = new NodePin[3];
        pins[0] = new NodePin("Variable", false, Datatype.Variable);
        pins[1] = new NodePin("Value", false, Datatype.Any);
        pins[2] = new NodePin("Pass Through", true, Datatype.Any);
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
