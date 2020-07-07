package PVZ;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class video implements Initializable {
	@FXML private BorderPane bp;
	@FXML private MediaView mv;
	private MediaPlayer mp;
	private Media me;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path= "file:/F:/DocNav/film.mp4";
		me= new Media(path);
		mp=new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		mp.play();
	}
	@FXML
	public void onEnter(ActionEvent event){
		try {
			Parent root= FXMLLoader.load(getClass().getResource("UserCreation.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

  }