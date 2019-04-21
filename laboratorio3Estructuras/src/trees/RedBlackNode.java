package trees;

class RedBlackNode<K extends Comparable<K>,V> extends Node<K,V> {

    /** Possible color for this node */
    static final int BLACK = 0;
    /** Possible color for this node */
    static final int RED = 1;
    // the key of each node

    // the number of elements to the left of each node
    private int numLeft = 0;
    // the number of elements to the right of each node
    private int numRight = 0;
    // the color of a node
    private int color;

    RedBlackNode(){
        super(null,null,null);
        setColor(BLACK);
        setNumLeft(0);
        setNumRight(0);
    }

    // Constructor which sets key to the argument.
    RedBlackNode(K key,V value){
        super(key,value,null);
        setColor(BLACK);
        setNumLeft(0);
        setNumRight(0);
    }

    /** Parent of node */
    public RedBlackNode<K, V> getParent() {
        return (RedBlackNode<K, V>) super.getParent();
    }

    void setParent(RedBlackNode<K, V> parent) {
        super.setParent(parent);
    }

    /** Left child */
    public RedBlackNode<K, V> getLeft() {
        return (RedBlackNode<K, V>) super.getLeft();
    }

    void setLeft(RedBlackNode<K, V> left) {
        super.setLeft(left);
    }

    /** Right child */
    public RedBlackNode<K, V> getRight() {
        return (RedBlackNode<K, V>) super.getRight();
    }

    void setRight(RedBlackNode<K, V> right) {
        super.setRight(right);
    }

    int getNumLeft() {
        return numLeft;
    }

    void setNumLeft(int numLeft) {
        this.numLeft = numLeft;
    }

    int getNumRight() {
        return numRight;
    }

    void setNumRight(int numRight) {
        this.numRight = numRight;
    }

    int getColor() {
        return color;
    }

    void setColor(int color) {
        this.color = color;
    }
}