package nodes;

import languageNodes.*;
import languageNodes.mathNodes.AddIntNode;
import languageNodes.mathNodes.DivIntNode;
import languageNodes.mathNodes.MulIntNode;
import languageNodes.mathNodes.SubIntNode;

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

        else if(type == AddIntNode.class)
            return new AddIntNode();
        else if(type == SubIntNode.class)
            return new SubIntNode();
        else if(type == MulIntNode.class)
            return new MulIntNode();
        else if(type == DivIntNode.class)
            return new DivIntNode();

        return null;
    }
}
