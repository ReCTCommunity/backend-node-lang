package nodes;

import languageNodes.*;
import languageNodes.controlNodes.ForNode;
import languageNodes.controlNodes.IfNode;
import languageNodes.controlNodes.SequenceNode;
import languageNodes.controlNodes.WhileNode;
import languageNodes.devNodes.PrintNode;
import languageNodes.mathNodes.*;

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

        else if(type == CastAnyToTypeNode.class)
            return new CastAnyToTypeNode();
        else if(type == TypeLiteralNode.class)
            return new TypeLiteralNode();

        else if(type == EqualsNode.class)
            return new EqualsNode();

        else if(type == GetCollectionItemNode.class)
            return new GetCollectionItemNode();
        else if(type == GetCollectionLenghNode.class)
            return new GetCollectionLenghNode();

        else if(type == GetDictionaryKeysNode.class)
            return new GetDictionaryKeysNode();
        else if(type == GetDictionaryItemNode.class)
            return new GetDictionaryItemNode();
        else if(type == GetDictionaryValuesNode.class)
            return new GetDictionaryValuesNode();

        else if(type == LogicalAndNode.class)
            return new LogicalAndNode();
        else if(type == LogicalOrNode.class)
            return new LogicalOrNode();
        else if(type == LogicalNotNode.class)
            return new LogicalNotNode();

        else if(type == IfNode.class)
            return new IfNode();
        else if(type == SequenceNode.class)
            return new SequenceNode();
        else if(type == WhileNode.class)
            return new WhileNode();
        else if(type == ForNode.class)
            return new ForNode();

        else if(type == ConcatNode.class)
            return new ConcatNode();
        else if(type == ToStringNode.class)
            return new ToStringNode();

        else if(type == PrintNode.class)
            return new PrintNode();

        return null;
    }
}
