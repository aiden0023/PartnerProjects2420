package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private BinaryTreeNode<Type> root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean add(Type item) {
        if (root == null) {
            root = new BinaryTreeNode<>(item);
            return true;
        } else {
            //recursive implementation?
            return false; //temp
        }
    }

    @Override
    public boolean addAll(Collection<? extends Type> items) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Type item) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        return false;
    }

    @Override
    public Type first() throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Type last() throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean remove(Type item) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ArrayList<Type> toArrayList() {
        return null;
    }
}
