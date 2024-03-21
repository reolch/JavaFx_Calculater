package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
