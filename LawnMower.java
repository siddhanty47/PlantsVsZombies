package PVZ;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class LawnMower implements Serializable{
	private ArrayList<Zombies> zombies;
	private boolean Active=true;
	private int column;
	private Tile tile;
	private Timeline attack;
	private Pane lbg;
	private Node p;
	private ImageView lawnmower;
	private boolean flag2=true;
	
	public LawnMower(int i,ImageView lawnmower,ArrayList<Zombies> z,Pane lbg) {
		this.column=i;
		this.lawnmower=lawnmower;
		this.zombies=z;
		this.lbg=lbg;
	}

	void Attack() {
		attack = new Timeline(new KeyFrame(Duration.millis(500),e-> {
			boolean flag=false;
			for(Zombies z : zombies) {
				if(z.getPane().getBoundsInParent().getCenterX()<=400&&z.getPane().getBoundsInParent().getCenterX()>=320&&
						(int)((z.getPane().getBoundsInParent().getCenterY()-10)/100)==this.column&&flag2&&z.health>0) {
					flag=true;flag2=false;
					System.out.println("moving");
					move();
				}
			}
			if(Active&&flag) {
				System.out.println("launch!!");
				Active=false;
				flag=false;
				for(Zombies z : zombies) {
					if((int)((z.getPane().getBoundsInParent().getCenterY()-10)/100)==this.column) {
						lbg.getChildren().remove(z.getPane());z.health=0;
					if(z.health<=0) {
						lbg.getChildren().remove(z.getPane());
						System.out.println("killed");
					}
					}
				}
			}
	}));
	attack.setCycleCount(Animation.INDEFINITE);
	attack.setDelay(Duration.seconds(5));
	attack.play();
	}
	
	private void move() {
		Line hor= new Line();
		hor.setStartX(hor.getStartX());
		hor.setStartY(hor.getStartY());
		hor.setEndX(1400);
		hor.setEndY(hor.getStartY());
		PathTransition tr = new PathTransition();
		tr.setNode(lawnmower);
		tr.setDuration(Duration.seconds(2));
		tr.setPath(hor);
		tr.setCycleCount(1);
		tr.play();
		
	}

	boolean isActive() {
		return Active;
	}
}
