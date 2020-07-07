package PVZ;


import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser; 
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.control.MenuBar; 
import javafx.scene.control.MenuItem; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color; 

//launches the application 
public class Menu implements Initializable { 
	@FXML private Button start;
	@FXML private Button exit;
	@FXML private Button resume;
	@FXML private VBox vb;
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initData();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(user.getName());
	} 
	
	public void Start(ActionEvent event) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("level.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void Resume(ActionEvent event) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("level.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void exit(ActionEvent event) {
		System.exit(0);
	}
	
	private void initData() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in=null;
		try {
			in= new ObjectInputStream( new FileInputStream("present.txt"));
			user=(User) in.readObject();
		}
		finally {
			in.close();
		}
	}
} 

	

	