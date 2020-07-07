package PVZ;

import javafx.scene.layout.Pane;

public class potatomine extends Plant implements Attacking{

	public potatomine() {
		super();
		this.level=3;
		this.cost=100;
		this.health=100;
		this.rechargeTime=10;
	}

	@Override
	public void Attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Pane getPlant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void recharge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean IsRecharging() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void eaten(Zombies z) {
		// TODO Auto-generated method stub
		
	}

}
