package PVZ;

import java.io.Serializable;
import java.util.Iterator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class cherrybomb extends Plant implements Attacking,Serializable{

	private final ImageView p_img= new ImageView(new Image("resource/potato_mine.gif"));
	private Pane p;
	private boolean recharging;
	private boolean eaten_;
	private Pane lbg;
	private Timeline attack;
	private Zombies zombie;
	
	cherrybomb(Tile tile,Pane lbg) {
		super();
		this.level=5;
		this.cost=25;
		this.health=100;
		this.rechargeTime=20;
		p_img.setFitHeight(100);
    	p_img.setFitWidth(100);
    	p= new Pane(p_img);
    	this.tile=tile;
    	this.lbg=lbg;
	}

	protected Pane getPlant() {
		return p;
	}
	
	@Override
	public void Attack() {
		attack = new Timeline(new KeyFrame(Duration.millis(250),e-> {
				if(eaten_) {
					System.out.println("booomb!!");
					eaten_=false;
					zombie.health=0;
					if(zombie.health<=0) {
						lbg.getChildren().remove(zombie.getPane());
						System.out.println("killed");
					}
				}
		}));
		attack.setCycleCount(Animation.INDEFINITE);
		attack.setDelay(Duration.seconds(5));
		attack.play();
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

	protected void eaten(Zombies z) {
		// TODO Auto-generated method stub
		eaten_=true;
		this.zombie=z;
	}

}
