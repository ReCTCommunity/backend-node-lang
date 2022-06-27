package nodes;

public class NodeLink {
    private final int start;
    private final int end;
    private final int id;

    public NodeLink(int start, int end, int id){
        this.start = start;
        this.end = end;
        this.id = id;
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
}
