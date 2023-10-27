package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GraphTimer {
    public static void main(String[] args) {
        // Identify your problem size: N
        // We will generally want to find runtimes at regular intervals of N
        // For example, N = 100000, 110000, 120000, 130000, ..., 200000
        // Smallest value of N.
        int nMin = 10000;
        // Largest value of N.
        int nMax = 20000;
        // Increment value for N.
        int nStep = 1000;
        // Setup storage for problem sizes and average runtimes.
        ArrayList<Integer> problemSizes = new ArrayList<Integer>();
        ArrayList<Double> averageTimes = new ArrayList<Double>();
        for (int n = nMin; n <= nMax; n += nStep) {
            // We will generally want to run the timing test for N
            // multiple times to smooth out any abnormalities.
            // Number of times to run each test.
            // Larger values yield more reliable averages.
            int loopCount = 100;
            // Now we run 100 timing tests for each value of N.
            // Variables to keep track of time in nanoseconds.
            long start, end, extra;
            // Placeholder for any shared setup for the timing test.
            // We want to warm up the system, for example warm up
            // for 1 second = 1_000_000_000 nanoseconds.
            start = System.nanoTime();
            while (System.nanoTime() - start > 1_000_000_000) {}
            // Compute the total time it takes to run the timing
            // test the specified number of times.
            start = System.nanoTime();
            for (int l = 0; l < loopCount; l++) {
            // do any setup
            // run the algorithm or method
            // do any cleanup
                ArrayList<String> sources = new ArrayList<>();
                ArrayList<String> destinations = new ArrayList<>();
                generateVertices(sources, destinations, n);
                Random rng = new Random();
                //GraphUtility.areConnected(sources, destinations, sources.get(rng.nextInt(n)), destinations.get(rng.nextInt(n)));
                /*try {
                    GraphUtility.shortestPath(sources, destinations, sources.get(rng.nextInt(n)), destinations.get(rng.nextInt(n)));
                } catch (Exception e) {
                    //ignore
                }*/
                try {
                    GraphUtility.sort(sources, destinations);
                } catch (Exception e) {
                    //ignore
                }
            }
            end = System.nanoTime();
            // so the total time elapsed is (end - start).
            // Compute the extra time it took to do any setup
            // and cleanup the specified number of times.
            for (int l = 0; l < loopCount; l++) {
            // do same setup
            // do same cleanup
                ArrayList<String> sources = new ArrayList<>();
                ArrayList<String> destinations = new ArrayList<>();
                generateVertices(sources, destinations, n);
                Random rng = new Random();
            }
            extra = System.nanoTime();
            // Total time to run the test the specified number of times.
            double totalTime = end - start;
            // Total time to do any auxiliary setup/cleanup the specified
            // number of times.
            double extraTime = extra - end;
            // Average time for just the algorithm or method
            double averageTime = (totalTime - extraTime) / loopCount;
            // store data size and average runtime
            problemSizes.add(n);
            averageTimes.add(averageTime);
            // print results to console if you want
            System.out.println(n + "\t " + averageTime);
        }
        // print problemSizes and averageTimes
        System.out.println("\nproblemSizes = " + problemSizes);
        System.out.println("averageTimes = " + averageTimes);
    }

    public static void generateRandomDotFile(String filename, int vertexCount) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(filename);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        Random rng = new Random();

        // randomly construct a digraph
        String edgeOp = " -> ";
        out.println("digraph G {");

        // generate a list of vertices
        String[] vertex = new String[vertexCount];
        for(int i = 0; i < vertexCount; i++)
            vertex[i] = "v" + i;

        // randomly connect the vertices using 2 * |V| edges
        for(int i = 0; i < 2 * vertexCount; i++)
            out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);

        out.println("}");
        out.close();
    }

    public static Graph<Integer> generateGraph(int vertexCount) {
        Graph<Integer> graph = new Graph<>();

        // Add vertices labeled from 1 to vertexCount
        for (int i = 1; i <= vertexCount; i++) {
            graph.addVertex(i);
        }

        Random random = new Random();

        int maxEdges = 2 * vertexCount;
        int addedEdges = 0;

        while (addedEdges < maxEdges) {
            int source = random.nextInt(vertexCount) + 1;
            int destination = random.nextInt(vertexCount) + 1;

            if (source != destination && !graph.areConnected(source, destination)) {
                graph.addEdge(source, destination);
                addedEdges++;
            }
        }
        return graph;
    }

    public static void generateVertices(ArrayList<String> sources, ArrayList<String> destinations, int vertexCount) {
        Random rng = new Random();
        for (int i = 0; i < 2*vertexCount; i++) {
            sources.add(("v"+rng.nextInt(vertexCount)));
            destinations.add(("v"+rng.nextInt(vertexCount)));
        }
    }
}
