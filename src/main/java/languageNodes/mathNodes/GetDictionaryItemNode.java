package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class GetDictionaryItemNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public GetDictionaryItemNode()
    {
        pins[0] = new NodePin(this, "Dictionary", false, Datatype.Dictionary);
        pins[1] = new NodePin(this, "Key", false, Datatype.Any);
        pins[2] = new NodePin(this, "Value", true, Datatype.Any);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Get Dictionary Item";
    }
}
