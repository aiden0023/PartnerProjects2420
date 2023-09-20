package assign03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Tests for the SimplePriorityQueue class
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-09-07
 */

public class SimplePriorityQueueTests {

    Comparator<String> compString = Comparator.comparing(String::toString);
    private SimplePriorityQueue<Integer> simplePriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<Integer> intPriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<String> stringPriorityQueue = new SimplePriorityQueue<>(compString);
    ArrayList<Integer> intColl = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        Integer[] intArray = new Integer[20];
        intArray[19] = 4;
        intArray[18] = 2;
        intArray[17] = 1;
        intPriorityQueue.setQueue(intArray);
        intPriorityQueue.setSize(3);


        String[] stringArray = new String[20];
        stringArray[19] = "apple";
        stringArray[18] = "cat";
        stringArray[17] = "hello";
        stringArray[16] = "world";
        stringArray[15] = "zoo";

        stringPriorityQueue.setQueue(stringArray);
        stringPriorityQueue.setSize(5);

        intColl.add(1);
        intColl.add(2);
        intColl.add(3);
    }

    @Test
    public void testEmptyFindMax(){
        simplePriorityQueue.insert(5);
        assertEquals(5, (int) simplePriorityQueue.findMax());
    }

    @Test
    public void testEmptyIsEmpty() {
        Assertions.assertTrue(simplePriorityQueue.isEmpty());
        simplePriorityQueue.insert(5);
        assertFalse(simplePriorityQueue.isEmpty());
    }

    @Test
    public void testEmptyInsert() {
        simplePriorityQueue.insert(7);
        Object[] temp = simplePriorityQueue.getQueue();
        assertEquals(7, temp[19]);
    }

    @Test
    public void testEmptyInsertAll() {
        simplePriorityQueue.insertAll(intColl);
        assertArrayEquals(simplePriorityQueue.getQueue(), intColl.toArray());
    }

    @Test
    public void testEmptyContains() {
        assertFalse(simplePriorityQueue.contains(2));
    }

    @Test
    public void testEmptySize() {
    simplePriorityQueue.size();
    }

    @Test
    public void testEmptyClear() {
        simplePriorityQueue.clear();
    }


   // int tests
   @Test
   public void testIntFindMax(){
       assertEquals(4, (int) intPriorityQueue.findMax());
   }

    @Test
    public void testIntIsEmpty() {
        assertFalse(intPriorityQueue.isEmpty());
    }

    @Test
    public void testIntInsert() {
        intPriorityQueue.insert(3);
        Object[] temp = intPriorityQueue.getQueue();
        assertEquals(3, (int) temp[18]);
    }

    @Test
    public void testIntContains() {
        assertTrue(intPriorityQueue.contains(2));
    }

    @Test
    public void testIntSize() {
        intPriorityQueue.size();
    }

    @Test
    public void testIntClear() {
        intPriorityQueue.clear();
    }


    //String tests
    @Test
    public void testStringFindMax(){
        assertEquals("apple", stringPriorityQueue.findMax());
    }

    @Test
    public void testStringIsEmpty() {
        assertFalse(stringPriorityQueue.isEmpty());
    }

    @Test
    public void testStringInsert() {
        stringPriorityQueue.insert("dad");
        Object[] temp = stringPriorityQueue.getQueue();
        assertEquals("dad", temp[17]);
    }

    @Test
    public void testStringContains() {
        assertTrue(stringPriorityQueue.contains("world"));
    }

    @Test
    public void testStringSize() {
        stringPriorityQueue.size();
    }

    @Test
    public void testStringClear() {
        stringPriorityQueue.clear();
    }
}
