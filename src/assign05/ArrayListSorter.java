package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {

    private static final int INSERTION_SORT_THRESHOLD = 6; // 7 or greater and JDK uses mergesort over insertion sort

    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList) {
        ArrayList<T> temp = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            temp.add(null); // Pre-allocate space for merging
        }
        mergesort(arrayList, temp, 0, arrayList.size() - 1);
    }


    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList) {
        quicksort(arrayList, 0, arrayList.size()-1);
    }

    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i, i+1);
        }
        return arrayList;
    }

    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i, i+1);
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }

    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size);
        for (int i = size; i > 0; i--) {
            arrayList.add(size-i, i);
        }
        return arrayList;
    }

    //HELPER METHODS

    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList, ArrayList<T> temp, int left, int right) {
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arrayList, left, right);
        } else {
            int mid = (left + right) / 2;
            mergesort(arrayList, temp, left, mid);
            mergesort(arrayList, temp, mid + 1, right);
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

    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arrayList, low, high);
            quicksort(arrayList, low, partitionIndex-1);
            quicksort(arrayList, partitionIndex, high);
        }
    }

    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arrayList, int low, int high) {
        T pivot = pivotRandom(arrayList); // THIS IS WHERE YOU SWITCH THE PIVOT METHOD

        while (high >= low) {
            while (arrayList.get(low).compareTo(pivot) < 0) {
                low++;
            }
            while (pivot.compareTo(arrayList.get(high)) < 0) {
                high++;
            }

            if (high >= low) {
                swap(arrayList, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    private static <T extends Comparable<? super T>> T pivotRandom(ArrayList<T> arrayList) {
        Random rand = new Random();
        return arrayList.get(rand.nextInt(arrayList.size()-1));
    }

    private static <T extends Comparable<? super T>> T pivotMedian(ArrayList<T> arraylist, int listSize) {
        return null;
    }

    private static <T extends Comparable<? super T>> T pivotFirstElement(ArrayList<T> arrayList) {
        ArrayList arr = new ArrayList();
        return arrayList.get(arrayList.size() - arrayList.size()-1);
    }

    private static <T extends Comparable<? super T>> void swap(ArrayList<T> arrayList, int i, int j) {
        T temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }
}

