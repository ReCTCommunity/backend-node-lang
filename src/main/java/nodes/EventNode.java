package nodes;

/* EventNode
 * =========
 * This node is the Blueprint for any event nodes.
 */
public abstract class EventNode extends Node {
    private final NodePin[] executionPins = new NodePin[1];

    public EventNode() {
        executionPins[0] = new NodePin(this, "out", true, true);
    }

    @Override
    public NodeType getType() {
        return NodeType.Event;
    }

    @Override
    public NodePin[] getExecutionPins()
    {
        return executionPins;
    }
}
