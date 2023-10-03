package StudyingMaterials;

import java.util.Comparator;

/**
 * SELECTION SORT
 * Best: O(N^2)
 * Average: O(N^2)
 * Worst: O(N^2)
 */
public class SelectionSort {
    public static <T> void selectionSort(T[] arr) {
        Comparator cmp = Comparator.naturalOrder();
        int minIndex = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (cmp.compare(arr[minIndex], arr[j]) > 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
