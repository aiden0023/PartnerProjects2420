package assign04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

/**
 * A class with multiple static methods that compute the largest number and methods relating
 * to finding the largest number.
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-09-21
 */
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
        if (arr.length == 0) { //check to see if array is empty
            return BigInteger.ZERO;

        }
        BigInteger[] bigIntArr = new BigInteger[arr.length]; //temp array to hold BigInteger representations
        for (int i = 0; i < arr.length; i++) {
            bigIntArr[i] = BigInteger.valueOf(arr[i]);
        }
        Comparator<BigInteger> cmp = (num1, num2) -> { //sorts BigIntegers in descending order
            String concat1 = num1.toString() + num2.toString();
            String concat2 = num2.toString() + num1.toString();
            return concat2.compareTo(concat1);
        };

        insertionSort(bigIntArr, cmp);

        StringBuilder largestNumberStr = new StringBuilder(); //converts largestNumberStr to a string
        for (BigInteger num : bigIntArr) {
            largestNumberStr.append(num);
        }
        return new BigInteger(largestNumberStr.toString()); //converts to BigInteger and returns

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
        if (arr.length == 0) { //check to see if array is empty
            return 0;
        }

        Integer[] tempArr = new Integer[arr.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length); //copies contents of arr to tempArr

        StringBuilder largestNumberStr = buildLargestNumber(tempArr); //builds the largest number as a string

        long value = Long.parseLong(largestNumberStr.toString());
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) { //checks to see if the largest number is within the min and max of int
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
        if (arr.length == 0) { //check to see if array is empty
            return 0;
        }

        Integer[] tempArr = new Integer[arr.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length); //copies contents of arr to tempArr

        StringBuilder largestNumberStr = buildLargestNumber(tempArr); //builds the largest number as a string

        BigInteger value = new BigInteger(largestNumberStr.toString());
        if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 || value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) { //checks to see if the largest number is within the min and max of long
            throw new OutOfRangeException("int");
        }
        return Long.parseLong(largestNumberStr.toString());
    }

    /**
     * Computes the sum of the largest numbers from a list containing int arrays.
     *
     * @param list - list of int arrays
     * @return - sum of the largest numbers
     */
    public static BigInteger sum(List<Integer[]> list) {
        if (list.size() == 0) { //check to see if list is empty
            return BigInteger.ZERO;
        }
        BigInteger[] tempArr = new BigInteger[list.size()]; //temp array to hold the largest numbers of each array
        for (int i = 0; i < list.size(); i++) {
            tempArr[i] = findLargestNumber(list.get(i)); //finding the largest number of the array at list.get(i)
        }

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : tempArr) { //adding the largest numbers together
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
        if (list.size() == 0) { //check to see if list is empty
            return null;
        }

        if (k < 0 || k > list.size()-1) { //checks to see if k is within bounds of the list
            throw new IllegalArgumentException();
        }

        Integer[][] temp = list.toArray(new Integer[list.size()][]); //makes a 2d matrix that holds the same contents as list
        ArrayComparator cmp = new ArrayComparator();
        insertionSort(temp, cmp); //sort the 2d matrix

        return temp[k]; //returns the kth largest array
    }

    /**
     * Reads a formatted file of integer arrays and converts it to a list of integer
     * arrays.
     *
     * @param filename - name of the file
     * @return - a list of integer arrays
     * @throws FileNotFoundException - when the filename does not exist
     */
    public static List<Integer[]> readFile(String filename) {
        ArrayList<Integer[]> list = new ArrayList<>(); //creates empty list to be returned
        Scanner scanner = null;
        try { //try-catch to check to see if the filename exists
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) { //loops through each line of the .txt file
            String line = scanner.nextLine();
            String[] temp = line.split(" "); //converts the line into a string array
            Integer[] intArr = new Integer[temp.length];
            for (int i = 0; i < intArr.length; i++) { //converts the string array into an integer array
                intArr[i] = Integer.parseInt(temp[i]);
            }
            list.add(intArr);
        }

        return list;
    }

    //HELPER METHODS

    /**
     * Builds a string of the largest number from an integer array.
     *
     * @param arr - integer array to build the largest number from
     * @return - a string of the largest number
     */
    private static StringBuilder buildLargestNumber(Integer[] arr) {
        Comparator<Integer> cmp = (num1, num2) -> { //sorts BigIntegers in descending order
            String concat1 = num1.toString() + num2.toString();
            String concat2 = num2.toString() + num1.toString();
            return concat2.compareTo(concat1);
        };

        insertionSort(arr, cmp); //sorts the array with the above comparator

        StringBuilder largestNumberStr = new StringBuilder(); //converts largestNumberStr to a string
        for (int num : arr) {
            largestNumberStr.append(num);
        }
        return largestNumberStr;
    }

    private static class ArrayComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            BigInteger o1Largest = findLargestNumber(o1); //converts o1 to the largest number
            BigInteger o2Largest = findLargestNumber(o2); //converts o2 to the largest number

            //logic for a reversed natural order comparator of the largest numbers
            if (o1Largest.compareTo(o2Largest) > 0) {
                return -1;
            } else if (o1Largest.compareTo(o2Largest) < 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
