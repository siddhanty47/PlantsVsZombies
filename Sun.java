package PVZ;

import java.io.Serializable;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Sun implements Character,Serializable{
	private boolean collected;
	protected final int value=25;
	private final Image sunimg =new Image("resource/Sun2.png");
	private ImageView suniv;
	private Pane p;
	private Pane lbg;
	private SunCount token;
	double x,y;
	
	Sun(Pane lbg,SunCount t){
		collected=false;
		suniv = new ImageView();
		suniv.setFitHeight(60);
		suniv.setFitWidth(60);
		suniv.setImage(sunimg);
		p=new Pane();
		p.setLayoutX(-60);
		p.setLayoutY(-60);
		p.getChildren().add(suniv);
		this.lbg=lbg;
		this.token=t;
	}
	
	protected void fall() {
		lbg.getChildren().add(p);
		//p.toFront();
		Random r= new Random();
		int num2=0;
		Line line = new Line();
		line.setStartX(num2=r.nextInt(900)+100);
		line.setStartY(0);
		line.setEndX(num2);
		line.setEndY(500);
		PathTransition transition = new PathTransition();
		transition.setNode(p);
		transition.setDuration(Duration.seconds(5));
		transition.setPath(line);
		transition.setDelay(Duration.seconds(9));
		
		p.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	collected=true;
		    	token.AddSun(25);
		        transition.stop();
		    	x=event.getSceneX();
		        y=event.getSceneY();
		    	System.out.println(x+" "+y);
				line.setStartX(x+30);
				line.setStartY(y+30);
				line.setEndX(250);
				line.setEndY(0);
				PathTransition transition2 = new PathTransition();
				transition2.setNode(p);
				transition2.setDuration(Duration.seconds(1));
				transition2.setPath(line);
				transition2.play();	
		    }
		    
		});
		transition.play();
	}
	
	protected void emerges(double a,double b){
		lbg.getChildren().add(p);
		Line line =new Line();
		p.setLayoutX((a+2)*101);
		p.setLayoutY((b)*114);
		p.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	collected=true;
		    	System.out.println("outside");
		    	token.AddSun(25);
		    	x=event.getSceneX();
		        y=event.getSceneY();
		    	System.out.println(x+" "+y);
				line.setStartX(0);
				line.setStartY(0);
				line.setEndX(-(a)*101);
				line.setEndY(-((b)*114)-20);
				PathTransition transition2 = new PathTransition();
				transition2.setNode(p);
				transition2.setDuration(Duration.seconds(1.3));
				transition2.setPath(line);
				transition2.play();	
		    }
		    
		});
	}
	
	protected boolean isCollected() {
		return collected;
	}

	public Pane getPane() {
		// TODO Auto-generated method stub
		return p;
	}
}
