package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class GetDictionaryKeysNode extends MathNode {
    private final NodePin[] pins = new NodePin[2];
    public GetDictionaryKeysNode()
    {
        pins[0] = new NodePin(this, "Dictionary", false, Datatype.Dictionary);
        pins[1] = new NodePin(this, "Keys", true, Datatype.AnyCollection);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Get Dictionary Keys";
    }
}
