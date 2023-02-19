package views.screen.backBike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import entity.card.card;
import entity.db.EcoDB;
import entity.payment.payment;
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
	
	private payment paymentBike;
	

	public GiveBackBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			this.paymentBike = payment.getPayment();
			bikeId.setText(Integer.toString(paymentBike.getBikeid()));
			postage.setText(Integer.toString(paymentBike.getPostage()));
			bikeTime.setText(Integer.toString(paymentBike.getTime()) +"h");
			refund.setText(Integer.toString(paymentBike.getRefund()));
			File file = new File(paymentBike.getImg());
		    Image image = new Image(file.toURI().toString());
		    bikeImg.setImage(image);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnBackBike.setOnMouseClicked(e->{
			try {
				updateCard();
				updateRentBike();
				SuccessNotiHandler successNotiPayment = new SuccessNotiHandler(stage, Configs.SUCC_NOTI_PATH);
				successNotiPayment.show();
				successNotiPayment.setNoti("Chúc mừng bạn đã trả xe thành công");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
	
	}




	private void updateCard() throws SQLException {
		// TODO Auto-generated method stub
		card creditCard = card.getcard();
		int amount = creditCard.getBalance()+this.paymentBike.getRefund();
		String sql = "update card set balance = " + amount +";";
	    Statement stm = EcoDB.getConnetttion().createStatement();
	    int res = stm.executeUpdate(sql);
	}




	private void updateRentBike() throws SQLException {
		// TODO Auto-generated method stub
	 String sql = "update rent_bike set status = 0 where status = 1";
	 Statement stm = EcoDB.getConnetttion().createStatement();
     int res = stm.executeUpdate(sql);
	}
	
	

}
