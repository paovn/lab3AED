package trees;

class AVLNode<K extends Comparable<K>,V> extends Node<K,V>{
    private int balance;
    private int height;

    AVLNode(K key , V value, AVLNode<K,V> parent) {
        super(key,value,parent);
    }

    public AVLNode<K, V> getLeft() {
        return (AVLNode<K, V>) super.getLeft();
    }

    public AVLNode<K, V> getRight() {
        return (AVLNode<K, V>) super.getRight();
    }

    public AVLNode<K, V> getParent() {
        return (AVLNode<K, V>) super.getParent();
    }

    int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }
}