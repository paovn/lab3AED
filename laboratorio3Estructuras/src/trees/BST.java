package trees;

public class BST<K extends Comparable<K>,V> implements BinarySearchTree<K,V>{

    class Node<K extends Comparable<K>,V> {
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        Node(K key ,V value, Node<K,V> parent) {
            this.value = value;
            this.key = key;
            this.parent = parent;
        }

        Box<K,V> getBox(){return new Box<>(this);}
    }

    class Box<K extends Comparable<K>,V>{
        private K key;
        private V value;
        private Box(Node<K,V>node){
            key = node.key;
            value = node.value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    Node<K,V> root;

    @Override
    public boolean insert(K key, V value) {
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
        if (key.compareTo(root.key) > 0){
            if (root.right == null) root.right = new Node<>(key,value,root);
            else insert(key,value,root.right);
        }else {
            if (root.left == null) root.left = new Node<>(key,value,root);
            else insert(key,value,root.left);
        }
        return true;
    }

    @Override
    public void delete(K delKey) {
        if (root == null)
            return;

        Node<K,V> child = root;
        while (child != null) {
            Node<K,V> node = child;
            child = delKey.compareTo(node.key) >= 0 ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }

    private void delete(Node<K,V> node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node<K,V> parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            return;
        }

        if (node.left != null) {
            Node<K,V> child = node.left;
            while (child.right != null) child = child.right;
            node.key = child.key;
            node.value = child.value;
            delete(child);
        } else {
            Node<K,V> child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            node.value = child.value;
            delete(child);
        }
    }

    @Override
    public V searchElement(K key) {
        if (isEmpty()) return null;
        return searchElement(key,root);
    }

    V searchElement(K key, Node<K,V> root) {
        if (key.equals(root.key)){
            return root.value;
        }else if (root.key.compareTo(key)>0 && root.left != null){
            return searchElement(key,root.left);
        }else if (root.key.compareTo(key)<0 && root.right != null){
            return searchElement(key,root.right);
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
}

