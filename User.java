package PVZ;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private int level;
	private House house;
	
	User(String s){
		this.name=s;
		house=new House();
	}
	
	void levelUp() {
		this.level++;
	}
	int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}
	
	protected void setHouse(House h) {
		this.house=h;
	}
}
