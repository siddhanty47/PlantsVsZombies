package PVZ;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class sunflower extends Plant implements Serializable{
	private final int sunProduceTime=20;
	private Pane sunf;
	private Sun sun;
	private Pane lbg;
	private boolean recharging=false;
	private final ImageView p_img= new ImageView(new Image("resource/sunflower.gif"));
	private boolean collected=true;
	private SunCount token;
	private Timeline fiveSeconds;
	sunflower(Tile tile,Pane lbg,SunCount t){
		super();
		p_img.setFitHeight(90);
		p_img.setFitWidth(90);
		this.level=2;
		this.cost=50;
		this.health=100;
		this.rechargeTime=10;
    	sunf= new Pane(p_img);
    	super.tile=tile;
    	this.lbg=lbg;
    	this.token=t;
    	this.sun=new Sun(lbg,t);
	}
	
	@Override
	protected Pane getPlant() {
		return sunf;
	}
	
	void GenerateSuns(){
		fiveSeconds = new Timeline(new KeyFrame(Duration.seconds(sunProduceTime), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if(collected||sun.isCollected()) {
		    		sun=new Sun(lbg,token);
		    		collected=false;
		        	System.out.println("this is called every 5 seconds on UI thread");
		        	sun.emerges(tile.getX(), tile.getY());
		        }
		    }
		}));
		fiveSeconds.setCycleCount(Timeline.INDEFINITE);
		fiveSeconds.play();
	}
	
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
		//fiveSeconds.setOnFinished(e -> {System.out.println("recharge done");recharging=false;});
	}
	
	boolean IsRecharging() {
		return recharging;
	}

	@Override
	protected void eaten(Zombies z) {
		// TODO Auto-generated method stub
		
	}
}
