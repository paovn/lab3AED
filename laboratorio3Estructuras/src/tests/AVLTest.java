package tests;

import org.junit.jupiter.api.Test;
import trees.AVLTree;

public class AVLTest extends BinarySearchTreeTest{

    void setUp(){
        tree = new AVLTree<>();
    }

    @Test
    void insertTestAVL(){
        setUp();
        inserTest();
    }

    @Test
    void deleteTestAVL(){
        setUp();
        deleteTest();
    }

    @Test
    void searchElementTestAVL(){
        setUp();
        searchElementTest();
    }

    @Test
    void keysInOrderTestAVL(){
        setUp();
        keysInOrderTest();
    }

    @Test
    void valuesInOrderAVL(){
        setUp();
        valuesInOrder();
    }

}
