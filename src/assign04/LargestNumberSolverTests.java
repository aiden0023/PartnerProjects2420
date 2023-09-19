package assign04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Random;

import static assign04.LargestNumberSolver.findLargestNumber;
import static assign04.LargestNumberSolver.insertionSort;
import static org.junit.Assert.*;

public class LargestNumberSolverTests {

    private Integer[] randSmallIntArr = new Integer[5];
    private Integer[] smallIntArr = new Integer[]{5, 7, 1, 2, 11};

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        //Setting up smallIntArr with randomized numbers
        for (int i = 0; i < randSmallIntArr.length; i++) {
            randSmallIntArr[i] = rand.nextInt(randSmallIntArr.length*2);
        }
    }

    @Test
    public void testRandSmallInsertSort() {
        insertionSort(randSmallIntArr, Comparator.naturalOrder());
        for (int i = 0; i < randSmallIntArr.length-1; i++) {
            assertFalse("Array not in order", randSmallIntArr[i] > randSmallIntArr[i+1]);
        }
    }

    @Test
    public void smallFindLargestNumber() {
        findLargestNumber(smallIntArr);
        assertEquals(BigInteger.valueOf(752111) ,findLargestNumber(smallIntArr));
    }
}
