package nodes;

public class NodeLink {
    private final int start;
    private final int end;
    private final int id;

    private static int nextLinkID = 1;

    public NodeLink(int start, int end){
        this.start = start;
        this.end = end;
        this.id = nextLinkID++;
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
