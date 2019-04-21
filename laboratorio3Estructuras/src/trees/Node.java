package trees;

class Node<K extends Comparable<K>,V> {
    private K key;
    private V value;
    private Node<K,V> left;
    private Node<K,V> right;
    private Node<K,V> parent;

    Node(K key ,V value, Node<K,V> parent) {
        this.setValue(value);
        this.setKey(key);
        this.setParent(parent);
    }

    Box<K,V> getBox(){return new Box<K,V>(this);}

    K getKey() {
        return key;
    }

    void setKey(K key) {
        this.key = key;
    }

    V getValue() {
        return value;
    }

    void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    void setRight(Node<K, V> right) {
        this.right = right;
    }

    Node<K, V> getParent() {
        return parent;
    }

    void setParent(Node<K, V> parent) {
        this.parent = parent;
    }
}