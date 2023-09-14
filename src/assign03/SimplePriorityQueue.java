package assign03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {

    private E[] queue;
    private int size;
    private Comparator<? super E> cmp;

    public SimplePriorityQueue() {
        queue = (E[]) new Object[20];
        size = 0;
        cmp = null;
    }

    public SimplePriorityQueue(Comparator<? super E> cmp) {
        queue = (E[]) new Object[20];
        size = 0;
        this.cmp = cmp;
    }

    @Override
    public E findMax() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return queue[queue.length-1];
        }
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            E tempMax = queue[queue.length - 1];
            size--;
            this.advanceQueue();
            return tempMax;
        }
    }

    @Override
    public void insert(E item) {
        if (size == queue.length) {
            this.enlargeQueue();
        }
        if (queue[queue.length-1] == null) {
            queue[queue.length-1] = item;
        }

        //add binary search to look for same value

        int index = 0;
        for (int i = 0; i < queue.length; i++) {
            int temp = compare(item, queue[i]);
            if (temp == -1 || temp == 0) {
                index = i-1;
            }
        }

        //make a temp array in order to move int index down to make room to insert item
        E[] tempArray = (E[]) new Object[queue.length];
        for (int i = index; i < 0; i--) {
            if (queue[i] == null) {
                break;
            }
            tempArray[i-1] = queue[i];
        }
        queue = tempArray;
        queue[index] = item;
        size++;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        queue = (E[]) coll.toArray();
    }

    @Override
    public boolean contains(E item) {
        return !(binarySearch(item, 0, queue.length-1) == -1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        queue = (E[]) new Object[20];
        size = 0;
    }

    private int binarySearch(E target, int low, int high) { //FIX LATER
        if (low < high) {
            int mid = (low/2) + (high/2);
            int cmpOutput = compare(queue[mid], target);
            if (cmpOutput < 0) {
                return binarySearch(target, low, mid+1);
            } else if (cmpOutput > 0) {
                return binarySearch(target, mid+1, high);
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int compare(E o1, E o2) {
        if (cmp == null) {
            return ((Comparable<? super E>) o1).compareTo(o2);
        } else {
            return cmp.compare(o1, o2);
        }
    }

    private void advanceQueue() {
        E[] tempArray = (E[]) new Object[queue.length];
        for (int i = 0; i < queue.length; i++) {
            tempArray[i+1] = queue[i];
        }
        queue = tempArray;
    }

    private void enlargeQueue() {
        E[] tempArray = (E[]) new Object[queue.length*2];
        int j = 0;
        for (int i = queue.length; i < 0; i--) {
            tempArray[tempArray.length-j] = queue[i];
            j++;
        }
        queue = tempArray;
    }
}
