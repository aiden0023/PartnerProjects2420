package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private BinaryTreeNode<Type> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(Type item) {
        if (root == null) {
            root = new BinaryTreeNode<>(item);
            size++;
            return true;
        } else {
            return addRecursive(root, item);
        }
    }

    private boolean addRecursive(BinaryTreeNode<Type> node, Type item) {
        int comparison = item.compareTo(node.getData());
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode<>(item));
                size++;
                return true;
            } else {
                return addRecursive(node.getLeft(), item);
            }
        } else if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode<>(item));
                size++;
                return true;
            } else {
                return addRecursive(node.getRight(), item);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean inserted = false;
        for (Type item : items) {
            if (add(item)) {
                inserted = true;
            }
        }
        return inserted;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(Type item) {
        return containsRecursive(root, item);
    }

    private boolean containsRecursive(BinaryTreeNode<Type> node, Type item) {
        if (node == null) {
            return false;
        }

        int comparison = item.compareTo(node.getData());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return containsRecursive(node.getLeft(), item);
        } else {
            return containsRecursive(node.getRight(), item);
        }
    }

    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        boolean contains = true;
        for (Type item : items) {
            if (!contains(item)) {
                contains = false;
            }
        }
        return contains;
    }

    @Override
    public Type first() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }

        BinaryTreeNode<Type> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Type last() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }

        BinaryTreeNode<Type> current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getData();
    }

    @Override
    public boolean remove(Type item) {
        BinaryTreeNode<Type> temp = removeRecursive(root, item);
        if (temp.getData().equals(item)) {
            return true;
        } else {
            return false;
        }
    }

    private BinaryTreeNode<Type> removeRecursive(BinaryTreeNode<Type> node, Type item) {
        if (node == null) {
            return node;
        }

        int comparison = item.compareTo(node.getData());
        if (comparison == 0) {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setData(findMin(node.getRight()));
            node.setRight(removeRecursive(node.getRight(), node.getData()));
            size--;
        } else if (comparison < 0) {
            node.setLeft(removeRecursive(node.getLeft(), item));
        } else {
            node.setRight(removeRecursive(node.getRight(), item));
        }
        return node;
    }

    private Type findMin(BinaryTreeNode<Type> node) {
        Type min = node.getData();
        while (node.getLeft() != null) {
            min = node.getLeft().getData();
            node = node.getLeft();
        }
        return min;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean removed = false;
        for (Type item : items) {
            if (remove(item)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(BinaryTreeNode<Type> node, ArrayList<Type> list) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inOrderTraversal(node.getRight(), list);
        }
    }
}
