package PVZ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlay implements Serializable{
	private Lawn l;
	private Label label;
	private Label time;
	private Label winLabel;
	private SunCount token;
	private Tile[][] tile;
	private House h;
	private GridPane lawn;
	private User player;
	private Pane lbg;
	private ImageView sunflower_img;
	private ImageView peashooter_img;
	private ImageView wallnut_img;
	private ImageView cherrybomb_img;
	private boolean flag_s,flag_p,flag_c,flag_w=false;
	private ImageView menu;
	private sunflower sunflower=new sunflower(null, lbg,null);
	private peashooter peashooter= new peashooter(null, lbg,null);
	private Wallnut wallnut= new Wallnut(null);
	private cherrybomb cherrybomb= new cherrybomb(null,null);
	private Zombies zombie;int i=0;Random r = new Random();
	private ArrayList<Plant> plants;
	private ArrayList<Zombies> zombies_l;
	private double time_t=30;
	private ImageView Lawnmower1;private ImageView Lawnmower2;
	private ImageView Lawnmower3;private ImageView Lawnmower4;private ImageView Lawnmower5;
	private int zombieNumber=10;
	
	GamePlay(GridPane lawn,Pane lbg,ImageView menu,
			ImageView sunflower, ImageView peashooter,
			ImageView wallnut, ImageView cherrybomb, Label label,Label Time,Label winLabel, ImageView Lawnmower1, ImageView Lawnmower2, ImageView Lawnmower3, ImageView Lawnmower4, ImageView Lawnmower5){
		this.cherrybomb_img=cherrybomb;
		this.lawn=lawn;this.Lawnmower1=Lawnmower1;this.Lawnmower2=Lawnmower2;
		this.Lawnmower3=Lawnmower3;this.Lawnmower4=Lawnmower4;this.Lawnmower5=Lawnmower5;
		this.peashooter_img=peashooter;
		this.lbg=lbg;
		this.sunflower_img=sunflower;
		this.wallnut_img=wallnut;
		this.menu=menu;
		this.time=Time;
		this.label=label;
		this.winLabel=winLabel;
		token=new SunCount(this.label);
		plants= new ArrayList<Plant>();
		zombies_l=new ArrayList<Zombies>();
	}
	
	User getUser() {return player;}
	
	protected void Start() {
		time.setText(Integer.toString(zombieNumber-zombies_l.size()));
		ObjectInputStream in=null;
		try {
			in= new ObjectInputStream( new FileInputStream("present.txt"));
			player=(User) in.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("name: "+player.getName());
		h = new House();
		player.setHouse(h);
		h.setLawn(lbg,zombies_l,lawn,Lawnmower1,Lawnmower2,Lawnmower3,Lawnmower4,Lawnmower5);
		l=h.getLawn();
		tile=l.getTile();
		KeyValue Lawn = new KeyValue(lawn.opacityProperty(),0,Interpolator.EASE_OUT);
		KeyFrame kf = new KeyFrame(Duration.seconds(1),Lawn);
		KeyFrame delay=new KeyFrame(Duration.seconds(4));
		KeyValue rt = new KeyValue(lbg.translateXProperty(),-444,Interpolator.EASE_IN);
		KeyFrame right = new KeyFrame(Duration.seconds(3), rt);
		Timeline t=new Timeline(kf,delay,right);
		t.setCycleCount(2);
		t.setDelay(Duration.seconds(2));
		t.setAutoReverse(true);
		t.play();
		Timeline suns= new Timeline(new KeyFrame(Duration.seconds(15), ev-> {
			Sun s=new Sun(lbg,token);
			s.fall();
		}));
		suns.setCycleCount(Animation.INDEFINITE);
		suns.play();
		GenerateZombies();
		menu.setOnMouseClicked(e->{
			System.out.println("clicked");
			InGameMenu(e);
		});
		sunflower_img.setOnMouseClicked(e-> {
			if(!sunflower.IsRecharging()) {
				System.out.println("Plant fast");
				flag_s=true;}
			else {System.out.println("No boi! its recharging!");}
			
		});
		peashooter_img.setOnMouseClicked(e-> {
			if(!peashooter.IsRecharging()) {
				System.out.println("Plant fast");
				flag_p=true;}
			else {System.out.println("No boi! its recharging!");}
		});
		wallnut_img.setOnMouseClicked(e-> {
			if(!wallnut.IsRecharging()) {
				System.out.println("Plant fast");
				flag_w=true;}
			else {System.out.println("No boi! its recharging!");}
		});
		cherrybomb_img.setOnMouseClicked(e-> {
			if(!cherrybomb.IsRecharging()) {
				System.out.println("Plant fast");
				flag_c=true;}
			else {System.out.println("No boi! its recharging!");}
		});
		
		Timeline t2=new Timeline(new KeyFrame(Duration.seconds(1), e->   {
			for(Zombies z : zombies_l) {
				double x=z.getPane().getBoundsInParent().getMaxX();
				System.out.println("posision########## "+ x);
				if(x<300&&x>200&&z.health>0)
					LostGame();
			}
		}));
		t2.setCycleCount(Animation.INDEFINITE);
		t2.play();
	}
	
	protected void InGameMenu(MouseEvent e) {
		Save();
		try {
			Parent root= FXMLLoader.load(getClass().getResource("InGameMenu.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)e.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	void GenerateZombies() {
		Timeline zombies= new Timeline(new KeyFrame(Duration.seconds(time_t), ev-> {
			zombie = new Zombies(lbg,plants,lawn);
			zombies_l.add(zombie);
			time_t/=2;
			boolean flag=false;
			i=r.nextInt(6);
			while(i<1) {i=r.nextInt(6);}
			System.out.println(i);
			if(!winLabel.getText().equals("0")) zombie.move(i);
			time.setText(Integer.toString(zombieNumber-zombies_l.size()));
			if(Integer.parseInt(time.getText())<=0) {
				try {
					int sum=0;
					for(Zombies z : zombies_l) {
						sum+=z.health;flag=true;
					}if(sum<=0&&flag) {EndGame(true);}
			} catch (GameWinnerException | GameLostException e) {
				System.out.println(e.getMessage());
				WinGame();
			}};
		}));
		zombies.setCycleCount(Animation.INDEFINITE);
		zombies.play();
	}

	private void WinGame() {
		winLabel.toFront();winLabel.setText("WINNER");
	}
	
	private void LostGame() {
		winLabel.toFront();winLabel.setText("GAME LOST");
	}

	void plant(MouseEvent e) throws OutOfSunException {
		
		Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        int x=colIndex.intValue();
        int y=rowIndex.intValue();
        System.out.println( x+" "+y);
        if(flag_s) {
        	if(token.getTotal()>=sunflower.cost) {
				sunflower=new sunflower(tile[x][y],lbg,token);
				System.out.println(sunflower.getPlant().getBoundsInParent().getCenterX());
				System.out.println(sunflower.getPlant().getBoundsInParent().getCenterY());
				plants.add(sunflower);
				sunflower.recharge();
				flag_s=false;
	        	tile[x][y].addPlant(sunflower);
	        	sunflower.GenerateSuns();
	        	token.Spend(sunflower.cost);
        	}
        }
        else if(flag_p) {
        	if(token.getTotal()>=peashooter.cost) {
	        	peashooter= new peashooter(tile[x][y], lbg,zombies_l);
	        	plants.add(peashooter);
	        	peashooter.recharge();
	        	flag_p=false;
	        	tile[x][y].addPlant(peashooter);
	        	peashooter.Attack();
	        	token.Spend(peashooter.cost);
        	}
        }
        else if(flag_w) {
        	if(token.getTotal()>=wallnut.cost) {
	        	wallnut = new Wallnut(tile[x][y]);
	        	plants.add(wallnut);
	        	wallnut.recharge();
	        	flag_w=false;
	        	tile[x][y].addPlant(wallnut);
	        	token.Spend(wallnut.cost);
        	}
        }
        else if(flag_c) {
        	if(token.getTotal()>=cherrybomb.cost){
	        	cherrybomb= new cherrybomb(tile[x][y],lbg);
	        	plants.add(cherrybomb);
	        	cherrybomb.recharge();
	        	cherrybomb.Attack();
	        	flag_c=false;
	        	tile[x][y].addPlant(cherrybomb);
	        	token.Spend(cherrybomb.cost);
	        }
        }
	}
	
	void Save() {
		/*ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream (new FileOutputStream(player.getName()+"_1.txt"));
			out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	void EndGame(Boolean win) throws GameWinnerException, GameLostException {
		if(win) throw new GameWinnerException("win!!!");
		else throw new GameLostException("lost");
	}
	
	void LevelUp() {
		
	}
	
}
