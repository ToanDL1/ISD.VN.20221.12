import java.io.IOException;
import java.sql.SQLException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Configs;
import utils.DbCheck;
import views.screen.home.HomeScreenHandler;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		// Application starts by loading in and show splash screen
		Pane splash = (Pane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
		Scene scene = new Scene(splash);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Fade in the splash screen
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), splash);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);
		
		// Fade out the splash screen and load home screen
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), splash);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);
		fadeOut.setOnFinished(e -> {
			try {
				HomeScreenHandler homeScreen = new HomeScreenHandler(primaryStage, Configs.HOME_SCREEN_PATH);
				homeScreen.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		});
		
		// Setup animation sequence
		fadeIn.play();
		fadeIn.setOnFinished(e -> {
			fadeOut.play();
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

