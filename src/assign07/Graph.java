package assign07;

import java.util.*;

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
        Vertex<Type> source = vertices.get(srcData);
        Vertex<Type> destination = vertices.get(dstData);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        // Use depth-first recursive search to check if vertices are connected
        Set<Type> visited = new HashSet<>();
        return dfs(source, destination, visited);
    }

    public List<Type> shortestPath(Type srcData, Type dstData) {
        Vertex<Type> source = vertices.get(srcData);
        Vertex<Type> destination = vertices.get(dstData);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        // Use breadth-first search to find shortest path
        Map<Type, Type> parentMap = bfs(source, destination);
        return getPath(destination.getData(), parentMap);
    }

    public List<Type> topologicalSort() {
        // Implement topological sort
        List<Type> result = new ArrayList<>();
        Set<Type> visited = new HashSet<>();

        for (Vertex<Type> vertex : vertices.values()) {
            if (!visited.contains(vertex.getData())) {
                dfsTopoSort(vertex, visited, result);
            }
        }

        return result;
    }
    private boolean dfs(Vertex<Type> current, Vertex<Type> destination, Set<Type> visited) {
        if (current.equals(destination)) {
            return true;
        }

        visited.add(current.getData());
        for (Edge<Type> edge : getEdgesFromVertex(current)) {
            Vertex<Type> neighbor = edge.getDestination();
            if (!visited.contains(neighbor.getData())) {
                if (dfs(neighbor, destination, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Map<Type, Type> bfs(Vertex<Type> start, Vertex<Type> destination) {
        Map<Type, Type> parentMap = new HashMap<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();
        Set<Type> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start.getData());

        while (!queue.isEmpty()) {
            Vertex<Type> current = queue.poll();
            if (current.equals(destination)) {
                break;
            }

            for (Edge<Type> edge : getEdgesFromVertex(current)) {
                Vertex<Type> neighbor = edge.getDestination();
                if (!visited.contains(neighbor.getData())) {
                    queue.offer(neighbor);
                    visited.add(neighbor.getData());
                    parentMap.put(neighbor.getData(), current.getData());
                }
            }
        }

        return parentMap;
    }

    private List<Type> getPath(Type destination, Map<Type, Type> parentMap) {
        List<Type> path = new ArrayList<>();
        Type current = destination;

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private void dfsTopoSort(Vertex<Type> current, Set<Type> visited, List<Type> result) {
        visited.add(current.getData());

        for (Edge<Type> edge : getEdgesFromVertex(current)) {
            Vertex<Type> neighbor = edge.getDestination();
            if (!visited.contains(neighbor.getData())) {
                dfsTopoSort(neighbor, visited, result);
            }
        }

        result.add(current.getData());
    }

    private List<Edge<Type>> getEdgesFromVertex(Vertex<Type> vertex) {
        List<Edge<Type>> edgesFromVertex = new ArrayList<>();

        for (Edge<Type> edge : edges) {
            if (edge.getSource().equals(vertex)) {
                edgesFromVertex.add(edge);
            }
        }

        return edgesFromVertex;
    }
}
