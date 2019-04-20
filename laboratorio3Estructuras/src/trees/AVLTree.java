package trees;


import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>,V> extends BST<K,V>{


     private AVLNode<K,V> root = (AVLNode<K,V>) super.root;

    @Override
    public boolean insert(K key, V value) {
        size++;
        if (root == null) {
            root = new AVLNode<>(key, value, null);
            return true;
        }

        AVLNode<K,V> n = root;
        while (true) {
            //if (n.key.equals(key))
            //   return false;

            AVLNode<K,V> parent = n;

            boolean goLeft = n.getKey().compareTo(key)>0;
            n = goLeft ? n.getLeft() : n.getRight();

            if (n == null) {
                if (goLeft) {
                    parent.setLeft(new AVLNode<>(key, value, parent));
                } else {
                    parent.setRight(new AVLNode<>(key, value, parent));
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(AVLNode<K,V> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() == null) {
                root = null;
            } else {
                AVLNode<K,V> parent = node.getParent();
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                rebalance(parent);
            }
            return;
        }

        if (node.getLeft() != null) {
            AVLNode<K,V> child = node.getLeft();
            while (child.getRight() != null) child = child.getRight();
            node.setKey(child.getKey());
            node.setValue(child.getValue());
            delete(child);
        } else {
            AVLNode<K,V> child = node.getRight();
            while (child.getLeft() != null) child = child.getLeft();
            node.setKey(child.getKey());
            node.setValue(child.getValue());
            delete(child);
        }
    }

    @Override
    public void delete(K delKey) {
        if (root == null)
            return;
        size--;
        AVLNode<K,V> child = root;
        while (child != null) {
            AVLNode<K,V> node = child;
            child = delKey.compareTo(node.getKey()) >= 0 ? node.getRight() : node.getLeft();
            if (delKey == node.getKey()) {
                delete(node);
                return;
            }
        }
        size++;
    }

    private void rebalance(AVLNode<K,V> n) {
        setBalance(n);

        if (n.getBalance() == -2) {
            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight()))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.getBalance() == 2) {
            if (height(n.getRight().getRight()) >= height(n.getRight().getLeft()))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.getParent() != null) {
            rebalance(n.getParent());
        } else {
            root = n;
        }
    }

    private AVLNode<K,V> rotateLeft(AVLNode<K,V> a) {

        AVLNode<K,V> b = a.getRight();
        b.setParent(a.getParent());

        a.setRight(b.getLeft());

        if (a.getRight() != null)
            a.getRight().setParent(a);

        b.setLeft(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private AVLNode<K,V> rotateRight(AVLNode<K,V> a) {

        AVLNode<K,V> b = a.getLeft();
        b.setParent(a.getParent());

        a.setLeft(b.getRight());

        if (a.getLeft() != null)
            a.getLeft().setParent(a);

        b.setRight(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private AVLNode<K,V> rotateLeftThenRight(AVLNode<K,V> n) {
        n.setLeft(rotateLeft(n.getLeft()));
        return rotateRight(n);
    }

    private AVLNode<K,V> rotateRightThenLeft(AVLNode<K,V> n) {
        n.setRight(rotateRight(n.getRight()));
        return rotateLeft(n);
    }

    private int height(AVLNode<K,V> n) {
        if (n == null)
            return -1;
        return n.getHeight();
    }

    @SafeVarargs
    private final void setBalance(AVLNode<K, V>... nodes) {
        for (AVLNode<K,V> n : nodes) {
            reheight(n);
            n.setBalance(height(n.getRight()) - height(n.getLeft()));
        }
    }

    public V searchElement(K key){
        if (isEmpty()) return null;
        return searchElement(key,root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private void reheight(AVLNode<K,V> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        }
    }

    @Override
    public List<K> keysInOrder(){
        if (root != null) return keysInOrder(root,new ArrayList<>());
        return null;
    }

    @Override
    public List<V> valuesInOrder(){
        if (root != null) return valuesInOrder(root,new ArrayList<>());
        return null;
    }

    @Override
    public List<Box<K,V>> pairOfElementsInOrder(){
        if (root != null) return pairOfElementsInOrder(root,new ArrayList<>());
        return null;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new RedBlackTree<>();
        //BinarySearchTree<Integer, Integer> tree = new AVLTree<>();

        System.out.println("Inserting values 1 to 64");
        for (int i = 0; i <= 64; i++) {
            tree.insert(i/2,i*8);
        }
        System.out.println(tree.keysInOrder().size());
        System.out.print("Printing balance: ");
        System.out.println();
        for (Integer integer : tree.keysInOrder()) System.out.print(integer + " ");
        System.out.println("\n"+tree.searchElement(8));
        tree.delete(8);
        System.out.println("\n"+tree.searchElement(8));
        tree.delete(8);
        System.out.println("\n"+tree.searchElement(8));
        System.out.println(tree.size());
        tree.delete(9);
        System.out.println(tree.size());

    }
}