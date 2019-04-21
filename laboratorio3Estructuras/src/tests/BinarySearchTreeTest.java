package tests;

import org.junit.jupiter.api.Test;
import trees.AVLTree;
import trees.BST;
import trees.BinarySearchTree;
import trees.RedBlackTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTreeTest {

    private BinarySearchTree<String,Integer> tree;
    private int nubOfAdditions = 500000;
    private int numOfDeletions = 100000;

    void setUpLikeBST() {
        tree = new BST<>();
    }

    void setUpLikeAVL(){
        tree = new AVLTree<>();
    }

    void setUpLikeRedBlack(){
        tree = new RedBlackTree<>();
    }

    private void fillTree(int max) {
        for (int i = 0; i < max; i++) {
            tree.insert("Key-"+i,i);
        }
    }

    // *************************
    //        Insert Test
    // *************************
    private void inserTest() {
        fillTree(nubOfAdditions);
        assertEquals(tree.keysInOrder().size(), nubOfAdditions);
        for (int i = 0; i < nubOfAdditions; i++) {
            assertEquals(tree.searchElement("Key-" + i), i);
        }
    }

    @Test
    void insertTestBST(){
        setUpLikeBST();
        inserTest();
    }

    @Test
    void insertTestAVL(){
        setUpLikeAVL();
        inserTest();
    }

    @Test
    void insertTestRBT(){
        setUpLikeRedBlack();
        inserTest();
    }

    // *************************
    //        Delete Test
    // *************************
    private void deleteTest() {
        fillTree(nubOfAdditions);
        Set<String> removeKeys = new HashSet<>();
        for (int i = 0; i < numOfDeletions;) {
            String s ="Key-" + (int) (Math.random()*nubOfAdditions);
            if (tree.searchElement(s) != null){
                tree.delete(s);
                removeKeys.add(s);
                i++;
            }
        }
        tree.keysInOrder().forEach(s -> assertFalse(removeKeys.contains(s)));
    }

    @Test
    void deleteTestBST(){
        setUpLikeBST();
        deleteTest();

    }
}