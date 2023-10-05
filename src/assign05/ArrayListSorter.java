package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class holding static methods for mergesort and quicksort.
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-10-05
 */
public class ArrayListSorter {

    private static final int INSERTION_SORT_THRESHOLD = 6; // 7 or greater and JDK uses mergesort over insertion sort

    /**
     * Driver method for mergesort.
     *
     * @param arrayList - arraylist to be sorted
     * @param <T> - generic
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList) {
        ArrayList<T> temp = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            temp.add(null); // Pre-allocate space for merging
        }
        mergesort(arrayList, temp, 0, arrayList.size() - 1); //start recursive method
    }

    /**
     * Driver method for quicksort.
     *
     * @param arrayList - arraylist to be sorted
     * @param <T> - generic
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList) {
        quicksort(arrayList, 0, arrayList.size()-1); //start recursive method
    }

    /**
     * Generates and returns an arraylist in ascending order.
     *
     * @param size - size of the arraylist
     * @return - an arraylist in ascending order
     */
    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size); //creates an arrayList of int size
        for (int i = 0; i < size; i++) {
            arrayList.add(i, i+1); //arrayList = {1, 2, 3, ..., int size};
        }
        return arrayList;
    }

    /**
     * Generates and returns an arraylist in permuted (random) order.
     *
     * @param size - size of the arraylist
     * @return - an arraylist in permuted order.
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size); //creates an arrayList of int size
        for (int i = 0; i < size; i++) {
            arrayList.add(i, i+1); //arrayList = {1, 2, 3, ..., int size};
        }
        Collections.shuffle(arrayList); //randomizes the values in the arrayList
        return arrayList;
    }

    /**
     * Generates and returns an arraylist in descending order.
     *
     * @param size - size of the arraylist
     * @return - an arraylist in descending order
     */
    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<>(size); //creates an arrayList of int size
        for (int i = size; i > 0; i--) {
            arrayList.add(size-i, i); //arrayList = {int size, ..., 3, 2, 1};
        }
        return arrayList;
    }

    //HELPER METHODS

    /**
     * The recursive method for mergesort. Calls insertion sort when appropriate for runtime optimization.
     *
     * @param arrayList - arraylist to be sorted
     * @param temp - pre-allocated arraylist
     * @param left - left index of the array
     * @param right - right index of the array
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList, ArrayList<T> temp, int left, int right) {
        if (right - left <= INSERTION_SORT_THRESHOLD) { //if the subarray is <= INSERTION_SORT_THRESHOLD, use insertion sort to sort
            insertionSort(arrayList, left, right);
        } else {
            int mid = (left + right) / 2; //find mid to split the array
            mergesort(arrayList, temp, left, mid); //recursive call to get to subarray.length <= INSERTION_SORT_THRESHOLD
            mergesort(arrayList, temp, mid + 1, right); //recursive call to get to subarray.length <= INSERTION_SORT_THRESHOLD
            merge(arrayList, temp, left, mid, right); //merges the subarrays together
        }
    }

    /**
     * Merges the subarrays to an array (still inside the main arrayList).
     *
     * @param arrayList - arraylist to be sorted
     * @param temp - pre-allocated arraylist
     * @param mid - middle index of the array
     * @param left - left index of the array
     * @param right - right index of the array
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arrayList, ArrayList<T> temp, int left, int mid, int right) {
        int length = mid - left + 1; //length of subarrays

        for (int i = 0; i < length; i++) {
            temp.set(i, arrayList.get(left + i));
        }

        int i = 0; //while loop counter, also subarray cursor
        int j = mid + 1; //while loop counter, also subarray cursor
        int k = left; //array cursor

        while (i < length && j <= right) { //while inside indexes of the subarrays
            if (temp.get(i).compareTo(arrayList.get(j)) <= 0) { //if temp.get(i) <= arrayList.get(j)
                arrayList.set(k, temp.get(i)); //set temp.get(i) to arrayList.get(k)
                i++;
            } else {
                arrayList.set(k, arrayList.get(j)); //set arrayList.get(j) to arrayList.get(k)
                j++;
            }
            k++;
        }

        while (i < length) { //while loop to finish adding values from subarray
            arrayList.set(k++, temp.get(i++));
        }
    }

    /**
     * A basic insertion sort method. Called when appropriate for runtime optimization in mergesort and quicksort.
     *
     * @param arrayList - arraylist to be sorted
     * @param left - left index of the array
     * @param right - right index of the array
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList, int left, int right) {
        for (int i = left+1; i <= right; i++) { //while inbounds of the subarray (setting i to index 1 of the subarray to start the loop)
            T currentIndex = arrayList.get(i); //current index checking in subarray
            int j = i-1; //index below i
            while (j >= left && arrayList.get(j).compareTo(currentIndex) > 0) { //while j is greater than index -1 of the subarray AND arraylist.get(j) > currentIndex
                arrayList.set(j+1, arrayList.get(j)); //swap value to new index
                j--;
            }
            arrayList.set(j+1, currentIndex); //swap value to new index
        }
    }

    /**
     * The recursive method for quicksort. Calls insertion sort when appropriate for runtime optimization.
     *
     * @param arrayList - arraylist to be sorted
     * @param low - left index of the array
     * @param high - right index of the array
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList, int low, int high) {
        if (low < high && high - low > INSERTION_SORT_THRESHOLD) { //if not done with the subarray (done with array for first and final call)
            int partitionIndex = partition(arrayList, low, high); //find the partition index

            quicksort(arrayList, low, partitionIndex - 1); //recursive call to quick sort
            quicksort(arrayList, partitionIndex, high); //recursive call to quick sort
        } else {
            insertionSort(arrayList, low, high);
        }
    }

    /**
     * The partitioning method where the sorting and pivot selection happen in quicksort.
     *
     * @param arrayList - arraylist to be sorted
     * @param low - left index of the array
     * @param high - right index of the array
     * @return - the new left index of the array
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arrayList, int low, int high) {
        T pivot = pivotRandom(arrayList, low, high); // CHANGE PIVOT METHOD HERE
        while (high >= low) {
            while (arrayList.get(low).compareTo(pivot) < 0) { //if arrayList.get(low) < pivot
                low++;
            }
            while (pivot.compareTo(arrayList.get(high)) < 0) { //if pivot < arrayList.get(high)
                high--;
            }
            if (high >= low) {
                swap(arrayList, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    /**
     * Picks a random value from the arrayList to be used as the pivot. Finds the middle index of the three random
     * indexes generated, then returns the value in the arrayList at that middle index to be used as the pivot.
     *
     * @param arrayList - arraylist to be sorted
     * @param low - left index of the array
     * @param high - right index of the array
     * @return - the pivot
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> T pivotRandom(ArrayList<T> arrayList, int low, int high) {
        Random rand = new Random();
        int randomIndex1 = rand.nextInt(high - low + 1) + low;
        int randomIndex2 = rand.nextInt(high - low + 1) + low;
        int randomIndex3 = rand.nextInt(high - low + 1) + low;

        if ((randomIndex1 < randomIndex2 && randomIndex2 < randomIndex3) || (randomIndex3 < randomIndex2 && randomIndex2 < randomIndex1)) { //checking for randomIndex2
            return arrayList.get(randomIndex2);
        } else if ((randomIndex2 < randomIndex1 && randomIndex1 < randomIndex3) || (randomIndex3 < randomIndex1 && randomIndex1 < randomIndex2)) { //checking for randomIndex1
            return arrayList.get(randomIndex1);
        } else {
            return arrayList.get(randomIndex3);
        }
    }

    /**
     * Picks the pivot by using the "median of three" method.
     *
     * @param arrayList - arraylist to be sorted
     * @param low - left index of the array
     * @param high - right index of the array
     * @return - the pivot
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> T pivotMedian(ArrayList<T> arrayList, int low, int high) {
        int mid = low + (high - low) / 2; //middle index of an array from int low to int high
        T first = arrayList.get(low); //gets low index value
        T middle = arrayList.get(mid); //gets mid index value
        T last = arrayList.get(high); //gets high index value

        T pivot;

        if (first.compareTo(middle) < 0) { //if first < middle
            if (middle.compareTo(last) < 0) { //if middle < last
                pivot = middle; //set pivot to middle
            } else if (first.compareTo(last) < 0) { //if first < last
                pivot = last; //set pivot to last
            } else { //if first < middle
                pivot = first; //set pivot to first
            }
        } else { //if middle < first
            if (first.compareTo(last) < 0) { //if first < last
                pivot = first; //set pivot to first
            } else if (middle.compareTo(last) < 0) { //if middle < last
                pivot = last; //set pivot to last
            } else { //if middle < first
                pivot = middle; //set pivot to middle
            }
        }

        return pivot;
    }

    /**
     * Picks the first element in the array as the pivot.
     *
     * @param arrayList - arraylist to be sorted
     * @param low - left index of the array
     * @return - the pivot
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> T pivotFirstElement(ArrayList<T> arrayList, int low) {
        return arrayList.get(low);
    }

    /**
     * Swaps values at two given indexes of an arraylist.
     *
     * @param arrayList - arraylist
     * @param i - first value to be swapped
     * @param j - second value to be swapped
     * @param <T> - generic
     */
    private static <T extends Comparable<? super T>> void swap(ArrayList<T> arrayList, int i, int j) {
        T temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }
}

