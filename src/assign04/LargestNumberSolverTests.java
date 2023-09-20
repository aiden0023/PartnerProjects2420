package assign04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static assign04.LargestNumberSolver.*;
import static org.junit.Assert.*;

public class LargestNumberSolverTests {

    private Integer[] randSmallIntArr = new Integer[5];
    private Integer[] randMediumIntArr = new Integer[30];
    private Integer[] randLargeIntArr = new Integer[100];
    private Integer[] outOfRangeIntArr = new Integer[11];
    private Integer[] outOfRangeLongArr = new Integer[20];
    private Integer[] smallIntArr = new Integer[]{5, 7, 1, 2, 91};
    private ArrayList<Integer[]> smallListOfIntArr = new ArrayList<>();
    private ArrayList<Integer[]> mediumListOfIntArr = new ArrayList<>();
    private ArrayList<Integer[]> largeListOfIntArr = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Random rand = new Random();

        //Setting up randSmallIntArr with randomized numbers
        for (int i = 0; i < randSmallIntArr.length; i++) {
            randSmallIntArr[i] = rand.nextInt(randSmallIntArr.length*2);
        }

        //Setting up randMediumIntArr with randomized numbers
        for (int i = 0; i < randMediumIntArr.length; i++) {
            randMediumIntArr[i] = rand.nextInt(randMediumIntArr.length*2);
        }

        //Setting up randLargeIntArr with randomized numbers
        for (int i = 0; i < randLargeIntArr.length; i++) {
            randLargeIntArr[i] = rand.nextInt(randLargeIntArr.length*2);
        }

        //Setting up outOfRangeIntArr with randomized numbers
        for (int i = 0; i < outOfRangeIntArr.length; i++) {
            outOfRangeIntArr[i] = rand.nextInt(9);
        }

        //Setting up outOfRangeLongArr with randomized numbers
        for (int i = 0; i < outOfRangeLongArr.length; i++) {
            outOfRangeLongArr[i] = rand.nextInt(9);
        }

        //Setting up smallListOfIntArr
        smallListOfIntArr.add(new Integer[]{1, 2, 10});
        smallListOfIntArr.add(new Integer[]{45, 20, 10});

        //Setting up mediumListOfIntArr
        mediumListOfIntArr.add(new Integer[]{23, 4, 5, 7});
        mediumListOfIntArr.add(new Integer[]{5, 3, 12, 84});
        mediumListOfIntArr.add(new Integer[]{35, 12, 4, 13});
        mediumListOfIntArr.add(new Integer[]{7, 14, 82, 9});
        mediumListOfIntArr.add(new Integer[]{6, 53, 23, 52});

        //Setting up largeListOfIntArr
        largeListOfIntArr.add(new Integer[]{93, 27, 5, 9, 74});
        largeListOfIntArr.add(new Integer[]{72, 5, 41, 8, 2});
        largeListOfIntArr.add(new Integer[]{83, 72, 4, 3, 9});
        largeListOfIntArr.add(new Integer[]{17, 18, 19, 20, 1});
        largeListOfIntArr.add(new Integer[]{30, 41, 2, 31, 4});
        largeListOfIntArr.add(new Integer[]{71, 49, 5, 9, 74});
        largeListOfIntArr.add(new Integer[]{51, 1, 1, 2, 7});
        largeListOfIntArr.add(new Integer[]{9, 8, 7, 6, 5});
        largeListOfIntArr.add(new Integer[]{123, 2, 1, 1, 10});
        largeListOfIntArr.add(new Integer[]{1, 40, 30, 20, 74});
    }

    //insertionSort() tests
    @Test
    public void randSmallInsertionSortTest() {
        insertionSort(randSmallIntArr, Comparator.naturalOrder());
        for (int i = 0; i < randSmallIntArr.length-1; i++) {
            assertFalse("Array not in order", randSmallIntArr[i] > randSmallIntArr[i+1]);
        }
    }

    @Test
    public void randMediumInsertionSortTest() {
        insertionSort(randMediumIntArr, Comparator.naturalOrder());
        for (int i = 0; i < randMediumIntArr.length-1; i++) {
            assertFalse("Array not in order", randMediumIntArr[i] > randMediumIntArr[i+1]);
        }
    }

    @Test
    public void randLargeInsertionSortTest() {
        insertionSort(randLargeIntArr, Comparator.naturalOrder());
        for (int i = 0; i < randLargeIntArr.length-1; i++) {
            assertFalse("Array not in order", randLargeIntArr[i] > randLargeIntArr[i+1]);
        }
    }

    //findLargestNumber() tests
    @Test
    public void smallFindLargestNumberTest() {
        assertEquals(BigInteger.valueOf(917521), findLargestNumber(smallIntArr));
    }

    @Test
    public void mediumFindLargestNumberTest() {

    }

    @Test
    public void largeFindLargestNumberTest() {

    }

    //findLargestInt() tests
    @Test
    public void findLargestIntExceptionTest() {
        assertThrows(OutOfRangeException.class, () -> {
            findLargestInt(outOfRangeIntArr);
        });
    }

    @Test
    public void smallFindLargestIntTest() {
        assertEquals(917521, findLargestInt(smallIntArr));
    }

    @Test
    public void mediumFindLargestIntTest() {

    }

    @Test
    public void largeFindLargestIntTest() {

    }

    //findLargestLong() tests
    @Test
    public void findLargestLongExceptionTest() {
        assertThrows(OutOfRangeException.class, () -> {
            findLargestLong(outOfRangeLongArr);
        });
    }

    @Test
    public void smallFindLargestLongTest() {
        assertEquals(917521, findLargestLong(smallIntArr));
    }

    @Test
    public void mediumFindLargestLongTest() {

    }

    @Test
    public void largeFindLargestLongTest() {

    }

    //sum() tests
    @Test
    public void smallFindSumTest() {
        assertEquals(BigInteger.valueOf(454120), sum(smallListOfIntArr));
    }

    @Test
    public void mediumFindSumTest() {

    }

    @Test
    public void largeFindSumTest() {

    }

    //findKthLargest() tests
    @Test
    public void findKthLargestExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            findKthLargest(smallListOfIntArr, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            findKthLargest(smallListOfIntArr, smallListOfIntArr.size()+1);
        });
    }

    @Test
    public void smallFindKthLargestTest() {
        assertEquals(smallListOfIntArr.get(0), findKthLargest(smallListOfIntArr, 1));
    }

    @Test
    public void mediumFindKthLargestTest() {
        assertEquals(mediumListOfIntArr.get(2), findKthLargest(mediumListOfIntArr, 1));
    }

    @Test
    public void largeFindKthLargestTest() {

    }

    //readFile() tests
    @Test
    public void readFileExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            readFile("foo");
        });
    }

    @Test
    public void readFileTest() {

    }
}
