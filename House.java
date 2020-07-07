package PVZ;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class House implements Serializable{
	private boolean Safe=true;
	private Lawn lawn;
	
	boolean isSafe() {
		return Safe;
	}
	
	boolean AllDead() {
		return false;
	}
	
	protected void setLawn(Pane lbg,ArrayList<Zombies> z,GridPane gp, ImageView lawnmower1, ImageView lawnmower2, ImageView lawnmower3, ImageView lawnmower4, ImageView lawnmower5) {
		lawn= new Lawn(lbg,z,gp,lawnmower1,lawnmower2,lawnmower3,lawnmower4,lawnmower5);
	}

	public Lawn getLawn() {
		return lawn;
	}
}
