package nodes;

public class NodePin {
    private final int id;
    private final boolean output;
    private final String name;

    private static int nextPinID = 1;

    public NodePin(String name, boolean output) {
        this.id = nextPinID++;
        this.output = output;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isOutput() {
        return output;
    }

    public String getName() {
        return name;
    }
}
