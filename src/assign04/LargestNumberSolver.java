package assign04;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        for (int i = 1; i < arr.length-1; i++) {
            for (int j = i; j > 0 && cmp.compare(arr[j], arr[j-1]) < 0; j--) {
                T temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }

    public static BigInteger findLargestNumber(Integer[] arr) {
        if (arr.length == 0) {
            return BigInteger.ZERO;

        }
//temp array to hold BigInteger representations
                BigInteger[] bigIntArr = new BigInteger[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    bigIntArr[i] = BigInteger.valueOf(arr[i]);
                }
//sorts BigIntegers in decending order
            Comparator<BigInteger> customComparator = (num1, num2) -> {
                String concat1 = num1.toString() + num2.toString();
                String concat2 = num2.toString() + num1.toString();
                return concat2.compareTo(concat1); // Compare in reverse order
            };
//sorts them without altering array
                BigInteger[] sortedArr = Arrays.copyOf(bigIntArr, bigIntArr.length);
                insertionSort(sortedArr, customComparator);

//
                StringBuilder largestNumberStr = new StringBuilder();
                for (BigInteger num : sortedArr) {
                    largestNumberStr.append(num);
                }
//converts to BigInteger and returns
                return new BigInteger(largestNumberStr.toString());

            }


    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        return 0; //EMPTY RETURN STATEMENT
    }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        return 0; //EMPTY RETURN STATEMENT
    }

    public static BigInteger sum(List<Integer[]> list) {
        return null; //EMPTY RETURN STATEMENT
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        return null; //EMPTY RETURN STATEMENT
    }

    public static List<Integer[]> readFile(String filename) {
        return null; //EMPTY RETURN STATEMENT
    }
}
