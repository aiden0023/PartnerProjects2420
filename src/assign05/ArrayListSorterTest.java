package assign05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Random;

import static assign05.ArrayListSorter.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class holding tests for the class ArrayListSorter.
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-10-05
 */
public class ArrayListSorterTest {

    public ArrayList<Integer> smallIntArrayList = new ArrayList<>();
    public ArrayList<Integer> mediumIntArrayList = new ArrayList<>();
    public ArrayList<Integer> largeIntArrayList = new ArrayList<>();
    public ArrayList<String> smallStringArrayList = new ArrayList<>();
    public ArrayList<String> mediumStringArrayList = new ArrayList<>();
    public ArrayList<String> largeStringArrayList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            smallIntArrayList.add(rand.nextInt(100));
            smallStringArrayList.add(Integer.toString(rand.nextInt(100)));
        }
        for (int i = 0; i < 100; i++) {
            mediumIntArrayList.add(rand.nextInt(500));
            mediumStringArrayList.add(Integer.toString(rand.nextInt(500)));
        }
        for (int i = 0; i < 500; i++) {
            largeIntArrayList.add(rand.nextInt(1000));
            mediumStringArrayList.add(Integer.toString(rand.nextInt(1000)));
        }
    }

    @Test
    public void smallIntArrayListMergeSortTest() {
        mergesort(smallIntArrayList);
        for (int i = 0; i < smallIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", smallIntArrayList.get(i) > smallIntArrayList.get(i+1));
        }
    }

    @Test
    public void mediumIntArrayListMergeSortTest() {
        mergesort(mediumIntArrayList);
        for (int i = 0; i < mediumIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", mediumIntArrayList.get(i) > mediumIntArrayList.get(i+1));
        }
    }

    @Test
    public void largeIntArrayListMergeSortTest() {
        mergesort(largeIntArrayList);
        for (int i = 0; i < largeIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", largeIntArrayList.get(i) > largeIntArrayList.get(i+1));
        }
    }

    @Test
    public void smallStringArrayListMergeSortTest() {
        mergesort(smallStringArrayList);
        for (int i = 0; i < smallStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", smallStringArrayList.get(i).compareTo(smallStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void mediumStringArrayListMergeSortTest() {
        mergesort(mediumStringArrayList);
        for (int i = 0; i < mediumStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", mediumStringArrayList.get(i).compareTo(mediumStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void largeStringArrayListMergeSortTest() {
        mergesort(largeStringArrayList);
        for (int i = 0; i < largeStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", largeStringArrayList.get(i).compareTo(largeStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void smallIntArrayListQuickSortTest() {
        quicksort(smallIntArrayList);
        for (int i = 0; i < smallIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", smallIntArrayList.get(i) > smallIntArrayList.get(i+1));
        }
    }

    @Test
    public void mediumIntArrayListQuickSortTest() {
        quicksort(mediumIntArrayList);
        for (int i = 0; i < mediumIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", mediumIntArrayList.get(i) > mediumIntArrayList.get(i+1));
        }
    }

    @Test
    public void largeIntArrayListQuickSortTest() {
        quicksort(largeIntArrayList);
        for (int i = 0; i < largeIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", largeIntArrayList.get(i) > largeIntArrayList.get(i+1));
        }
    }

    @Test
    public void smallStringArrayListQuickSortTest() {
        quicksort(smallStringArrayList);
        for (int i = 0; i < smallStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", smallStringArrayList.get(i).compareTo(smallStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void mediumStringArrayListQuickSortTest() {
        quicksort(mediumStringArrayList);
        for (int i = 0; i < mediumStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", mediumStringArrayList.get(i).compareTo(mediumStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void largeStringArrayListQuickSortTest() {
        quicksort(largeStringArrayList);
        for (int i = 0; i < largeStringArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", largeStringArrayList.get(i).compareTo(largeStringArrayList.get(i+1)) > 0);
        }
    }

    @Test
    public void generateAscendingSmallTest() {
        ArrayList<Integer> arrayList = generateAscending(20);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue( arrayList.get(i) == i+1);
        }
    }

    @Test
    public void generateAscendingMediumTest() {
        ArrayList<Integer> arrayList = generateAscending(100);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue( arrayList.get(i) == i+1);
        }
    }

    @Test
    public void generateAscendingLargeTest() {
        ArrayList<Integer> arrayList = generateAscending(500);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue( arrayList.get(i) == i+1);
        }
    }

    @Test
    public void generatePermutedSmallTest() {
        ArrayList<Integer> firstList = generatePermuted(20);
        ArrayList<Integer> secondList = generatePermuted(20);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void generatePermutedMediumTest() {
        ArrayList<Integer> firstList = generatePermuted(100);
        ArrayList<Integer> secondList = generatePermuted(100);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void generatePermutedLargeTest() {
        ArrayList<Integer> firstList = generatePermuted(500);
        ArrayList<Integer> secondList = generatePermuted(500);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void generateDescendingSmallTest() {
        ArrayList<Integer> arrayList = generateDescending(20);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue(arrayList.get(i) == arrayList.size()-i);
        }
    }

    @Test
    public void generateDescendingMediumTest() {
        ArrayList<Integer> arrayList = generateDescending(100);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue(arrayList.get(i) == arrayList.size()-i);
        }
    }

    @Test
    public void generateDescendingLargeTest() {
        ArrayList<Integer> arrayList = generateDescending(500);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue(arrayList.get(i) == arrayList.size()-i);
        }
    }
}
