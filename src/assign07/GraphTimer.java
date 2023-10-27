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
        int nMin = 100000;
        // Largest value of N.
        int nMax = 200000;
        // Increment value for N.
        int nStep = 10000;
        // Setup storage for problem sizes and average runtimes.
        ArrayList<Integer> problemSizes = new ArrayList<Integer>();
        ArrayList<Double> averageTimes = new ArrayList<Double>();
        for (int n = nMin; n <= nMax; n += nStep) {
            generateRandomDotFile("graphTiming", n);
            // We will generally want to run the timing test for N
            // multiple times to smooth out any abnormalities.
            // Number of times to run each test.
            // Larger values yield more reliable averages.
            int loopCount = 1000;
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
                Random rand = new Random();
                ArrayList<String> sources = new ArrayList<>();
                ArrayList<String> destinations = new ArrayList<>();
                GraphUtility.buildListsFromDot("src/assign07/graphTiming", sources, destinations);
                GraphUtility.areConnected(sources, destinations, "v" + rand.nextInt(n), "v" + rand.nextInt(n));
            }
            end = System.nanoTime();
            // so the total time elapsed is (end - start).
            // Compute the extra time it took to do any setup
            // and cleanup the specified number of times.
            for (int l = 0; l < loopCount; l++) {
            // do same setup
            // do same cleanup
                Random rand = new Random();
                ArrayList<String> sources = new ArrayList<>();
                ArrayList<String> destinations = new ArrayList<>();
                GraphUtility.buildListsFromDot("src/assign07/graphTiming", sources, destinations);
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

    public Graph<String> generateGraph(int vertexCount) {
        // generate a list of vertices
        ArrayList<String> sources = new ArrayList<>();
        ArrayList<String> destinations = new ArrayList<>();
        for(int i = 0; i < vertexCount; i++) {
            if (i%2 == 0) {
                sources.add("v" + i);
            } else {
                destinations.add("v" + i);
            }
        }
    }
}
