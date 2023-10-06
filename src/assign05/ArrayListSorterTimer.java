package assign05;

import java.util.ArrayList;

import static assign05.ArrayListSorter.*;

public class ArrayListSorterTimer {
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
            ArrayList<Integer> arrayList = generateDescending(n);
            // We will generally want to run the timing test for N
            // multiple times to smooth out any abnormalities.
            // Number of times to run each test.
            // Larger values yield more reliable averages.
            int loopCount = 200;
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
                ArrayList<Integer> temp = arrayList;
                quicksort(temp);
            }
            end = System.nanoTime();
            // so the total time elapsed is (end - start).
            // Compute the extra time it took to do any setup
            // and cleanup the specified number of times.
            for (int l = 0; l < loopCount; l++) {
            // do same setup
            // do same cleanup
                ArrayList<Integer> temp = arrayList;
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
