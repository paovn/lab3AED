package trees;

import java.util.ArrayList;
import java.util.List;

public interface BinarySearchTree<K extends Comparable<K>,V> {

    /**
     * in
     * @param key - the key of the element to be added
     * @param value - the value to the element to be added
     * @return
     */
    boolean insert(K key, V value);

    /**
     * remove the element whose key the user choose
     * @param delKey - the key of the element that will be delete
     */
    void delete(K delKey);

    /**
     * Search an element in the tree
     * @param key - the key of the element
     * @return - the value of the element, null if the element don't exist in the tree
     */
    V searchElement(K key);

    /**
     * check if the tree is or not empty
     * @return
     */
    boolean isEmpty();

    /**
     * check if the value whit the key key is in the Tree </br>
     * @param key
     * @return
     */
    boolean exists(K key);

}

