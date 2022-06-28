package nodes;

import java.util.Dictionary;
import java.util.Hashtable;

public class NodePin {
    public static Dictionary<Integer,NodePin> AllPins = new Hashtable<>();

    private final int id;
    private final boolean output;
    private final boolean execution;
    private final String name;

    private static int nextPinID = 1;

    public NodePin(String name, boolean output, boolean execution) {
        this.id = nextPinID++;
        this.output = output;
        this.execution = execution;
        this.name = name;

        // register this pin for later use
        AllPins.put(id, this);
    }

    public int getId() {
        return id;
    }

    public boolean isOutput() {
        return output;
    }
    public boolean isExecution() {
        return execution;
    }

    public String getName() {
        return name;
    }
}
