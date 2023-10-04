package assign05;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListSorter {

    private static final int INSERTION_SORT_THRESHOLD = 6; //7 or greater and JDK uses mergesort over insertion sort

    public static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> arrayList) {
        ArrayList<T> temp = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            temp.add(null); // Pre-allocate space for merging
        }
        mergeSort(arrayList, temp, 0, arrayList.size() - 1);
    }


    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList) {

    }

    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i, i+1);
        }
        return arrayList;
    }

    public static ArrayList<Integer> generatePermuted(int size) {
        return null;
    }

    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = size; i > 0; i--) {
            arrayList.add(size-i, i);
        }
        return arrayList;
    }

    //HELPER METHODS

    private static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> arrayList, ArrayList<T> temp, int left, int right) {
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arrayList, left, right);
        } else {
            int mid = (left + right) / 2;
            mergeSort(arrayList, temp, left, mid);
            mergeSort(arrayList, temp, mid + 1, right);
            merge(arrayList, temp, left, mid, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arrayList, ArrayList<T> temp, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        for (int i = 0; i < n1; i++) {
            temp.set(i, arrayList.get(left + i));
        }

        int i = 0, j = mid + 1;
        int k = left;

        while (i < n1 && j <= right) {
            if (temp.get(i).compareTo(arrayList.get(j)) <= 0) {
                arrayList.set(k++, temp.get(i++));
            } else {
                arrayList.set(k++, arrayList.get(j++));
            }
        }

        while (i < n1) {
            arrayList.set(k++, temp.get(i++));
        }
    }

    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList, int left, int right) {
        for (int i = left+1; i <= right; i++) {
            T currentIndex = arrayList.get(i);
            int j = i-1;
            while (j >= left && arrayList.get(j).compareTo(currentIndex) > 0) {
                arrayList.set(j+1, arrayList.get(j));
                j--;
            }
            arrayList.set(j+1, currentIndex);
        }
    }

    private static <T extends Comparable<? super T>> void quickSort(ArrayList<T> arrayList, int low, int high) {

    }
}
