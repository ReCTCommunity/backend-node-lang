package nodes;

import languageNodes.*;

public class NodeFactory {
    public Node getNode(Class<?> type)
    {
        if(type == BeginNode.class)
        {
            return new BeginNode();
        }

        if(type == SetVariableNode.class)
        {
            return new SetVariableNode();
        }

        if(type == ChangeVariableNode.class)
        {
            return new ChangeVariableNode();
        }

        return null;
    }
}
