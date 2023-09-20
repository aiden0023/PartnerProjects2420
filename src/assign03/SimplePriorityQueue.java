package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A simple priority queue that supports access of the maximum element only.
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-09-07
 *
 * @param <E> - the type of elements contained in this priority queue
 */
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
        if (this.isEmpty()) { //run empty check
            throw new NoSuchElementException();
        } else {
            return queue[queue.length-1]; //max is at last index
        }
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if (this.isEmpty()) { //run empty check
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
        if (size == queue.length) { //checks for if queue needs to be enlarged
            this.enlargeQueue();
        }

        if (queue[queue.length-1] == null) { //if last index is null, insert item at last index
            queue[queue.length-1] = item;
        } else {
            int index = binarySearch(item);
            E[] tempArray = (E[]) new Object[queue.length];
            for (int i = queue.length-1; i > index; i--) { //copies upper part of array
                tempArray[i] = queue[i];
            }

            for (int i = index; i >= 0; i--) {  //moves lower part of array down the queue
                if (queue[i] == null) {
                    break;
                }
                tempArray[i-1] = queue[i];
            }
            queue = tempArray;
            queue[index] = item;
        }
        size++;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        queue = (E[]) coll.toArray();
    }

    @Override
    public boolean contains(E item) {
        return queue[binarySearch(item)].equals(item); //checks if binary search found the item
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

    private int binarySearch(E target) {
        int low = queue.length-1-size;
        int high = queue.length-1;
        int mid = 0;
        int cmpOutput;
        while (low <= high) {
            mid = ((high - low)/2) + low;
            if (queue[mid] == null) { //if mid is null, move up the search up the queue
                low = mid+1;
                continue;
            }
            cmpOutput = compare(queue[mid], target);
            if (cmpOutput < 0) { //if mid and target compare lower
                low = mid+1;
            } else if (cmpOutput > 0) { //if mid and target compare higher
                high = mid-1;
            } else {
                return mid;
            }
        }
        return mid-1;
    }

    /**
     * Controls what compareTo method is used in methods above.
     *
     * @param o1 - object 1
     * @param o2 - object 2
     * @return - result of compareTo method
     */
    private int compare(E o1, E o2) {
        if (cmp == null) { //default comparator
            return ((Comparable<? super E>) o1).compareTo(o2);
        } else { //custom comparator
            return cmp.compare(o1, o2);
        }
    }

    /**
     * Advances the items up the queue.
     */
    private void advanceQueue() {
        E[] tempArray = (E[]) new Object[queue.length];
        for (int i = 0; i < size; i++) {
            tempArray[i+1] = queue[i];
        }
        queue = tempArray;
    }

    /**
     * Doubles the size of the queue by 2.
     */
    private void enlargeQueue() {
        E[] tempArray = (E[]) new Object[queue.length*2];
        int j = 1;
        for (int i = size-1; i >= 0; i--) { //adds items from the last index to the first index
            tempArray[tempArray.length-j] = queue[i];
            j++;
        }
        queue = tempArray;
    }

    //TEST HELPER METHODS
    public E[] getQueue() {
        return queue;
    }

    public void setQueue(E[] queue) {
        this.queue = queue;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
