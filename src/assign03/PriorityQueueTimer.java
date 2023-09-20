package assign03;

import java.util.ArrayList;
import java.util.Random;

import assign03.SimplePriorityQueue;

public class PriorityQueueTimer {
	public static Random RANDOM = new Random();

	// Pick appropriate values for your analysis
//	public static int LOOP_COUNT = 1000;
	public static int LOOP_COUNT = 100;
//	public static int STEP_SIZE = 40000;
	public static int STEP_SIZE = 100000;
//	public static int ROW_COUNT = 50;
	public static int ROW_COUNT = 20;

	public static void main(String[] args) {
		ArrayList<Integer> arraySizes = new ArrayList<Integer>();
		ArrayList<Double> averageTimes = new ArrayList<Double>();

		warmUp(1);
		
		for (int row = 0; row < ROW_COUNT; row++) {
			int size = STEP_SIZE * (row + 1);

			double averageTime = computeAverageSortTime(size);
			
			System.out.println(size + ": " + averageTime);
			
			// Store array size and average time
			arraySizes.add(size);
			averageTimes.add(averageTime);
		}

		// Print arraySizes and averageTimes for plotting
		System.out.println("\narray_sizes = " + arraySizes);
		System.out.println("average_times = " + averageTimes);
	}
	
	public static double computeAverageSortTime(int size) {
		SimplePriorityQueue<Integer> spq = new SimplePriorityQueue<Integer>();
		for (int i = 0; i < size; i++) {
			spq.insert(i);  // O(logN) operation -- why?
		}

		double startTime, midTime, stopTime;
		
		// Re-warm the system
		warmUp(1);
		
		// Begin timer
		startTime = System.nanoTime();
		
		// Do the computation loopCount times
		for (int i = 0; i < LOOP_COUNT; i++) {
			//Integer element = RANDOM.nextInt(size);
			spq.findMax();
		}
		
		// End timer
		midTime = System.nanoTime();

		for(int i = 0; i < LOOP_COUNT; i++) {

		}
		stopTime = System.nanoTime();

		// Return average time
		return ((midTime - startTime) - (stopTime - midTime)) / (double) LOOP_COUNT;
	}
	
	/**
	 * Warm up the system for a specified amount of time.
	 * @param seconds
	 */
	private static void warmUp(double seconds) {
		long time = System.nanoTime();
		while (System.nanoTime() - time < 1_000_000_000 * seconds) {} 
	}

}
