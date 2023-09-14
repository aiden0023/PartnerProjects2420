package assign03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import assign03.SimplePriorityQueue;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimplePriorityQueueTests {

    private SimplePriorityQueue<Integer> simplePriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<Integer> intPriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<String> stringPriorityQueue = new SimplePriorityQueue<>();


    @BeforeEach
    public void setUp() {
        Integer[] intArray = new Integer[20];
        intArray[19] = 4;
        intArray[18] = 2;
        intArray[17] = 1;
        intPriorityQueue.setQueue(intArray);
        intPriorityQueue.setSize(3);


        String[] stringArray = new String[10];
        stringArray[9] = "apple";
        stringArray[8] = "cat";
        stringArray[7] = "hello";
        stringArray[6] = "world";
        stringArray[5] = "zoo";

        stringPriorityQueue.setQueue(stringArray);
        stringPriorityQueue.setSize(5);
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

    }

    @Test
    public void testEmptyInsertAll() {

    }

    @Test
    public void testEmptyContains() {
        assertTrue(simplePriorityQueue.contains(2));
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
        Integer[] temp = intPriorityQueue.getQueue();
        assertEquals(3, (int) temp[18]);
    }
    @Test
    public void testIntInsertAll() {

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
        stringPriorityQueue.insert("water");
        //assertArrayEquals();
    }

    @Test
    public void testStringInsertAll() {

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
