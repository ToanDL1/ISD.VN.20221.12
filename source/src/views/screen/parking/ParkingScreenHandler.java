package views.screen.parking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import entity.parking.parking;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

public class ParkingScreenHandler extends BaseScreenHandler {
	@FXML
    private Button homeView;
	@FXML
	private VBox parkingListVbox;
	
	public ParkingScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		
//		Trở về màn hình chính
		homeView.setOnMouseClicked(e->{
			homeScreenHandler.show();
		});
		
		try {
			ArrayList<parking> parkingList = parking.getAllParking();
			showAllDock(parkingList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	
	public void showAllDock(ArrayList<parking> parking ) {
		this.parkingListVbox.getChildren().clear();
		try {
			parking.forEach((item) ->{
				try {
					ParkingItemHandler parkingItem = new ParkingItemHandler (this.stage,Configs.PARKING_ITEM_PATH,this);
					parkingItem.setParkingItem(item);
					this.parkingListVbox.getChildren().add(parkingItem.getContent());
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
