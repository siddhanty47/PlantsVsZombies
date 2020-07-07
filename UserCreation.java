package PVZ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserCreation extends PlantsVsZombies implements Serializable, Initializable {
	private static final long serialVersionUID = 1L;
	@FXML private Button submit;
	@FXML private TextField text;
	protected boolean new_u=false;
	protected User user;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	@FXML
	public void onEnter(ActionEvent event)  {
		String s=text.getText();
		try {
			ObjectInputStream in=null;
			ObjectOutputStream out_user=null;
		    ObjectOutputStream out_present=null;
			if(!userExist(s)) {
				File file = new File("user.txt");
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter writer = new BufferedWriter(fr);
				writer.write(s+"\n");
			    writer.close();
			    user= new User(s);
				super.users.add(user);
				out_user=new ObjectOutputStream (new FileOutputStream(s+".txt"));
				out_user.writeObject(user);
				out_present=new ObjectOutputStream (new FileOutputStream("present.txt"));
				out_present.writeObject(user);
				out_user.close();
				out_present.close();
				
			}
			else {
				
				try {
					in= new ObjectInputStream( new FileInputStream(s+".txt"));
					user= (User) in.readObject();
					out_present=new ObjectOutputStream (new FileOutputStream("present.txt"));
					out_present.writeObject(user);
				}catch (FileNotFoundException e1) {
					System.out.println("1");
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("2");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("3");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					in.close();
					out_present.close();
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("1");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("3");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Parent root= FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene=new Scene(root);
			Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean userExist(String s) throws IOException {
		BufferedReader in=null;
		try {
			in=new BufferedReader(new FileReader("user.txt"));
			String l;
			while((l=in.readLine())!=null) {
				if(l.equals(s)) {
					in.close();
					return true;
				}
			}
		}
		finally {
			in.close();
		}
		return false;
	}
	
}
