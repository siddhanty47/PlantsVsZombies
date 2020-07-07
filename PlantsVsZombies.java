package PVZ;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.RotateTransition; 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Polygon; 
import javafx.stage.Stage; 
import javafx.util.Duration; 
         
public class PlantsVsZombies extends Application { 
	Parent root;
	Stage window;
	protected ArrayList<User> users=new ArrayList<User>();
	@Override 
	public void start(Stage stage) throws IOException {  
		window=stage;
		root = FXMLLoader.load(getClass().getResource("video.fxml"));
	    Scene scene=new Scene(root);
	    scene.setRoot(root);
	    window.setScene(scene);
	    window.setTitle("Plants vs Zombies");
	    window.show();
	    System.out.println("iam here!!");
   }
	
   public static void main(String args[]){ 
      launch(args); 
   } 
   
   ArrayList<User> getUsers() {
	   return users;
   }
   
   void addUser(User u) {
	   users.add(u);
   }
   
} 
