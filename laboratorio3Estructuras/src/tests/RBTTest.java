package tests;

import org.junit.jupiter.api.Test;
import trees.RedBlackTree;

public class RBTTest extends BinarySearchTreeTest {

    void setUp(){
        tree = new RedBlackTree<>();
    }

    @Test
    void insertTestRBT(){
        setUp();
        inserTest();
    }

    @Test
    void deleteTestRBT(){
        setUp();
        deleteTest();
    }

    @Test
    void searchElementTestRBT(){
        setUp();
        searchElementTest();
    }

    @Test
    void keysInOrderTestRBT(){
        setUp();
        keysInOrderTest();
    }

    @Test
    void valuesInOrderRBT(){
        setUp();
        valuesInOrder();
    }
}
