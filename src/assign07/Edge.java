package assign07;

/**
 * A simple edge class that holds both the source and destination vertices of the edge.
 *
 * @param <Type> - generic
 * @author Prof. Parker & Aiden Fornalski & Henry Sippel
 * @version October 25, 2023
 */
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
