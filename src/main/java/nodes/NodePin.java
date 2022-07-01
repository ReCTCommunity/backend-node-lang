package nodes;

import javax.xml.crypto.Data;
import java.util.Dictionary;
import java.util.Hashtable;

public class NodePin {
    public static Dictionary<Integer,NodePin> AllPins = new Hashtable<>();

    private final int id;
    private final boolean output;
    private final boolean execution;
    private Datatype datatype;
    private final String name;
    private final Node myNode;

    private static int nextPinID = 1;

    public boolean Connected;

    public NodePin(Node node, String name, boolean output, boolean execution) {
        this.id = nextPinID++;
        this.myNode = node;
        this.output = output;
        this.execution = execution;
        this.datatype = null;
        this.name = name;

        // register this pin for later use
        AllPins.put(id, this);
    }

    public NodePin(Node node, String name, boolean output, Datatype datatype) {
        this.myNode = node;
        this.id = nextPinID++;
        this.output = output;
        this.datatype = datatype;
        this.execution = false;
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

    public Datatype getDatatype() {
        return datatype;
    }
    public void setDatatype(Datatype v) {
        datatype = v;
    }

    public String getName() {
        return name;
    }

    public Node getNode(){
        return myNode;
    }
}
