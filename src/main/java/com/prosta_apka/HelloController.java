package com.prosta_apka;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText1;

    @FXML
    private Label welcomeText2;
    @FXML
    private TreeView tree;
    private Window stage;
    @FXML
    protected void onButton() throws InterruptedException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File name = fileChooser.showOpenDialog(stage);

        if (HelloApplication.check(name)){
            welcomeText.setText("było w pamięci");
        }else{
            welcomeText.setText("nie było w pamięci");
            HelloApplication.read(name);
        }

        WeakReference<List<List<String>>> records=HelloApplication.get_from_map(name);


        welcomeText1.setText(records.get().get(0).toString()+"\n"+records.get().get(1).toString()+"\n"+records.get().get(2).toString()+"\n"+records.get().get(3).toString());
        welcomeText2.setText("tu będzie srednia");
    }

    private TreeItem<String> createItems(File file){
        TreeItem<String> treeItems = new TreeItem<>(file.getName());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childItem : files){
                    treeItems.getChildren().addAll(createItems(childItem));
                }
            }
        }

        return treeItems;
    }

    @FXML
    protected void onButton1(){
        File filePath = new File("C:\\Users\\micha\\Desktop\\aaaaaaaa");
        TreeItem<String> root = createItems(filePath);

        tree.setRoot(root);

        /*tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) ->{
            String selectedItem = newValue.getValue();
            System.out.println(selectedItem);
        });*/

    }

    @FXML
    protected void treeClicked(){
        //tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) ->{
            /*String selectedItem = newValue.getValue();
            System.out.println(selectedItem);*/

    }

}



