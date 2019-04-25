package tests;

import org.junit.jupiter.api.Test;
import trees.BST;

public class BSTTest extends BinarySearchTreeTest {

    void setUp(){
        tree = new BST<>();
    }

    @Test
    void insertTestBST(){
        setUp();
        inserTest();
    }

    @Test
    void deleteTestBST(){
        setUp();
        deleteTest();
    }

    @Test
    void searchElementTestBST(){
        setUp();
        searchElementTest();
    }

    @Test
    void keysInOrderTestBST(){
        setUp();
        keysInOrderTest();
    }

    @Test
    void valuesInOrderBST(){
        setUp();
        valuesInOrder();
    }
}
