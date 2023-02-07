package views.screen.parking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.parking.parking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class ParkingScreenHandler extends BaseScreenHandler {
	@FXML
    Button homeView;
	@FXML
	ImageView parking1Img;
	@FXML
	ImageView parking2Img;
	@FXML
	ImageView parking3Img;
	public ParkingScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		
//		Trở về màn hình chính
		homeView.setOnMouseClicked(e->{
			homeScreenHandler.show();
		});
		
		try {
			ArrayList<parking> parkingList = parking.getAllParking();
			setImgParking(parkingList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	
	public void setImgParking(ArrayList<parking> parkingList) {
		
	}
	
        
}
