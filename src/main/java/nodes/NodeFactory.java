package nodes;

import languageNodes.*;

public class NodeFactory {
    public Node getNode(Class<?> type)
    {

        if(type == BeginEventNode.class)
            return new BeginEventNode();
        else if(type == RequestEventNode.class)
            return new RequestEventNode();
        else if(type == SetVariableNode.class)
            return new SetVariableNode();
        else if(type == ChangeVariableNode.class)
            return new ChangeVariableNode();

        return null;
    }
}
