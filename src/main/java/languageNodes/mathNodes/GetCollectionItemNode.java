package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class GetCollectionItemNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public GetCollectionItemNode()
    {
        pins[0] = new NodePin(this, "Collection", false, Datatype.Any);
        pins[1] = new NodePin(this, "Index", false, Datatype.Int);
        pins[2] = new NodePin(this, "Item", true, Datatype.Any);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Get Collection Item";
    }
}
