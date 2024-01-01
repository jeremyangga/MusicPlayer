/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class MusicPlayer extends Application{
    
    public static void main(String[] args) {
       launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mp3ui.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Music Player");
        Image img = new Image("file:icons8-musical-notes-64.png");
        primaryStage.getIcons().add(img);
        primaryStage.setScene(scene);
       // primaryStage.setResizable(true);
        primaryStage.show();
        Connection con = ConnectDatabase.getConnection();
       if(con != null)
            System.out.println("Success connect to database");      
    }
    
}
