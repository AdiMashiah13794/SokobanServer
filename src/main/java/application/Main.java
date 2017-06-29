package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import server.ClientHandler;
import server.MyClientHandler;
import server.MyServer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		/*try {
			ClientHandler ch=new MyClientHandler();
			MyServer server=new MyServer(5555, ch);
			try {
				server.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			BorderPane root;
			try {
				root = (BorderPane) FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
				Scene scene = new Scene(root,741,417);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//} catch(Exception e) {
			//e.printStackTrace();
		//}
	}

	public static void main(String[] args) {



		launch(args);
	}

}
