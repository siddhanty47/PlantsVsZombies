package PVZ;

import java.io.Serializable;

import javafx.scene.layout.Pane;

public abstract class Plant implements Character,Serializable{
	protected double health;
	protected int level;
	protected int cost;
	protected Tile tile;
	protected int rechargeTime;
	protected int getLevel() {
		return level;
	}
	abstract protected Pane getPlant();
	abstract void recharge();
	abstract boolean IsRecharging();
	protected abstract void eaten(Zombies z);
}
