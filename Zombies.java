package PVZ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Zombies implements Character,Serializable {
	protected double health;
	protected final int level =1;
	protected double speed;
	protected Tile tile;
	private final Image zimg =new Image("resource/zombie_moving.gif");
	private ImageView zombies_img;
	private Pane zombie;
	private Pane lawn;
	private ArrayList<Plant> plants; 
	private GridPane gp;
	
	Zombies(Pane lawn, ArrayList<Plant> p,GridPane gp) {
		zombies_img=new ImageView();
		this.health=100;
		zombies_img.setFitHeight(100);
		zombies_img.setFitWidth(100);
		zombies_img.setImage(zimg);
		zombie=new Pane(zombies_img);
		this.lawn=lawn;
		this.plants=p;
		this.gp=gp;
		lawn.getChildren().add(zombie);
	}
	
	protected void move(int i) {
		int y;
		zombie.setLayoutX(-100);
		zombie.setLayoutY(-100);
		Line hor= new Line();
		hor.setStartX(1300+150);
		hor.setStartY(y=(i*120+100));
		hor.setEndX(100);
		hor.setEndY(y);
		PathTransition tr= new PathTransition();
		tr.setNode(zombie);
		tr.setDuration(Duration.seconds(40));
		tr.setPath(hor);
		tr.setDelay(Duration.seconds(4));
		tr.setCycleCount(Animation.INDEFINITE);
		tr.play();
		if(this.health>0) eat(tr);
	}
	
	Pane getPane() {
		return zombie;
	}
	
	protected void eat(PathTransition tr) {
		Timeline t= new Timeline(new KeyFrame(Duration.seconds(1), e-> {
			System.out.println("size ----- "+plants.size());
			double y=zombie.getBoundsInParent().getCenterY()-20;
			double x=zombie.getBoundsInParent().getCenterX();
			Iterator<Plant> p = plants.iterator();
			boolean flag=true;
			while(p.hasNext()) {
				Plant p_=p.next();
				System.out.println(y+" "+p_.tile.getX());
				if(((int)y/100==p_.tile.getY())&&((x<=(p_.tile.getX()*90+400))&&(x>=(p_.tile.getX()*90+300))&&this.health>0)) {
					System.out.println("yiepeee");
					
					tr.pause();
					p_.health-=20;
					if(p_.health<0) {
						p_.eaten(this);
						p_.tile.removePlant();
						gp.getChildren().remove(p_.getPlant());
						plants.remove(p_);
						tr.play();
					}
				}
			}
		}));
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
	}

	public void remove() {
		lawn.getChildren().remove(this);
	}

}
