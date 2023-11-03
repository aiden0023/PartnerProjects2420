package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A class to construct a binary search tree.
 *
 * @param <Type> - generic
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-11-02
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private BinaryTreeNode<Type> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(Type item) {
        if (root == null) { //if no root, start BST
            root = new BinaryTreeNode<>(item);
            size++;
            return true;
        } else {
            return addRecursive(root, item); //recursive start
        }
    }

    /**
     * The recursive method for add().
     *
     * @param node - current node
     * @param item - item to add
     * @return - whether added or not
     */
    private boolean addRecursive(BinaryTreeNode<Type> node, Type item) {
        int comparison = item.compareTo(node.getData());
        if (comparison < 0) { //if less than
            if (node.getLeft() == null) { //if no left node
                node.setLeft(new BinaryTreeNode<>(item)); //add node
                size++;
                return true;
            } else {
                return addRecursive(node.getLeft(), item); //again until opening found
            }
        } else if (comparison > 0) { //if greater than
            if (node.getRight() == null) { //if no right node
                node.setRight(new BinaryTreeNode<>(item)); //add node
                size++;
                return true;
            } else {
                return addRecursive(node.getRight(), item); //again until opening found
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean inserted = false;
        for (Type item : items) { //loops through all items to be added
            if (add(item)) { //if added successfully
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
        return containsRecursive(root, item); //starts recursive method
    }

    /**
     * Recursive method for contains().
     *
     * @param node - current node
     * @param item - item to check
     * @return - if item is in the BST (boolean)
     */
    private boolean containsRecursive(BinaryTreeNode<Type> node, Type item) {
        if (node == null) { //if node is null, return false
            return false;
        }

        int comparison = item.compareTo(node.getData());
        if (comparison == 0) { //if node is equal to item, return true
            return true;
        } else if (comparison < 0) { //if item less than node, call recursive method
            return containsRecursive(node.getLeft(), item);
        } else { //if item is greater than node, call recursive method
            return containsRecursive(node.getRight(), item);
        }
    }

    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        boolean contains = true;
        for (Type item : items) { //loops through all items to check
            if (!contains(item)) { //if BST does not contain item, return false
                contains = false;
            }
        }
        return contains;
    }

    @Override
    public Type first() throws NoSuchElementException {
        if (root == null) { //root is null, throw exception
            throw new NoSuchElementException();
        }

        BinaryTreeNode<Type> current = root;
        while (current.getLeft() != null) { //loop through BST until smallest item is found
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
        if (root == null) { //root is null, throw exception
            throw new NoSuchElementException();
        }

        BinaryTreeNode<Type> current = root;
        while (current.getRight() != null) { //loop through BST until largest item is found
            current = current.getRight();
        }
        return current.getData();
    }

    @Override
    public boolean remove(Type item) {
        BinaryTreeNode<Type> temp = removeRecursive(root, item); //start recursive method

        if (temp == null) { //if returned node is null, return false
            return false;
        }

        return temp.getData().equals(item); //returns boolean
    }

    /**
     * Recursive method for remove().
     *
     * @param node - current node
     * @param item - item to remove
     * @return
     */
    private BinaryTreeNode<Type> removeRecursive(BinaryTreeNode<Type> node, Type item) {
        if (node == null) { //if node is null, return node (null)
            return node;
        }

        int comparison = item.compareTo(node.getData());
        if (comparison == 0 && !item.equals(root.getData())) { //if item found and is not at the root
            if (node.getLeft() == null) { //if left node is null, return right node
                size--;
                return node.getRight();
            } else if (node.getRight() == null) { //if right node is null, return left node
                size--;
                return node.getLeft();
            }
            //recursive calls and find min if node as two children
            node.setData(findMin(node.getRight()));
            node.setRight(removeRecursive(node.getRight(), node.getData()));
            size--;
        } else if (item.equals(root.getData())) { //if item is found and is at the root
            if (node.getLeft() == null) { //if left node is null, return right node
                size--;
                root = root.getRight();
                return node;
            } else if (node.getRight() == null) { //if right node is null, return left node
                size--;
                root = root.getLeft();
                return node;
            }
            //recursive calls and find min if node as two children
            root.setData(findMin(root.getRight()));
            root.setRight(removeRecursive(root.getRight(), root.getData()));
            size--;
        } else if (comparison < 0) { //if item is less than node
            node.setLeft(removeRecursive(node.getLeft(), item)); //recursive call
        } else { //if item is greater than node
            node.setRight(removeRecursive(node.getRight(), item)); //recursive call
        }
        return node;
    }

    /**
     * Finds the minimum item of a subtree. Helper method for recursiveRemove().
     *
     * @param node - root of subtree
     * @return - data of minimum item in subtree
     */
    private Type findMin(BinaryTreeNode<Type> node) {
        Type min = node.getData();
        while (node.getLeft() != null) { //loops through subtree until minimum of the subtree is found
            min = node.getLeft().getData();
            node = node.getLeft();
        }
        return min;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean removed = false;
        for (Type item : items) { //loops through items to remove
            if (remove(item)) { //if removal was successful return true
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
        ArrayList<Type> list = new ArrayList<>(); //list to add items to
        inOrderTraversal(root, list); //start in order traversal to add items to list
        return list;
    }

    /**
     * Traverses the BST in order. Helper method for toArrayList().
     *
     * @param node - root node
     * @param list - list to write to
     */
    private void inOrderTraversal(BinaryTreeNode<Type> node, ArrayList<Type> list) {
        if (node != null) { //loops until hits end of subtree
            inOrderTraversal(node.getLeft(), list); //recursive call for left node
            list.add(node.getData()); //add current node to list
            inOrderTraversal(node.getRight(), list); //recursive call for right node
        }
    }
}
