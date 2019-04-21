package trees;

import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>,V> implements BinarySearchTree<K,V>{

    Node<K,V> root;
    int size;

    @Override
    public boolean insert(K key, V value) {
        size++;
        if (!isEmpty())
        {
            return insert(key, value, root);
        }
        else
        {
            root = new Node<>(key, value,null);
            return true;
        }
    }

    private boolean insert(K key, V value, Node<K,V> root) {
        if (key.compareTo(root.getKey()) > 0){
            if (root.getRight() == null) root.setRight(new Node<>(key,value,root));
            else insert(key,value, root.getRight());
        }else {
            if (root.getLeft() == null) root.setLeft(new Node<>(key,value,root));
            else insert(key,value, root.getLeft());
        }
        return true;
    }

    @Override
    public void delete(K delKey) {
        if (root == null)
            return;

        size--;
        Node<K,V> child = root;
        while (child != null) {
            Node<K,V> node = child;
            child = delKey.compareTo(node.getKey()) >= 0 ? node.getRight() : node.getLeft();
            if (delKey.equals(node.getKey())) {
                delete(node);
                return;
            }
        }
        size++;
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
            }
            return;
        }

        if (node.getLeft() != null) {
            Node<K,V> child = node.getLeft();
            while (child.getRight() != null) child = child.getRight();
            node.setKey(child.getKey());
            node.setValue(child.getValue());
            delete(child);
        } else {
            Node<K,V> child = node.getRight();
            while (child.getLeft() != null) child = child.getLeft();
            node.setKey(child.getKey());
            node.setValue(child.getValue());
            delete(child);
        }
    }

    @Override
    public V searchElement(K key) {
        if (isEmpty()) return null;
        return searchElement(key,root);
    }

    V searchElement(K key, Node<K,V> root) {
        if (key.equals(root.getKey())){
            return root.getValue();
        }else if (root.getKey().compareTo(key)>0 && root.getLeft() != null){
            return searchElement(key, root.getLeft());
        }else if (root.getKey().compareTo(key)<0 && root.getRight() != null){
            return searchElement(key, root.getRight());
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean exists(K key) {
        return searchElement(key) != null;
    }

    @Override
    public int size() {
        return size;
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

    List<Box<K, V>> pairOfElementsInOrder(Node<K, V> root, List<Box<K, V>> list) {
        if (root.getLeft() != null) pairOfElementsInOrder(root.getLeft(),list);
        list.add(root.getBox());
        if (root.getRight() != null) pairOfElementsInOrder(root.getRight(),list);
        return list;
    }

    List<K> keysInOrder(Node<K, V> root, List<K> list) {
        if (root.getLeft() != null) keysInOrder(root.getLeft(),list);
        list.add(root.getKey());
        if (root.getRight() != null) keysInOrder(root.getRight(),list);
        return list;
    }

    List<V> valuesInOrder(Node<K, V> root, List<V> list) {
        if (root.getLeft() != null) valuesInOrder(root.getLeft(),list);
        list.add(root.getValue());
        if (root.getRight() != null) valuesInOrder(root.getRight(),list);
        return list;
    }
}
