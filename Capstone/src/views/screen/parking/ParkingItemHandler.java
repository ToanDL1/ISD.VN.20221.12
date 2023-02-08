package views.screen.parking;

import java.io.File;
import java.io.IOException;

import entity.parking.parking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Configs;
import views.fxml.bike.BikeScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

public class ParkingItemHandler extends BaseScreenHandler {
	@FXML
	   private Label parkingId;
	@FXML
	   private Label parkingAddress;
	@FXML
	private ImageView parkingImg;
	@FXML
	private Button parkingButton;
	
    private parking parkingItem;
	private ParkingScreenHandler parkingScreen;
	 
   public ParkingItemHandler(Stage stage,String screenPath, ParkingScreenHandler parkingScreen) throws IOException {
		super(stage,screenPath);
		this.parkingScreen = parkingScreen;
		// TODO Auto-generated constructor stub
	}

    public void setParkingItem(parking parkingItem) {
	   this.parkingItem = parkingItem;
	   displayParking();
   }
   
   public void displayParking() {
	   this.parkingId.setText(Integer.toString(parkingItem.getId()));
	   this.parkingAddress.setText(parkingItem.getAddress());
	   File file = new File(parkingItem.getImg());
       Image image = new Image(file.toURI().toString());
       System.out.println(parkingItem.getImg());
       parkingImg.setImage(image);
       parkingButton.setOnMouseClicked(e->{
    	   try {
    		    System.out.println("chuyen man sang bike");
    		    BikeScreenHandler bikeScreen = new BikeScreenHandler(this.stage, Configs.BIKE_LIST_PATH, parkingItem);
    		    bikeScreen.setHomeScreenHandler(homeScreenHandler);
    		    bikeScreen.show();
    		    
				
		} catch (Exception e2) {
			e2.printStackTrace();
		}
       });
   }
      

}
