package languageNodes.mathNodes;

import nodes.Datatype;
import nodes.MathNode;
import nodes.NodeLink;
import nodes.NodePin;

public class CastAnyToTypeNode extends MathNode {
    private final NodePin[] pins = new NodePin[3];
    public CastAnyToTypeNode()
    {
        pins[0] = new NodePin(this, "Any", false, Datatype.Any);
        pins[1] = new NodePin(this, "Type Reference", false, Datatype.Any);
        pins[2] = new NodePin(this, "Result", true, Datatype.Any);
    }

    @Override
    public void OnInputHookup(NodeLink link) {
        if (link.getEnd() == pins[1].getId()) {
            pins[2].setDatatype(link.getDatatype());
        }
    }

    @Override
    public NodePin[] getPins() {
        return pins;
    }

    @Override
    public String getName() {
        return "Cast Any to Type";
    }
}
