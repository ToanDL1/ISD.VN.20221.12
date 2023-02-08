package views.fxml.bike;

import java.io.IOException;

import entity.parking.parking;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

public class BikeScreenHandler extends BaseScreenHandler {
     private parking parking;
	public BikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	public BikeScreenHandler(Stage stage, String screenPath, parking parking) throws IOException {
		super(stage, screenPath);
		this.parking = parking;
		// TODO Auto-generated constructor stub
	}
	

}
