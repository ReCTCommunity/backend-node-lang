package nodes;

public class NodePin {
    private final int id;
    private final boolean output;
    private final String name;

    public NodePin(int id, boolean output, String name) {
        this.id = id;
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
