package assign05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Random;

import static assign05.ArrayListSorter.mergeSort;
import static org.junit.Assert.assertFalse;

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
}
