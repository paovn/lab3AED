package model;

import trees.AVLTree;

import java.io.*;

public class BVC {

    private AVLTree<String,String> actions;

    public BVC(){
        loadActions();
        if (actions == null) actions = new AVLTree<>();
    }

    private Action searchAction(String name){
        String file = actions.searchElement(name);
        StringBuilder action = new StringBuilder();
        if (file == null) return null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bf.readLine())!=null) {
                action.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Action(action.toString());
    }

    private void loadActions() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File("actions.tree")));
            actions = (AVLTree<String, String>) reader.readObject();
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("corrupt file");
        }
    }

    private void saveActions() {
        try {
            ObjectOutput writer = new ObjectOutputStream(new FileOutputStream(new File("actions.tree")));
            writer.writeObject(actions);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
