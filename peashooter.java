package PVZ;

import java.io.Serializable;
import java.util.ArrayList;
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

public class peashooter extends Plant implements Attacking,Serializable{
	private Pea pea;
	private Pane peashotr;
	private Pane lbg;
	private final ImageView p_img= new ImageView(new Image("resource/peashooter.gif"));
	private boolean recharging;
	private ArrayList<Zombies> zombies;
	private Timeline attack;
	peashooter(Tile tile,Pane lbg,ArrayList<Zombies> zombies){
		super();
		this.level=1;
		this.cost=100;
		this.health=100;
		this.rechargeTime=10;
		this.lbg=lbg;
		p_img.setFitHeight(100);
    	p_img.setFitWidth(100);
    	peashotr=new Pane(p_img);
    	pea=new Pea();
		this.tile=tile;
		this.zombies=zombies;
	}
	
	protected Pane getPlant() {
		return peashotr; 
	}
	
	@Override
	public void Attack() {
		attack = new Timeline(new KeyFrame(Duration.millis(250),e-> {
			Iterator<Zombies> z=zombies.iterator();
			while(z.hasNext()) {
				Zombies z_=z.next();
				if(z_.getPane().getBoundsInParent().intersects(pea.getPane().getBoundsInParent())) {
					System.out.println("hit it!!");
					z_.health-=10;
					if(z_.health<0) {
						lbg.getChildren().remove(z_.getPane());
						System.out.println("killed");
					}
				}
			}
		}));
		attack.setCycleCount(Animation.INDEFINITE);
		attack.setDelay(Duration.seconds(5));
		attack.play();
		pea.shoot(tile.getX(),tile.getY(),lbg);
		
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
		lbg.getChildren().remove(pea.getPane());
		pea.tr.stop();
		attack.stop();
	}
	
}
