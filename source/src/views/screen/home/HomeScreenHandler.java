package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import entity.db.EcoDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import utils.DbCheck;
import views.screen.BaseScreenHandler;
import views.screen.backBike.GiveBackBikeHandler;
import views.screen.backBike.GiveBackBikeHandler;
import views.screen.parking.ParkingScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rent.BarCodeScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler  implements Initializable{
	@FXML
	private Button viewParking;
	@FXML
	private Button rentButton;
	@FXML
	private Button backButton;
	
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
			  
		});
          
		  rentButton.setOnMouseClicked(e->{
			  
			     try {
					if(DbCheck.checkRentbike()== false) {
						 BarCodeScreenHandler barCodeScreen = new BarCodeScreenHandler(this.stage,Configs.BARCODE_SCREEN_PATH);
							barCodeScreen.show();
					   }else {
						   String renting = "bạn đang mượn xe";
						   PopupScreen.error(renting);
					   }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    });
		
		  backButton.setOnMouseClicked(e ->{
			  try {
				if(DbCheck.checkRentbike()== false) {
					  String mess = "Bạn chưa mượn xe";
					  PopupScreen.error(mess);
				  }else {
					  LocalDateTime timeBackBike = LocalDateTime.now();
//					  System.out.println("Before formatting: " + timeBackBike);
					  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//					  System.out.println(timeBackBike.format(myFormatObj));
					  String sql = "update rent_bike set endAt = "  
                                   + "\""+ timeBackBike.format(myFormatObj) +"\""
                                   + "where status = 1"+";";
					  Statement stm = EcoDB.getConnetttion().createStatement();
				      int res = stm.executeUpdate(sql);
				      
				      GiveBackBikeHandler backBike = new GiveBackBikeHandler(stage, Configs.GIVE_BACK_BIKE_PATH);
				      backBike.show();
				  }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  });
	}
}
