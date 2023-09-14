package assign03;

import org.junit.jupiter.api.Test;
import assign03.SimplePriorityQueue;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PriorityQueueTests {


    private SimplePriorityQueue<Integer> priorityQueue;

//    @BeforeEach
//    void setUp() throws Exception {
//        priorityQueue = new SimplePriorityQueue<>();
//    }


    @Test
    public void testFindMax(){
        assertNull(priorityQueue.findMax());
        priorityQueue.insert(5);
        assertEquals(5, priorityQueue.findMax());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.insert(5);
        assertFalse(priorityQueue.isEmpty());
    }
}
