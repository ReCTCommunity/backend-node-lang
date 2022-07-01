package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodePin;

public class GetCollectionLenghNode extends MathNode {
    private final NodePin[] pins = new NodePin[2];
    public GetCollectionLenghNode()
    {
        pins[0] = new NodePin(this, "Collection", false, Datatype.Any);
        pins[1] = new NodePin(this, "Length", true, Datatype.Int);
    }
    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Get Collection Length";
    }
}
