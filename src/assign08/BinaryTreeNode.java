package assign08;

/**
 * A basic class for a node of a Binary Search Tree.
 *
 * @param <Type> - generic
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-11-02
 */
public class BinaryTreeNode<Type extends Comparable<? super Type>> {
    private Type data;
    private BinaryTreeNode<Type> left;
    private BinaryTreeNode<Type> right;

    public BinaryTreeNode(Type data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Type getData() {
        return data;
    }

    public void setData(Type data) {
        this.data = data;
    }

    public BinaryTreeNode<Type> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<Type> left) {
        this.left = left;
    }

    public BinaryTreeNode<Type> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<Type> right) {
        this.right = right;
    }
}
