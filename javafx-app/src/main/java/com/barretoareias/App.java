package com.barretoareias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

import com.barretoareias.utils.DataKey;

public class App extends Application {

    private static Scene scene;
    private static HashMap<DataKey,Object> data = new HashMap<>();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 600, 400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        } catch (Exception e) {
            System.out.printf("Unable to load file %s\n",fxml);
            System.out.printf("%s\n",e.getMessage());
        }
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static <T> void setData(DataKey key,T value){
        App.data.put(key, value);
    }

    public static <T> T getData(DataKey key){
        return (T)App.data.get(key);
    }

    public static void removeData(DataKey key){
        App.data.remove(key);
    }

}