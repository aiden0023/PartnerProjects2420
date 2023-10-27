package assign07;

/**
 * A simple vertex class that holds data (usual the name of the vertex).
 *
 * @param <Type> - generic
 * @author Prof. Parker & Aiden Fornalski & Henry Sippel
 * @version October 25, 2023
 */
public class Vertex<Type> {
    private Type data;

    public Vertex(Type data) {
        this.data = data;
    }

    public Type getData() {
        return data;
    }
}
