package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.parking.ParkingScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler  implements Initializable{
	@FXML
	private Button viewParking;
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
	    super(stage, screenPath);
//	    this.setHomeScreenHandler(this);
	  }

	  @Override
	  public void show() {
	    super.show();
	  }
  
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  viewParking.setOnMouseClicked(e ->{
			  System.out.println("hahah");
			  try {
				ParkingScreenHandler parkingScreen = new ParkingScreenHandler(this.stage, Configs.VIEW_PARKING_PATH);
				parkingScreen.setHomeScreenHandler(this);
				parkingScreen.show();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		  );
		
	}
}
