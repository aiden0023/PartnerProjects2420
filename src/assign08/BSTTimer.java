package assign08;

import assign07.Graph;
import assign07.GraphUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class BSTTimer {
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
            //TreeSet<Integer> ts = new TreeSet<>();
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
                Random rng = new Random();
                for (int i = n; i > 0; i--) {
                    bst.add(rng.nextInt(n));
                }
            }
            end = System.nanoTime();
            // so the total time elapsed is (end - start).
            // Compute the extra time it took to do any setup
            // and cleanup the specified number of times.
            for (int l = 0; l < loopCount; l++) {
            // do same setup
            // do same cleanup
                Random rng = new Random();
                for (int i = n; i > 0; i--) {
                    ;
                }
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
}
