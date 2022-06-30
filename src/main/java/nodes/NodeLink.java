package nodes;

import javax.xml.crypto.Data;
import java.util.Dictionary;

public class NodeLink {
    private final int start;
    private final int end;
    private final int id;

    private final boolean isExecution;
    private final Datatype datatype;

    private static int nextLinkID = 1;

    public NodeLink(int start, int end, boolean isExecution){
        this.start = start;
        this.end = end;
        this.id = nextLinkID++;
        this.isExecution = isExecution;
        this.datatype = null;
    }

    public NodeLink(int start, int end, Datatype datatype){
        this.start = start;
        this.end = end;
        this.id = nextLinkID++;
        this.isExecution = false;
        this.datatype = datatype;
    }

    public int getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isExecution() {
        return isExecution;
    }

    public Datatype getDatatype() {
        return datatype;
    }
}
