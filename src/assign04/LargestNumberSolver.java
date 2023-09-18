package assign04;

import java.math.BigInteger;
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

        //FINISH METHOD

        return null; //EMPTY RETURN STATEMENT
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
