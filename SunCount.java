package PVZ;

import java.io.Serializable;

import javafx.scene.control.Label;

public class SunCount implements Serializable {
	private int TotalSun=50;
	private Label suns;
	
	public SunCount(Label label) {
		this.suns=label;
	}

	protected void Spend(int num) throws OutOfSunException {
		if(num<=TotalSun)
		this.TotalSun-=num;
		else System.out.println("out of bound");
		suns.setText(Integer.toString(TotalSun));
	}
	
	protected void AddSun(int sun) {
		TotalSun+=sun;
		System.out.println("inside");
		suns.setText(Integer.toString(TotalSun));
	}
	
	protected int getTotal() {
		return TotalSun;
	}
	
}
