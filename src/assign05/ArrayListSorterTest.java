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

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            smallIntArrayList.add(rand.nextInt(100));
        }
    }

    @Test
    public void smallIntArrayListMergeSortTest() {
        mergeSort(smallIntArrayList);
        for (int i = 0; i < smallIntArrayList.size()-1; i++) {
            assertFalse("ArrayList not in order", smallIntArrayList.get(i) > smallIntArrayList.get(i+1));
        }
    }

    @Test
    public void generateAscendingTest() {
        ArrayList<Integer> arrayList = generateAscending(20);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue( arrayList.get(i) == i+1);
        }
    }

    @Test
    public void generateDescendingTest() {
        ArrayList<Integer> arrayList = generateDescending(20);
        for (int i = 0; i < arrayList.size(); i++) {
            assertTrue(arrayList.get(i) == arrayList.size()-i);
        }
    }
}
