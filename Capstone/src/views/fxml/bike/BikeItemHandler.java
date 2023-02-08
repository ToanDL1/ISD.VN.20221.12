package views.fxml.bike;

import java.io.File;
import java.io.IOException;

import entity.bike.bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

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
	public void setBikeItem(bike bikeItem) {
		this.bikeItem = bikeItem;
		displayBike();
	}
	public void displayBike() {
		 this.bikeId.setText(Integer.toString(bikeItem.getId()));
		   this.bikePrice.setText(Integer.toString(bikeItem.getPrice()));;
		   File file = new File(bikeItem.getImg());
	       Image image = new Image(file.toURI().toString());
//	       System.out.println(parkingItem.getImg());
	       bikeImg.setImage(image);
	}

}
