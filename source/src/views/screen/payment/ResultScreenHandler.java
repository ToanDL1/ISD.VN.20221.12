package views.screen.payment;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class ResultScreenHandler extends BaseScreenHandler {
	@FXML
	private Label resultLabel;
	@FXML
	private Label messageLabel;
	
	public ResultScreenHandler(Stage stage, String screenPath,String result, String message) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
	}

	
}
