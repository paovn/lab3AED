package tests;

import trees.BinarySearchTree;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree<String,Integer> tree;
    private final int numOfAdditions = 500000, numOfDeletions = 100000, searchMin = 1000, searchMax = 10000;

    private void fillTree(int min, int max) {
        for (int i = min; i < max; i++) {
            tree.insert("Key-"+i,i);
        }
    }

    // *************************
    //        Insert Test
    // *************************
    void inserTest() {
        fillTree(0, numOfAdditions);
        assertEquals(tree.keysInOrder().size(), numOfAdditions);
        for (int i = 0; i < numOfAdditions; i++) {
            assertEquals((int)tree.searchElement("Key-" + i), i);
        }
    }

    // *************************
    //        Delete Test
    // *************************
    void deleteTest() {
        fillTree(0, numOfAdditions);
        Set<String> removeKeys = new HashSet<>();
        for (int i = 0; i < numOfDeletions;) {
            String s ="Key-" + (int) (Math.random()* numOfAdditions);
            if (tree.searchElement(s) != null){
                tree.delete(s);
                removeKeys.add(s);
                i++;
            }
        }
        tree.keysInOrder().forEach(s -> assertFalse(removeKeys.contains(s)));
        assertEquals(tree.size(), numOfAdditions -numOfDeletions);
    }

    // *************************
    //    searchElement Test
    // *************************
    void searchElementTest(){
        fillTree(searchMin,searchMax);
        for (int i = 0; i < numOfDeletions; i++) {
            int num = (int) (Math.random()* numOfAdditions);
            String s ="Key-" + num;
            if (num >= searchMin && num < searchMax){
                assertNotNull(tree.searchElement(s));
                assertEquals((int)tree.searchElement(s),num);
            }else {
                assertNull(tree.searchElement(s));
            }
        }
    }

    // *************************
    //    keysInOrder Test
    // *************************
    void keysInOrderTest(){
        fillTree(0,numOfAdditions);
        List<String> keys = tree.keysInOrder();
        assertEquals(keys.size(),tree.size());
        List<String> clone = new ArrayList<>(keys);
        clone.sort(Comparator.naturalOrder());
        assertEquals(keys,clone);
    }

    // *************************
    //    valuesInOrder Test
    // *************************
    void valuesInOrder(){
        fillTree(0,numOfAdditions);
        List<Integer> vals = tree.valuesInOrder();
        assertEquals(vals.size(),tree.size());
        for (int i = 0; i < vals.size()-1; i++) {
            assertTrue(String.valueOf(vals.get(i)).compareTo(String.valueOf(vals.get(i+1)))<0);
        }
    }

}