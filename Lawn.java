package PVZ;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Lawn implements Serializable{
	private GridPane gp;
	private Tile[][] tiles; 
	private LawnMower[] lawnmower=new LawnMower[5];
	public Lawn(Pane lbg,ArrayList<Zombies> z,GridPane gp, ImageView lawnmower1, ImageView lawnmower2, ImageView lawnmower3, ImageView lawnmower4, ImageView lawnmower5) {
		this.gp=gp;
		tiles=new Tile[10][6];
		for(int i=0;i<10;i++) {
			for(int j=0;j<6;j++)
				tiles[i][j]=new Tile(i, j, gp);
		}
		lawnmower[0]= new LawnMower(1,lawnmower1,z,lbg);lawnmower[1]= new LawnMower(2,lawnmower2,z,lbg);lawnmower[2]= new LawnMower(3,lawnmower3,z,lbg);
		lawnmower[3]= new LawnMower(4,lawnmower4,z,lbg);lawnmower[4]= new LawnMower(5,lawnmower5,z,lbg);
		for(LawnMower l:lawnmower) { l.Attack();}
	}
	
	protected Tile[][] getTile(){
		return tiles;
	}

}
