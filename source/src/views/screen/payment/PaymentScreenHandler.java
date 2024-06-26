package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import controller.PaymentController;
import entity.Invoice.Invoice;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.successNoti.SuccessNotiHandler;

public class PaymentScreenHandler extends BaseScreenHandler {

	@FXML
	private Button btnConfirmPayment;

	@FXML 
	private Label costLabel;

	private Invoice invoice;

	public PaymentScreenHandler(Stage stage, String screenPath, int amount, String contents) throws IOException {
		super(stage, screenPath);
	}

	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;
		
		try {
			costLabel.setText(Integer.toString(invoice.getDeposit()) + " VND");
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
		Map<String, String> response = ctrl.payOrder(invoice.getDeposit(), contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText());
		ctrl.updateRentBike(invoice.getBikeInvoice());
		System.out.println("result:" + response.get("RESULT"));
		System.out.println("message: " + response.get("MESSAGE"));
        SuccessNotiHandler succNoti = new SuccessNotiHandler(stage, Configs.SUCC_NOTI_PATH);
        
        succNoti.setNoti(response.get("MESSAGE"));
        succNoti.show();
	}

	
	
}
