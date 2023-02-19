package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import entity.bike.bike;
import entity.card.card;
import entity.db.EcoDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.successNoti.SuccessNotiHandler;

public class PaymentSceenHandler extends BaseScreenHandler  {
	private bike bikeRent;
	@FXML
	private Label costLabel;
	@FXML
	private TextField cardOwner;
	@FXML
	private TextField cardNumber;
	@FXML
	private TextField cvvCode;
	@FXML
	private TextField expirationDate;
	@FXML
	private Button btnConfirmPayment;

	@FXML 
	private ResourceBundle resources;

	
	public PaymentSceenHandler(Stage stage, String screenPath,bike bikeRent) throws IOException {
		super(stage, screenPath);
		this.bikeRent = bikeRent;
		// TODO Auto-generated constructor stub
		btnConfirmPayment.setOnMouseClicked(e ->{
			try {
				paymentConfirm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public void setCost() {
		int cost = (int) (bikeRent.getPrice() * 0.4);
		costLabel.setText(Integer.toString(cost) + "VND");
	}
	private void paymentConfirm() throws SQLException, IOException {
		card creditCard = card.getcard();
		String owner = this.cardOwner.getText();
		String cardNumber = this.cardNumber.getText();
		String cvvCode =  this.cvvCode.getText();
		String dateExpried = this.expirationDate.getText();
		System.out.println(""+ creditCard.getOwner() +" " + creditCard.getCardCode());
		boolean check =creditCard.checkCard(owner, cardNumber, cvvCode, dateExpried);
		if (check == false) {
			String errorMess = "Thẻ không chính xác, vui lòng nhập lại";
			PopupScreen.error(errorMess);
		}else if (creditCard.getBalance() < this.bikeRent.getPrice()*0.4) {
			String errorMess = "Thẻ của bạn không đủ tiền";
			PopupScreen.error(errorMess);
		}else {
			updateRentbike();
			updateCard(creditCard);
			showSuccNoti();
		}
		
	}


	private void updateRentbike() throws SQLException {
		
		    String sql = "insert into rent_bike (bikeId,status) values( "
		    		      +this.bikeRent.getId()+ ",1);";
			Statement stm = EcoDB.getConnetttion().createStatement();
			int res = stm.executeUpdate(sql);
			System.out.println(res);
	}
	
	private void updateCard(card creditCard) throws SQLException {
		 int amount = (int) (creditCard.getBalance()-this.bikeRent.getPrice()*0.4);
		 System.out.println(amount);
		  String sql = "update card set balance = " + amount +";";
	      Statement stm = EcoDB.getConnetttion().createStatement();
	       int res = stm.executeUpdate(sql);
		System.out.println(res);
	}
	
    private void showSuccNoti() throws IOException {
              SuccessNotiHandler succPayment = new SuccessNotiHandler(stage, Configs.SUCC_NOTI_PATH);
              succPayment.setNoti("Bạn đã mượn xe thành công");
              succPayment.show();
              
    }

	

	
}
