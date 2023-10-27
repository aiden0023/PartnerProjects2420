package assign07;

import java.util.*;

/**
 * A graph class that holds the vertices and edges of the graph as well as the running
 * methods and its helper methods for the static methods in GraphUtility.
 *
 * @param <Type> - generic
 * @author Prof. Parker & Aiden Fornalski & Henry Sippel
 * @version October 25, 2023
 */
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

    /**
     * This method is called from areConnected() from GraphUtility in order to setup and start the
     * recursive dfs method.
     *
     * @param srcData - source vertex
     * @param dstData - destination vertex
     * @return - if source vertex and destination vertex are connected with an edge
     */
    public boolean areConnected(Type srcData, Type dstData) {
        Vertex<Type> source = vertices.get(srcData);
        Vertex<Type> destination = vertices.get(dstData);

        if (source == null || destination == null) { //check if vertices exist in the graph
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        Set<Type> visited = new HashSet<>(); //visited hashset
        return dfs(source, destination, visited); //start recursive method
    }

    /**
     * This method is called from shortestPath() from GraphUtility in order to setup and call the
     * bfs method.
     *
     * @param srcData - source vertex
     * @param dstData - destination vertex
     * @return - a list containing the path from source vertex to destination vertex
     */
    public List<Type> shortestPath(Type srcData, Type dstData) {
        Vertex<Type> source = vertices.get(srcData);
        Vertex<Type> destination = vertices.get(dstData);

        if (source == null || destination == null) { //check if vertices exist in the graph
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        Map<Type, Type> parentMap = bfs(source, destination); //start bfs
        return getPath(destination.getData(), parentMap); //list edges and vertices to get to desired destination
    }

    /**
     * Sorts the vertices of a graph using the topological sorting algorithm.
     *
     * @return - sorted list of vertices
     */
    public List<Type> topologicalSort() {
        Map<Type, Integer> inDegree = new HashMap<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();

        for (Vertex<Type> vertex : vertices.values()) { //populate list of vertices
            inDegree.put(vertex.getData(), 0);
        }

        for (Edge<Type> edge : edges) { //add appropriate indegrees to each vertex
            Vertex<Type> destination = edge.getDestination();
            inDegree.put(destination.getData(), inDegree.get(destination.getData()) + 1);
        }

        for (Vertex<Type> vertex : vertices.values()) { //adds root vertices to queue
            if (inDegree.get(vertex.getData()) == 0) {
                queue.offer(vertex);
            }
        }

        List<Type> result = new ArrayList<>(); //create the result list
        while (!queue.isEmpty()) { //while loop to loop through entire queue
            Vertex<Type> current = queue.poll();
            result.add(current.getData()); //add current vertex to results

            for (Edge<Type> edge : getEdgesFromVertex(current)) { //loops through the list of valid edges
                Vertex<Type> neighbor = edge.getDestination();
                inDegree.put(neighbor.getData(), inDegree.get(neighbor.getData()) - 1); //-1 indegree to neighbor vertex of the current vertex
                if (inDegree.get(neighbor.getData()) == 0) { //if indegree gets to 0, add vertex to queue
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) { //checks for cyclic graph, throws exception
            throw new IllegalArgumentException("Graph contains a cycle");
        }

        return result; //returns result list
    }

    /**
     * The recursive method for areConnected(). Uses DFS to find if two vertices has an edge that connects
     * them.
     *
     * @param current - current vertex
     * @param destination - destination vertex
     * @param visited - list of visited vertices
     * @return - if there is an edge that connects the source vertex to the destination vertex
     */
    private boolean dfs(Vertex<Type> current, Vertex<Type> destination, Set<Type> visited) {
        if (current.getData().equals(destination.getData())) { //check if arrived at destination vertex
            return true;
        }

        visited.add(current.getData()); //add current vertex to visited list
        for (Edge<Type> edge : getEdgesFromVertex(current)) { //loops through list of valid edges
            Vertex<Type> neighbor = edge.getDestination();
            if (!visited.contains(neighbor.getData())) { //check if neighbor vertex is already in visited list
                if (dfs(neighbor, destination, visited)) { //recursive method (returns true if previous dfs call returned true)
                    return true;
                }
            }
        }

        return false; //if cannot find destination vertex from source vertex
    }

    /**
     * Method called by shortestPath(). Uses BFS to search for a path from the start vertex to the
     * destination vertex.
     *
     * @param start - starting vertex
     * @param destination - destination vertex
     * @return - map of sequences to get to destination vertex
     */
    private Map<Type, Type> bfs(Vertex<Type> start, Vertex<Type> destination) {
        Map<Type, Type> parentMap = new HashMap<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();
        Set<Type> visited = new HashSet<>();

        queue.offer(start); //adds start to queue
        visited.add(start.getData()); //adds start to visited list

        while (!queue.isEmpty()) { //loops through the entire queue
            Vertex<Type> current = queue.poll();
            if (current.equals(destination)) { //if at destination vertex, break out of loop
                break;
            }

            for (Edge<Type> edge : getEdgesFromVertex(current)) { //loops through list of valid edges
                Vertex<Type> neighbor = edge.getDestination();
                if (!visited.contains(neighbor.getData())) { //if vertex was not visited yet
                    queue.offer(neighbor); //add neighbor to queue
                    visited.add(neighbor.getData()); //add neighbor to visited list
                    parentMap.put(neighbor.getData(), current.getData()); //add pair to map
                }
            }
        }

        if (!visited.contains(destination.getData())) { //checks if destination vertex was visited to determine if there is a path from source to destination, throws exception if not
            throw new IllegalArgumentException("No path found between source and destination");
        }

        return parentMap;
    }

    /**
     * Creates a list that contains a path from the start vertex to the destination vertex. Helper method
     * for bfs().
     *
     * @param destination - destination vertex
     * @param parentMap - map of sequences to get to destination vertex
     * @return - a list containing the path from the start vertex to the destination vertex
     */
    private List<Type> getPath(Type destination, Map<Type, Type> parentMap) {
        List<Type> path = new ArrayList<>();
        Type current = destination;

        while (current != null) { //loops until all vertices are added to form a path from destination to source in the path list
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path); //changes the path to from source to destination
        return path;
    }

    /**
     * Creates a list of edges that a vertex has. Helper method.
     *
     * @param vertex - vertex to get edges from
     * @return - list of valid edges
     */
    private List<Edge<Type>> getEdgesFromVertex(Vertex<Type> vertex) {
        List<Edge<Type>> edgesFromVertex = new ArrayList<>(); //list of edges that have the source of vertex

        for (Edge<Type> edge : edges) { //loops through list of all edges
            if (edge.getSource().getData().equals(vertex.getData())) { //checks to see if the edge's source is vertex
                edgesFromVertex.add(edge); //adds edge to list
            }
        }

        return edgesFromVertex;
    }
}
