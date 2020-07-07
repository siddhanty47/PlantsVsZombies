package PVZ;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane; 
import javafx.scene.paint.Color; 

public class Level implements Initializable{
	@FXML ImageView level1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		level1.setOnMouseClicked(e-> {
			clicked(e);
		});
	}
	public void clicked(MouseEvent e2) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("lawn.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)e2.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
