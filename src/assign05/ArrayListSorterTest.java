package assign05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Random;

import static assign05.ArrayListSorter.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListSorterTest {

    public ArrayList<Integer> smallIntArrayList = new ArrayList<>();
    public ArrayList<Integer> mediumIntArrayList = new ArrayList<>();
    public ArrayList<Integer> largeIntArrayList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            smallIntArrayList.add(rand.nextInt(100));
        }
        for (int i = 0; i < 100; i++) {
            mediumIntArrayList.add(rand.nextInt(500));
        }
        for (int i = 0; i < 500; i++) {
            largeIntArrayList.add(rand.nextInt(1000));
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
    public void generatePermutedTest() {

    }

    @Test
    public void generateDescendingTest() {
        ArrayList<Integer> arrayList = generateDescending(20);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue(arrayList.get(i) == arrayList.size()-i);
        }
    }
}
