package PVZ;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class Tile implements Serializable{
	private Plant plant;
	private ArrayList<Zombies> zombies;
	private int x;
	private int y;
	private GridPane lawn;
	
	Tile(int x,int y,GridPane lawn){
		this.x=x;
		this.y=y;
		this.lawn=lawn;
	}
	
	void addPlant(Plant p) {
		this.plant=p;
		lawn.add(p.getPlant(), x, y);
	}
	
	void addZombie(Zombies z) {
		zombies.add(z);
	}
	
	Plant getPlant() {
		return this.plant;
	}
	
	ArrayList<Zombies> getZombie(){
		return zombies;
	}
	
	void removePlant() {
		this.plant=null;
	}
	
	void removeZombie() {
		
	}
	
	int getX() {return x;} int getY() {return y;}
}
