package assign04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class LargestNumberSolver {

    /**
     * A simple insertion sort that sorts the array based on the Comparator
     *
     * @param arr - array to sort
     * @param cmp - comparator to sort the array
     * @param <T> - generic type
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && cmp.compare(arr[j], arr[j-1]) < 0; j--) {
                T temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }

    /**
     * Builds the largest number when combining (not adding) an array of integers as one number.
     *
     * @param arr - array of integers
     * @return - the largest number
     */
    public static BigInteger findLargestNumber(Integer[] arr) {
        if (arr.length == 0) {
            return BigInteger.ZERO;

        }
        //temp array to hold BigInteger representations
        BigInteger[] bigIntArr = new BigInteger[arr.length];
        for (int i = 0; i < arr.length; i++) {
            bigIntArr[i] = BigInteger.valueOf(arr[i]);
        }
        //sorts BigIntegers in descending order
        Comparator<BigInteger> customComparator = (num1, num2) -> {
            String concat1 = num1.toString() + num2.toString();
            String concat2 = num2.toString() + num1.toString();
            return concat2.compareTo(concat1); // Compare in reverse order
        };

        insertionSort(bigIntArr, customComparator);

        //
        StringBuilder largestNumberStr = new StringBuilder();
        for (BigInteger num : bigIntArr) {
            largestNumberStr.append(num);
        }
        //converts to BigInteger and returns
        return new BigInteger(largestNumberStr.toString());

    }


    /**
     * Builds the largest int when combining (not adding) an array of integers as one int, while staying
     * within the range of Integer.
     *
     * @param arr - array of integers
     * @return - the largest int
     * @throws OutOfRangeException - if the largest number built is outside the range of Integer
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        if (arr.length == 0) {
            return 0;
        }

        StringBuilder largestNumberStr = buildLargestNumber(arr);

        long value = Long.parseLong(largestNumberStr.toString());
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new OutOfRangeException("int");
        }
        return Integer.parseInt(largestNumberStr.toString());
    }

    /**
     * Builds the largest long when combining (not adding) an array of integers as one long, while staying
     * within the range of Long
     *
     * @param arr - array of integers
     * @return - the largest long
     * @throws OutOfRangeException - if the largest number built is outside the range of Long
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        if (arr.length == 0) {
            return 0;
        }

        StringBuilder largestNumberStr = buildLargestNumber(arr);

        BigInteger value = new BigInteger(largestNumberStr.toString());
        if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 || value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) {
            throw new OutOfRangeException("int");
        }
        return Integer.parseInt(largestNumberStr.toString());
    }

    /**
     * Computes the sum of the largest numbers from a list containing int arrays.
     *
     * @param list - list of int arrays
     * @return - sum of the largest numbers
     */
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger[] tempArr = new BigInteger[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArr[i] = findLargestNumber(list.get(i));
        }

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : tempArr) {
            sum = sum.add(number);
        }
        return sum;
    }

    /**
     * Finds the kth largest number from a list of int arrays. In this method, when k = 0, it
     * returns the largest number, while k = list.size()-1 returns the smallest number.
     *
     * @param list - list of int arrays
     * @param k - which largest number
     * @return - kth largest number
     * @throws IllegalArgumentException - if k is out of bounds of list
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        if (k < 0 || k > list.size()-1) {
            throw new IllegalArgumentException();
        }

        BigInteger[] tempArr = new BigInteger[list.size()];
        BigInteger[] indexArr = new BigInteger[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArr[i] = findLargestNumber(list.get(i));
            indexArr[i] = findLargestNumber(list.get(i));
        }

        insertionSort(tempArr, Comparator.naturalOrder());
        BigInteger kthLargest = tempArr[tempArr.length-1-k];

        int kIndex = -1;
        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i].equals(kthLargest)) {
                kIndex = i;
                break;
            }
        }
        return list.get(kIndex);
    }

    /**
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public static List<Integer[]> readFile(String filename) {
        ArrayList<Integer[]> list = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(" ");
            Integer[] intArr = new Integer[temp.length];
            for (int i = 0; i < intArr.length; i++) {
                intArr[i] = Integer.parseInt(temp[i]);
            }
            list.add(intArr);
        }

        return list;
    }

    //HELPER METHODS
    private static StringBuilder buildLargestNumber(Integer[] arr) {
        Comparator<Integer> cmp = (num1, num2) -> {
            String concat1 = num1.toString() + num2.toString();
            String concat2 = num2.toString() + num1.toString();
            return concat2.compareTo(concat1); // Compare in reverse order
        };

        insertionSort(arr, cmp);

        StringBuilder largestNumberStr = new StringBuilder();
        for (int num : arr) {
            largestNumberStr.append(num);
        }
        return largestNumberStr;
    }
}
