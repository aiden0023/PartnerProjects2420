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
        Map<Type, Integer> inDegree = new HashMap<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();

        for (Vertex<Type> vertex : vertices.values()) {
            inDegree.put(vertex.getData(), 0);
        }

        for (Edge<Type> edge : edges) {
            Vertex<Type> destination = edge.getDestination();
            inDegree.put(destination.getData(), inDegree.get(destination.getData()) + 1);
        }

        for (Vertex<Type> vertex : vertices.values()) {
            if (inDegree.get(vertex.getData()) == 0) {
                queue.offer(vertex);
            }
        }

        List<Type> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex<Type> current = queue.poll();
            result.add(current.getData());

            for (Edge<Type> edge : getEdgesFromVertex(current)) {
                Vertex<Type> neighbor = edge.getDestination();
                inDegree.put(neighbor.getData(), inDegree.get(neighbor.getData()) - 1);
                if (inDegree.get(neighbor.getData()) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }

        return result;
    }
    private boolean dfs(Vertex<Type> current, Vertex<Type> destination, Set<Type> visited) {
        if (current.getData().equals(destination.getData())) {
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

        if (!visited.contains(destination.getData())) {
            throw new IllegalArgumentException("No path found between source and destination");
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

    private List<Edge<Type>> getEdgesFromVertex(Vertex<Type> vertex) {
        List<Edge<Type>> edgesFromVertex = new ArrayList<>();

        for (Edge<Type> edge : edges) {
            if (edge.getSource().getData().equals(vertex.getData())) {
                edgesFromVertex.add(edge);
            }
        }

        return edgesFromVertex;
    }
}
