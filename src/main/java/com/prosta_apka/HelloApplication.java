package com.prosta_apka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

public class HelloApplication extends Application {

    private static WeakHashMap<File, WeakReference<List<List<String>>>> pliki = new WeakHashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("csv_reader");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

    public static void read(File name){
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pliki.put(name, new WeakReference(records));

        //System.out.println(pliki);
    }

    public static boolean check(File name){
        if (pliki.containsKey(name)) return true;
        else return false;
    }

    public static WeakReference<List<List<String>>> get_from_map(File csv_name){

        return pliki.get(csv_name);
    }

}