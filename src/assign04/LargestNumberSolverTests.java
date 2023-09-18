package assign04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;
import java.util.Random;

import static assign04.LargestNumberSolver.insertionSort;

public class LargestNumberSolverTests {

    private Integer[] randSmallIntArr = new Integer[5];

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
        //FINISH TEST LOGIC
    }
}
