package PVZ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable{
	@FXML private GridPane lawn;
	@FXML private Pane sun;
	@FXML private Pane lbg;
	@FXML private VBox plants;
	@FXML private Pane zbg;
	@FXML private Label suncount;
	@FXML private Label winLabel;
	@FXML private ImageView sunflower;
	@FXML private ImageView peashooter;
	@FXML private ImageView wallnut;
	@FXML private ImageView cherrybomb;
	@FXML private Button tile;
	@FXML private ImageView menu;
	@FXML Label time;
	@FXML private ImageView Lawnmower1;
	@FXML private ImageView Lawnmower2;
	@FXML private ImageView Lawnmower3;
	@FXML private ImageView Lawnmower4;@FXML private ImageView Lawnmower5;
	private GamePlay g;
	private int num=0;
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*ObjectInputStream in=null;
		try {
			in= new ObjectInputStream( new FileInputStream("sky7_1.txt"));
			g=(GamePlay) in.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}*/
		g=new GamePlay(lawn, lbg, menu, sunflower, peashooter, wallnut, cherrybomb,suncount,time,winLabel,
				Lawnmower1,Lawnmower2,Lawnmower3,Lawnmower4,Lawnmower5);
		g.Start();
	}


	@FXML
    private void mouseClicked(MouseEvent e) {
		try {
			g.plant(e);
		} catch (OutOfSunException e1) {
			e1.printStackTrace();
		}
    }
	
	
	void Save() {
	}
}
	

