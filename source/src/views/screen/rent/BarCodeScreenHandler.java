package views.screen.rent;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import entity.bike.bike;
import entity.db.EcoDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.PaymentSceenHandler;
import views.screen.popup.PopupScreen;

public class BarCodeScreenHandler extends BaseScreenHandler implements Initializable{
	private int barcode;
	@FXML
    private TextField BarCodeInput;
	@FXML
	private Button submitBarcode;
	@FXML
	private Button homeView;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		homeView.setOnMouseClicked(e->{
			try {
				HomeScreenHandler homeScreen = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
				homeScreen.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		submitBarcode.setOnMouseClicked(e ->{
			try {
				System.out.println("hahah day roi");
				
				showPayment();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
	}
	
	public BarCodeScreenHandler(Stage stage, String screenPath, int barcode) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		this.barcode = barcode;
			
		
	}
	public BarCodeScreenHandler(Stage stage, String barcodeScreenPath) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, barcodeScreenPath);
	}
	public void setBarCode(int barcode) {
		BarCodeInput.setText(Integer.toString(barcode));
	}
	
	public int checkBarCode() throws SQLException {
		int number = Integer.parseInt(BarCodeInput.getText());
		String sql = "Select * from bike where id = "+ number ;
		Statement stm = EcoDB.getConnetttion().createStatement();
		ResultSet res = stm.executeQuery(sql);
	   if(res.next()== false) {
		  return -1;
	   }
		return number;
	}
	private void showPayment() throws SQLException, IOException {
		
		if(checkBarCode()== -1) {
			String error = "Mã barcode ko tồn tại, vui lòng nhập lại";
			   PopupScreen.error(error);
		}else {
			System.out.println("nhay sang payment");
			int id =checkBarCode();
			bike bikeRent = bike.getBikeById(id);
			PaymentSceenHandler payment = new PaymentSceenHandler(stage, Configs.PAYMENT_FORM_PATH, bikeRent);
			payment.setCost();
			payment.show();
		}
	}

	}
	
	


