package views.screen.backBike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import controller.PaymentController;
import entity.Invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.successNoti.SuccessNotiHandler;

public class PaymentBackScreenHandler extends BaseScreenHandler {
	
	@FXML
	private Label refundLabel;
	
	@FXML
	private Label moneyLabel;
	
	@FXML
	private Button btnConfirmPayment;
	
	private Invoice invoice;

	public PaymentBackScreenHandler(Stage stage, String screenPath , Invoice invoice) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		this.invoice = invoice;
		
		try {
			refundLabel.setText(Integer.toString(invoice.getDeposit()) + " VND");
			moneyLabel.setText(Long.toString(invoice.getMoneyRent())+ "VND");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		});
	}
	



	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;

	void confirmToPayOrder() throws IOException, SQLException{
		String contents = "pay order";
		PaymentController ctrl = new PaymentController();
		int amount =(int) (invoice.getMoneyRent()) - invoice.getDeposit();
		Map<String, String> response = ctrl.payOrder(amount, contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText());
		System.out.println("result:" + response.get("RESULT"));
		System.out.println("message: " + response.get("MESSAGE"));
		ctrl.updateRentBikeBack();
        SuccessNotiHandler succNoti = new SuccessNotiHandler(stage, Configs.SUCC_NOTI_PATH);
        
        succNoti.setNoti(response.get("MESSAGE"));
        succNoti.show();
	}


	

}
