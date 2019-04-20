package trees;


import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>,V> extends BST<K,V>{

    class Node<K extends Comparable<K>,V> extends BST<K,V>.Node<K,V>{
        int balance;
        int height;

        Node(K key ,V value, Node<K,V> parent) {
            super(key,value,parent);
        }

        Node<K, V> getLeft() {
            return (Node<K, V>) left;
        }

        void setLeft(Node<K, V> left) {
            this.left = left;
        }

        Node<K, V> getRight() {
            return (Node<K, V>)right;
        }

        void setRight(Node<K, V> right) {
            this.right = right;
        }

        Node<K, V> getParent() {
            return (Node<K, V>)parent;
        }

        void setParent(Node<K, V> parent) {
            this.parent = parent;
        }
    }

    private Node<K,V> root;

    @Override
    public boolean insert(K key, V value) {
        if (root == null) {
            root = new Node<K,V>(key,value, null);
            return true;
        }

        Node<K,V> n = root;
        while (true) {
            //if (n.key.equals(key))
            //   return false;

            Node<K,V> parent = n;

            boolean goLeft = n.key.compareTo(key)>0;
            n = goLeft ? n.getLeft() : n.getRight();

            if (n == null) {
                if (goLeft) {
                    parent.setLeft(new Node<K,V>(key,value, parent));
                } else {
                    parent.setRight(new Node<K,V>(key,value, parent));
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(Node<K,V> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() == null) {
                root = null;
            } else {
                Node<K,V> parent = node.getParent();
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
            Node<K,V> child = node.getLeft();
            while (child.getRight() != null) child = child.getRight();
            node.key = child.key;
            node.value = child.value;
            delete(child);
        } else {
            Node<K,V> child = node.getRight();
            while (child.getLeft() != null) child = child.getLeft();
            node.key = child.key;
            node.value = child.value;
            delete(child);
        }
    }

    public void delete(K delKey) {
        if (root == null)
            return;

        Node<K,V> child = root;
        while (child != null) {
            Node<K,V> node = child;
            child = delKey.compareTo(node.key) >= 0 ? node.getRight() : node.getLeft();
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }

    private void rebalance(Node<K,V> n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight()))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.balance == 2) {
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

    private Node<K,V> rotateLeft(Node<K,V> a) {

        Node<K,V> b = a.getRight();
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

    private Node<K,V> rotateRight(Node<K,V> a) {

        Node<K,V> b = a.getLeft();
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

    private Node<K,V> rotateLeftThenRight(Node<K,V> n) {
        n.setLeft(rotateLeft(n.getLeft()));
        return rotateRight(n);
    }

    private Node<K,V> rotateRightThenLeft(Node<K,V> n) {
        n.setRight(rotateRight(n.getRight()));
        return rotateLeft(n);
    }

    private int height(Node<K,V> n) {
        if (n == null)
            return -1;
        return n.height;
    }

    private void setBalance(Node... nodes) {
        for (Node<K,V> n : nodes) {
            reheight(n);
            n.balance = height(n.getRight()) - height(n.getLeft());
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node<K,V> n) {
        if (n != null) {
            printBalance(n.getLeft());
            System.out.printf("%s ", n.balance);
            printBalance(n.getRight());
        }
    }

    public List<K> keysInOrder(){
        if (root != null) return keysInOrder(root,new ArrayList<>());
        return null;
    }

    public List<V> valuesInOrder(){
        if (root != null) return valuesInOrder(root,new ArrayList<>());
        return null;
    }

    public List<Box<K,V>> pairOfElementsInOrder(){
        if (root != null) return pairOfElementsInOrder(root,new ArrayList<>());
        return null;
    }

    public V searchElement(K key){
        if (isEmpty()) return null;
        return searchElement(key,root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private List<Box<K, V>> pairOfElementsInOrder(Node<K, V> root, List<Box<K, V>> list) {
        if (root.getLeft() != null) pairOfElementsInOrder(root.getLeft(),list);
        list.add(root.getBox());
        if (root.getRight() != null) pairOfElementsInOrder(root.getRight(),list);
        return list;
    }

    private List<K> keysInOrder(Node<K,V> root, List<K> list) {
            if (root.getLeft() != null) keysInOrder(root.getLeft(),list);
            list.add(root.key);
            if (root.getRight() != null) keysInOrder(root.getRight(),list);
            return list;
    }

    private List<V> valuesInOrder(Node<K,V> root, List<V> list) {
        if (root.getLeft() != null) valuesInOrder(root.getLeft(),list);
        list.add(root.value);
        if (root.getRight() != null) valuesInOrder(root.getRight(),list);
        return list;
    }

    private void reheight(Node<K,V> node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }


    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>();

        System.out.println("Inserting values 1 to 64");
        for (int i = 0; i <= 64; i++) {
            tree.insert(i/2,i*8);
        }
        System.out.println(tree.keysInOrder().size());
        System.out.print("Printing balance: ");
        tree.printBalance();
        tree.keysInOrder().forEach(System.out::println);
        System.out.println("\n"+tree.searchElement(8));
        tree.delete(8);
        System.out.println("\n"+tree.searchElement(8));
        tree.delete(8);
        System.out.println("\n"+tree.searchElement(8));

    }
}
