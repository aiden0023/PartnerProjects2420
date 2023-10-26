package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<Type> {
    private Map<Type, Vertex<Type>> vertices;
    private List<Edge<Type>> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Type data) {
        Vertex<Type> vertex = new Vertex<>(data);
        vertices.put(data, vertex);
    }

    public void addEdge(Type srcData, Type dstData) {
        Vertex<Type> source = vertices.get(srcData);
        Vertex<Type> destination = vertices.get(dstData);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        edges.add(new Edge<>(source, destination));
    }

    public boolean areConnected(Type srcData, Type dstData) {
        // Implement depth-first search to check if vertices are connected
        return false;
    }

    public List<Type> shortestPath(Type srcData, Type dstData) {
        // Implement breadth-first search to find shortest path
        return null;
    }

    public List<Type> topologicalSort() {
        // Implement topological sort
        return null;
    }
}
