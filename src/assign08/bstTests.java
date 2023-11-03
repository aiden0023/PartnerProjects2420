package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class bstTests {

    public BinarySearchTree<Integer> smallBST = new BinarySearchTree<>();
    public BinarySearchTree<Integer> emptyBST = new BinarySearchTree<>();
    public BinarySearchTree<Integer> twoBST = new BinarySearchTree<>();
    public BinarySearchTree<Integer> tenBST = new BinarySearchTree<>();
    public ArrayList<Integer> smallBSTList = new ArrayList<>();
    public ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> tenBSTList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        smallBST.add(18);
        smallBST.add(7);
        smallBST.add(20);
        smallBST.add(5);
        smallBST.add(27);
        smallBST.add(16);

        twoBST.add(1);
        twoBST.add(2);

        for (int i = 1; i < 11; i++) {
            tenBST.add(i);
        }

        for (int i = 1; i < 5; i++) {
            tenBSTList.add(i);
        }

        smallBSTList.add(18);
        smallBSTList.add(7);
        smallBSTList.add(20);
        smallBSTList.add(5);
        smallBSTList.add(27);
        smallBSTList.add(16);

        list.add(35);
        list.add(1);
        list.add(4);
        list.add(29);
    }

    @Test
    public void addTest() {
        smallBST.add(50);
        assertTrue(smallBST.contains(50));
        assertEquals(7, smallBST.size());
    }

    @Test
    public void addAllTest() {
        smallBST.addAll(list);
        assertTrue(smallBST.containsAll(list));
    }

    @Test
    public void clearTest() {
        smallBST.clear();
        assertTrue(smallBST.isEmpty());
    }

    @Test
    public void clearThenRemoveTest() {
        tenBST.clear();
        assertTrue(tenBST.isEmpty());
        ArrayList<Integer> temp = tenBST.toArrayList();
        assertEquals(0, temp.size());
        assertFalse(tenBST.remove(5));
    }

    @Test
    public void containsTest() {
        assertTrue(smallBST.contains(5));
    }

    @Test
    public void containsAllTest() {
        assertTrue(smallBST.containsAll(smallBSTList));
    }

    @Test
    public void firstTest() {
        assertEquals(5, smallBST.first());
    }

    @Test
    public void isEmptyTest() {
        assertTrue(emptyBST.isEmpty());
    }

    @Test
    public void isEmpty2SizeTest() {
        assertFalse(twoBST.isEmpty());
        twoBST.remove(1);
        twoBST.remove(2);
        assertTrue(twoBST.isEmpty());
    }

    @Test
    public void lastTest() {
        assertEquals(27, smallBST.last());
    }

    @Test
    public void removeTest() {
        smallBST.remove(20);
        assertFalse(smallBST.contains(20));
        assertEquals(5, smallBST.size());
    }

    @Test
    public void removeAllTest() {
        smallBST.addAll(list);
        smallBST.removeAll(list);
        assertFalse(smallBST.containsAll(list));
    }

    @Test
    public void removeAllTenTest() {
        tenBST.removeAll(tenBSTList);
        assertEquals(6, tenBST.size());
        assertFalse(tenBST.contains(1));
        assertFalse(tenBST.containsAll(tenBSTList));
        assertEquals(5, tenBST.first());
        assertEquals(10, tenBST.last());
    }

    @Test
    public void sizeTest() {
        assertEquals(6, smallBST.size());
    }

    @Test
    public void toArrayTest() {
        ArrayList<Integer> testList = smallBST.toArrayList();
        smallBSTList.sort(Integer::compareTo);
        for (int i = 0; i < smallBSTList.size(); i++) {
            assertEquals(smallBSTList.get(i), testList.get(i));
        }
    }
}
