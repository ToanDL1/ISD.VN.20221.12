package views.screen.successNoti;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class SuccessNotiHandler extends BaseScreenHandler implements Initializable {
	@FXML
	private Label succNoti;
	@FXML
	private Button homeBtn;

	public SuccessNotiHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	public void setNoti(String noti) {
		succNoti.setText(noti);;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		homeBtn.setOnMouseClicked(e->{
			try {
				HomeScreenHandler homeScreen= new HomeScreenHandler(stage, Configs.HOME_SCREEN_PATH);
				homeScreen.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
	}

}
