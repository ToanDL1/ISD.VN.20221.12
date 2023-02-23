package views.screen.backBike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;

import entity.Invoice.Invoice;
import entity.db.EcoDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.successNoti.SuccessNotiHandler;

public class GiveBackBikeHandler extends BaseScreenHandler implements Initializable {
	@FXML
	private Label bikeId;
	@FXML
	private Label bikeTime;
	@FXML
	private Label postage;
	@FXML
	private Label refund;
	@FXML
	private Button btnBackBike;
	@FXML
	private ImageView bikeImg;
	
	private Invoice invoiceBike;
	

	public GiveBackBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.invoiceBike = Invoice.getInvoice();
			bikeId.setText(Integer.toString(invoiceBike.getBikeInvoice().getId()));
			postage.setText(Long.toString(invoiceBike.getMoneyRent()));
			bikeTime.setText(Long.toString(invoiceBike.getTotalTime()) +"h");
			refund.setText(Integer.toString(invoiceBike.getDeposit()));
			File file = new File(invoiceBike.getBikeInvoice().getImg());
		    Image image = new Image(file.toURI().toString());
		    bikeImg.setImage(image);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnBackBike.setOnMouseClicked(e->{
			try {
				
				PaymentBackScreenHandler paymentBackScreen= new PaymentBackScreenHandler(stage,Configs.PAYMENT_FORM_BACK_PATH,invoiceBike);
				paymentBackScreen.show();
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	
	}

	
	
	

}
