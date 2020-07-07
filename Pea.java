package PVZ;

import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Pea implements Serializable{
	private int Speed;
	private int damage;
	private double shootspeed;
	private final ImageView pea_img = new ImageView(new Image("resource/pea.png"));
	private Pane p;
	protected PathTransition tr;
	
	Pea(){
		pea_img.setFitHeight(20);
    	pea_img.setFitWidth(20);
    	p = new Pane(pea_img);
	}
	
	Pane getPane() {
		return p;
	}
	
	void shoot(int a,int b,Pane lbg) {
		lbg.getChildren().add(p);
		Line hor= new Line();
		hor.setStartX((a+2)*101+75);
		hor.setStartY(b*114+5);
		hor.setEndX((a+2)*101+900-(a+2)*101+200);
		hor.setEndY(b*114);
		tr= new PathTransition();
		tr.setNode(p);
		tr.setDuration(Duration.seconds(2));
		tr.setPath(hor);
		tr.setDelay(Duration.seconds(4));
		tr.setCycleCount(Animation.INDEFINITE);
		tr.play();
	}
}
