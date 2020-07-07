package PVZ;

import java.io.Serializable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Wallnut extends Plant implements Serializable{
	private Pane wall;
	private ImageView p_img= new ImageView(new Image("resource/Wallnut.png"));
	private boolean recharging;
	
	Wallnut(Tile tile){
		super();
		this.level=3;
		this.cost=50;
		this.health=450;
		this.rechargeTime=20;
		p_img.setFitHeight(80);
    	p_img.setFitWidth(80);
    	wall = new Pane(p_img);
    	this.tile=tile;
	}
	
	protected Pane getPlant() {
		return wall;
	}

	@Override
	void recharge() {
		recharging=true;
		System.out.println("recharging");
		Timeline fiveSeconds = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        	System.out.println("recharging done");
		        	recharging=false;
		    }
		}));
		fiveSeconds.setCycleCount(2);
		fiveSeconds.play();
		
	}

	@Override
	boolean IsRecharging() {
		return recharging;
	}


	@Override
	protected void eaten(Zombies z) {
		// TODO Auto-generated method stub
		
	}
}
