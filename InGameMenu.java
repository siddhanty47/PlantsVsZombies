package PVZ;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class InGameMenu extends Controller implements Initializable ,Serializable{

	@FXML private Button save;
	@FXML private Button exit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		save.setOnMouseClicked(e->{
			System.out.println("clicked");
			save();
		});
	}

	@FXML
	private void save() {
		super.Save();
	}
	
	@FXML
	private void Exit() {
		System.exit(0);
	}
	
	
}
