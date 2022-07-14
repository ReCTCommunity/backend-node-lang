package languageNodes.controlNodes;

import nodes.ControlNode;
import nodes.NodeLink;
import nodes.NodePin;

import java.util.ArrayList;
import java.util.List;

public class SequenceNode extends ControlNode {
    private final List<NodePin> pins = new ArrayList<>();

    public SequenceNode() {
        super();
        pins.add(new NodePin(this, "SequencedOutput", true, true));
    }

    @Override
    public NodePin[] getPins() {
        return pins.toArray(new NodePin[0]);
    }

    @Override
    public String getName() {
        return "Sequence Node";
    }

    @Override
    public void OnOutputHookup(NodeLink link) {
        pins.add(new NodePin(this, "Sequenced Output", true, true));
    }

    @Override
    public void OnOutputRemoval(NodeLink link) {
        pins.remove(NodePin.AllPins.get(link.getStart()));
        NodePin.AllPins.remove(link.getStart());
    }
}
