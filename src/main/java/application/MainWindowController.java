package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import mvvm.model.Administrator;
import mvvm.view.DashboardController;
import mvvm.viewmodel.DashboardViewModel;
import server.ClientHandler;
import server.MyClientHandler;
import server.MyServer;

public class MainWindowController  {

	//private StringProperty clientsNumber;
	IntegerProperty clientsConnectedClients;
	IntegerProperty indexToDeleteClients;
	IntegerProperty clientsConnectedServer;
	IntegerProperty indexToDeleteServer;
	private Clients clients;
	DashboardViewModel vm;
	DashboardController dash=new DashboardController();

	private ClientHandler ch;
	private MyServer server;
	int counter=0;


	public MainWindowController() {
		this.ch=new MyClientHandler();
		this.clientsConnectedClients=new SimpleIntegerProperty(0);

		this.indexToDeleteClients=new SimpleIntegerProperty(0);
		this.clientsConnectedServer=new SimpleIntegerProperty(0);

		this.indexToDeleteServer=new SimpleIntegerProperty(0);
		ClientConnected con=new ClientConnected();
		this.clients=new Clients();
		this.clients.setDeleteIndex(indexToDeleteClients);
		this.clients.setNumberOfClients(clientsConnectedClients);
		this.clients.setAdi(con);
		this.server=new MyServer(5555, ch,clients,con);
		Administrator model=Administrator.getInstance();
		this.vm=new DashboardViewModel(model);
		model.addObserver(vm);



		//this.server.bindClients(this.clients.getNumberOfClients());
		//this.clients.addObserver(server);
		//this.clients=clients;
		//this.clientsNumber=new SimpleStringProperty();
		//this.clients.bindClients(clientsNumber);
		//counter=this.


		//this.countSteps.set("steps: "+counter);



	}


public void updateServerPool(){

	String name;
	Dialog<Pair<String, String>> dialog = new Dialog<Pair<String, String>>();
	dialog.setTitle("Updating.. ");

	try {
		dialog.setGraphic(new ImageView(new Image(new FileInputStream("./resources/pool.gif"))));
		ButtonType okButton = new ButtonType("update", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		TextField firstName = new TextField();
		firstName.setPromptText("limit to: ");
		//TextField lastName = new TextField();
		//lastName.setPromptText("last name:");
		grid.add(new Label("limit"), 0, 0);
		grid.add(firstName, 1, 0);
		//grid.add(new Label("last name"), 0, 1);
		//grid.add(lastName, 1, 1);

		dialog.getDialogPane().setContent(grid);



		dialog.setResultConverter(dialogButton->{
			if(dialogButton==okButton){
				int ret=Integer.parseInt(firstName.getText());
				System.out.println("the int from textfield: "+ret);
				this.server.limitPool(ret);

/*
				userManager.updateFirstName(this.user.getUserId(), firstName.getText());
				userManager.updateLastName(this.user.getUserId(), lastName.getText());
				return new Pair<>(firstName.getText(),lastName.getText());


*/
			}
			return null;
		});
	}

	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Optional<Pair<String, String>> result = dialog.showAndWait();

	// if(result.ifPresent())
	// System.out.println("your name: "+result.get());
	// levelDb.setName(result.get());





}





	public Clients getClients() {
		return clients;
	}


	public ClientHandler getCh() {
		return ch;
	}


	public MyServer getServer() {
		return server;
	}


	public int getCounter() {
		return counter;
	}


	public void setClients(Clients clients) {
		this.clients = clients;
	}


	public void setCh(ClientHandler ch) {
		this.ch = ch;
	}


	public void setServer(MyServer server) {
		this.server = server;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public void Button(){
		System.out.println("Start");
	}
	public void StartServer(){
		try {
			this.server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void ristrict(){
	updateServerPool();
}

public void  DashboardClients(){

	Platform.setImplicitExit(false);
	try {
		Stage window= new Stage();
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("AdminDashboard.fxml"));
		Parent root=(Parent) loader.load();

		Scene scene = new Scene(root,525,404);
		this.dash=loader.getController();
		this.dash.setViewModel(this.vm);
		window.setScene(scene);
		window.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



}





	public void ClientsMenu() throws IOException{
		this.clients.bindClients(this.server.getClientsConnected());

		Stage window= new Stage();
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientsMenu.fxml"));

		Pane layout;
		layout = loader.load();


		Scene scene = new Scene(layout);

		 this.clients = loader.getController();
		// menu.setSteps(steps);
		// menu.setTime(time);
		 //menu.setLevelName(levelDb.getLevelName());
		// menu.setLevel(levelDb);
		 //menu.setSteps(steps);


		window.setScene(scene);
		window.show();
		//this.server.bindClientstoInt(this.clients.numberOfClients);
		//this.server.bindIndexToDelete(this.clients.deleteIndex);
	}
	public void StopServer(){
		try {
			this.server.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




/*
	@Override
	public void update(Observable observable, Object arg) {
		@SuppressWarnings("unchecked")
		LinkedList<String> params=(LinkedList<String>) arg;
		String value= params.removeFirst();
		int x = Character.getNumericValue(value.charAt(2));
		System.out.println("id in the update"+x);
		this.server.disconnect(x);


	}

*/
}
