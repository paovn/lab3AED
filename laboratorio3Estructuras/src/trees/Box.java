package trees;

class Box<K extends Comparable<K>,V>{
    private K key;
    private V value;
    Box(Node<K,V> node){
        key = node.getKey();
        value = node.getValue();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}