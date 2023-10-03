package StudyingMaterials;

import java.util.Comparator;

/**
 * INSERTION SORT
 * Best: O(N) (when already sorted)
 * Average: O(N^2)
 * Worst: O(N^2) (when in reverse order)
 * General complexity: O(N+I) (I = number of inversions)
 */
public class InsertionSort {
    public <T> void insertionSort(T[] arr) {
        Comparator cmp = Comparator.naturalOrder();
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && cmp.compare(arr[j], arr[j-1]) < 0; j--) {
                T temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }
}
