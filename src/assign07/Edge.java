package assign07;

public class Edge<Type> {
    private Vertex<Type> source;
    private Vertex<Type> destination;

    public Edge(Vertex<Type> source, Vertex<Type> destination) {
        this.source = source;
        this.destination = destination;
    }

    public Vertex<Type> getSource() {
        return source;
    }

    public Vertex<Type> getDestination() {
        return destination;
    }
}
