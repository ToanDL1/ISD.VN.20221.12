package views.fxml.rent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EcoDB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class BarCodeScreenHandler extends BaseScreenHandler{
	private int barcode;
	@FXML
    private TextField BarCodeInput;

	public BarCodeScreenHandler(Stage stage, String screenPath, int barcode) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
		this.barcode = barcode;
	}
	public void setBarCode(int barcode) {
		BarCodeInput.setText(Integer.toString(barcode));
	}
	
	public boolean checkBarCode() throws SQLException {
		int number = Integer.parseInt(BarCodeInput.getText());
		String sql = "Select * from bike where id = "+ number ;
		Statement stm = EcoDB.getConnetttion().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if(res == null) {
			return false;
		}
		return true;
	}

}
