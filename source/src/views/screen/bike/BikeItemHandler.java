
package views.screen.bike;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import entity.bike.bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.DbCheck;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rent.BarCodeScreenHandler;

public class BikeItemHandler extends BaseScreenHandler {
	@FXML
	private Label bikeId;
	@FXML
	private Label bikePrice;
	@FXML
	private ImageView bikeImg;
	@FXML
	private Button bikeButton;
	
    private entity.bike.bike bikeItem;
    private BikeScreenHandler BikeScreen;
	public BikeItemHandler(Stage stage, String screenPath, BikeScreenHandler BikeScreen) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		this.BikeScreen = BikeScreen;
	}
	public void setBikeItem(bike bikeItem) throws SQLException {
		this.bikeItem = bikeItem;
		displayBike();
	}
	public void displayBike() throws SQLException {
		 this.bikeId.setText(Integer.toString(bikeItem.getId()));
		   this.bikePrice.setText(Integer.toString(bikeItem.getPrice()));;
		   File file = new File(bikeItem.getImg());
	       Image image = new Image(file.toURI().toString());
//	       System.out.println(parkingItem.getImg());
	       bikeImg.setImage(image);
	       
	       bikeButton.setOnMouseClicked(e -> {
	    	   try {
				if (DbCheck.checkRentbike()== false) {
					  
						System.out.println("Chuyen sang dien barcode");
						setBarCode(bikeItem.getId());
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
	   
	}
   
	public void setBarCode(int barcode) throws IOException {
		BarCodeScreenHandler barCodeScreen = new BarCodeScreenHandler(this.stage,Configs.BARCODE_SCREEN_PATH, barcode);
		barCodeScreen.show();
		barCodeScreen.setBarCode(barcode);
	}
}
