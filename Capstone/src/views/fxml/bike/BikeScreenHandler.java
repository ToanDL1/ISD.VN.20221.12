package views.fxml.bike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.bike.bike;
import entity.parking.parking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.parking.ParkingItemHandler;

public class BikeScreenHandler extends BaseScreenHandler {
	@FXML
	private VBox bikeListVbox;
	@FXML
	private Button homeView;
	
     private parking parking;
      
     
	public BikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	public BikeScreenHandler(Stage stage, String screenPath, parking parking) throws IOException {
		super(stage, screenPath);
		this.parking = parking;
		// TODO Auto-generated constructor stub
		// trở về màn hình chính
		homeView.setOnMouseClicked(e->{
			try {
				HomeScreenHandler homeScreen = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
				homeScreen.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		try {
			ArrayList<bike> bikeList = bike.getBikeInParking(parking.getId());
			showAllBike(bikeList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAllBike(ArrayList<bike> bikeList) {
		this.bikeListVbox.getChildren().clear();
		try {
			bikeList.forEach((item) ->{
				try {
					BikeItemHandler bikeItem = new BikeItemHandler (this.stage,Configs.BIKE_ITEM_PATH,this);
					bikeItem.setBikeItem(item);
					this.bikeListVbox.getChildren().add(bikeItem.getContent());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		
}
