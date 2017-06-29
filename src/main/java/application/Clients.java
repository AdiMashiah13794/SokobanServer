package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.MyClientHandler;
import server.MyServer;

//public class Clients implements EventHandler<ActionEvent>    {
public class Clients implements Initializable   {

	//@FXML
	//private Text connectedClients;
	//private StringProperty clientsNumber;
	int counter=0;
	//int clientId;
	private IntegerProperty index=new SimpleIntegerProperty();
	//private ArrayList<ClientsTable> clientsList=new ArrayList<ClientsTable>(new ClientsTable(1));
	private int number=1;
	ArrayList<MyClientHandler> clientsHandler=new ArrayList<MyClientHandler>();
	boolean flag=false;
	IntegerProperty deleteIndex;
	IntegerProperty numberOfClients;
	@FXML
	Button button=new Button("disconnect Button");
	ClientConnected adi=new ClientConnected();



	public void bindClients(IntegerProperty clients){
		//this.clientsNumber.bind(clients.asString());
		this.numberOfClients.bind(clients);

		System.out.println("in bind func");


	}

	public IntegerProperty getNumberOfClients() {
		return numberOfClients;
	}



	public void setNumberOfClients(IntegerProperty numberOfClients) {

		this.numberOfClients = numberOfClients;
	}



	public IntegerProperty getDeleteIndex() {
		return deleteIndex;
	}



	public void setDeleteIndex(IntegerProperty deleteIndex) {
		this.deleteIndex = deleteIndex;
	}



	public boolean isFlag() {
		return flag;
	}



	public void setFlag(boolean flag) {

		this.flag = flag;
	}



	public IntegerProperty getIndex() {
		return index;
	}



	public int getNumber() {
		return number;
	}



	public ArrayList<MyClientHandler> getClientsHandler() {
		return clientsHandler;
	}



	public TableView<ClientsTable> getClientsTable() {
		return clientsTable;
	}



	public ComboBox<ClientsTable> getClientscombo() {
		return clientscombo;
	}



	public TableColumn<ClientsTable, Integer> getId() {
		return id;
	}



	public Button getDelete() {
		return delete;
	}



	public ObservableList<ClientsTable> getData() {
		return data;
	}



	public ListProperty<ClientsTable> getListProp() {
		return listProp;
	}



	public void setIndex(IntegerProperty index) {
		this.index = index;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public void setClientsHandler(ArrayList<MyClientHandler> clientsHandler) {
		//this.clientsHandler = clientsHandler;
		System.out.println("set client handler");
		if(this.clientsHandler!=null){
			for (int i = 0; i <this.clientsHandler.size(); i++) {
				this.clientsHandler.remove(i);

			}
		}

		for (int i = 0; i < clientsHandler.size(); i++) {
			System.out.println(clientsHandler.get(i).toString());
			MyClientHandler newClientHandler=new MyClientHandler();

			this.clientsHandler.add(newClientHandler);

		}
		this.clientsHandler=new ArrayList<MyClientHandler>(clientsHandler);
		System.out.println("now i want to sleep please work");
		for (int i = 0; i < this.clientsHandler.size(); i++) {
			System.out.println(this.clientsHandler.get(i));

		}
	}

public Clients() {
	this.numberOfClients=new SimpleIntegerProperty();
	this.deleteIndex=new SimpleIntegerProperty(-1);
}

	public void setClientsTable(TableView<ClientsTable> clientsTable) {
		this.clientsTable = clientsTable;
	}



	public void setClientscombo(ComboBox<ClientsTable> clientscombo) {
		this.clientscombo = clientscombo;
	}



	public void setId(TableColumn<ClientsTable, Integer> id) {
		this.id = id;
	}



	public void setDelete(Button delete) {
		this.delete = delete;
	}



	public void setData(ObservableList<ClientsTable> data) {
		this.data = data;
	}



	public void setListProp(ListProperty<ClientsTable> listProp) {
		this.listProp = listProp;
	}



	@FXML
	TableView<ClientsTable> clientsTable;

	@FXML
	ComboBox<ClientsTable>clientscombo;

	@FXML
	TableColumn<ClientsTable, Integer> id;

	@FXML
	Button delete;


	@FXML
	Button newButton;

	@FXML
	Label newLable;

	@FXML
	ListView newListView;

	MainWindowController admin;

	protected ListProperty<String> newListProperty=new SimpleListProperty<>();

	private ObservableList<ClientsTable> data;

	private ListProperty<ClientsTable> listProp;


	@FXML
	public void handleButtonAction(ActionEvent event){

	}

	public Clients(IntegerProperty indexTodelete, IntegerProperty connectedClients) {
		this.deleteIndex=indexTodelete;
		this.numberOfClients=connectedClients;


	}



	public Button getButton() {
		return button;
	}



	public void setButton(Button button) {
		this.button = button;
	}



	public void setAdi(ClientConnected adi) {
		this.adi = adi;
	}

/*
	public Text getConnectedClients() {
		return connectedClients;
	}



	public StringProperty getClientsNumber() {
		return clientsNumber;
	}


*/
	public int getCounter() {
		return counter;
	}

/*

	public void setConnectedClients(Text connectedClients) {
		this.connectedClients = connectedClients;
	}



	public void setClientsNumber(StringProperty clientsNumber) {
		this.clientsNumber = clientsNumber;


	}

*/

	public void setCounter(int counter) {

		//System.out.println("i in set Counter");
	//	changeList(counter);
		/*
		System.out.println("i in set Counter");
		onAddItem(counter);
		/*
		System.out.println("i am here");
		this.counter = counter;
		System.out.println("the clients counter now is changed");
		System.out.println("the counter in the clients is:"+counter);
		 //ObservableList<ClientsTable> newData=FXCollections.observableArrayList(new ClientsTable(1));
		//for (int i=0;i<counter;i++){
			//newData.add(new ClientsTable(++i));
			//System.out.println("the client is:" +newData.get(i));

		//}
		//System.out.println(newData.get(0).toString());
		for (int i = 0; i < counter; i++) {
			this.clientsList.add(new ClientsTable(1));
			System.out.println("in the client list"+clientsList.get(i).toString());

		}
		//this.data=(ObservableList<ClientsTable>) clientsList;
		//this.clientsTable.setItems(data);
		//this.clientsTable.setItems(FXCollections.observableArrayList(clientsList));
		//this.clientsTable.refresh();
		System.out.println("end of the func");
		//this.clientsTable.getItems().clear();
		//this.clientsTable.getItems().addAll(clientsList);


		//this.clientsTable.setItems(newData);
		/*
		for (int i = 0; i < counter; i++) {
	        this.clientsTable.getItems().add((ClientsTable) FXCollections.observableArrayList(new ClientsTable(++i)));


		}
		/*
		for (int i = 0; i < counter; i++) {
			System.out.println("in the loop");
			this.data.set(i, new ClientsTable(++i));
			System.out.println("the data after adding:"+data.get(i).toString());


		}
		this.clientsTable.setItems(data);
		this.clientsTable.getItems();
		//changeData(counter);
		//this.clientsNumber.set("steps: "+counter);
		//this.data=null;
		//this.data=FXCollections.observableArrayList();
/*
		for (int i = 0; i < counter; i++) {
			System.out.println("the data is:");
			this.data.add(new ClientsTable(++i));
			System.out.println(this.data.get(i));

		}
		//this.clientsTable.setItems(this.data);

		this.clientsTable.setItems(data);
*/
	}


/*
	public Clients(StringProperty clientsNumber) {
		this.clientsNumber=clientsNumber;
	}


*/
/*

	public void bindClients(StringProperty clientsNumber) {
		this.connectedClients.textProperty().bind(clientsNumber);
	}
*/
	public void deleteClient(int indexD){
		System.out.println("in the delete func");
		for (int i = 0; i < clientsHandler.size(); i++) {
			this.clientsHandler.get(i).toString();

		}

		this.clientsHandler.remove(indexD);
		System.out.println("after deleting");
		for (int i = 0; i < clientsHandler.size(); i++) {
			this.clientsHandler.get(i).toString();

		}
		this.flag=true;


	}

@FXML
	public void DisconnectClient(){
		ObservableList<ClientsTable> allClients;
		ObservableList<ClientsTable> deletedClient;
		LinkedList<String> parms=new LinkedList<String>();

		//Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();

		allClients=this.clientsTable.getItems();

		deletedClient=this.clientsTable.getSelectionModel().getSelectedItems();

		for (int i = 0; i < deletedClient.size(); i++) {
			System.out.println("the clients that want to deklte:"+deletedClient.get(i));
			parms.add(deletedClient.get(i).toString());
/*
			Thread.currentThread().setName(deletedClient.get(i).toString());
			System.out.println("the current thread: "+Thread.currentThread().getName());
			if(Thread.currentThread().getName().equals(deletedClient.get(i).toString())){

				Thread.currentThread().interrupt();
			}
			*/

		}

//System.out.println("parms in cliens is"+parms.get(0));
	//	LinkedList<String> params=(LinkedList<String>) arg;
		String value= parms.removeFirst();
		int x = Character.getNumericValue(value.charAt(2));
		int deleteIndex=x-1;
		final int y=deleteIndex;
		System.out.println("id in the update"+deleteIndex);
		this.deleteIndex.set(y);
		//deleteClient(deleteIndex);
		ClientConnected.setX(deleteIndex);
		ClientConnected.getClientsCon().add(deleteIndex);

		deletedClient.forEach(allClients::remove);








		/*
		int i=index.get();
		System.out.println("the index that i want to delete"+""+index.get());
		if(i>-1){
			System.out.println("the data that i want to deltet is:"+data.get(i));
			data.remove(i);
			clientsTable.getSelectionModel().clearSelection();
		}
		*/
	}
	/*


	public void changeData(){
		index.set(-1);

		id.setCellValueFactory(new PropertyValueFactory<ClientsTable, Integer>("id"));
		this.clientsTable.setItems(this.data);

		this.clientsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				index.set(data.indexOf(newValue));
				System.out.println("ok index is"+""+data.indexOf(newValue));

			}
		});

	}


*/
/*
	public void changeData(int counter){
		data.clear();
		for (int i = 0; i < counter; i++) {
			this.data.add(new ClientsTable(++i));

		}
		this.clientsTable.setItems(data);



	}

	public void newChange(Object newValue){
		index.set(data.indexOf(newValue));
		System.out.println("ok index is"+""+data.indexOf(newValue));
		changeData(0);

	}
*/

	public void onAddItem(int counter){
System.out.println("im in on ADD ITEMMMM");
		for (int i = 0; i <counter; i++) {
			ClientsTable entry=new ClientsTable(i+1);
			data.add(entry);

		}
this.clientsTable.setItems(data);


	}

	public void changeList(int counter){
		System.out.println("changeList");
		List<ClientsTable> list=new ArrayList<ClientsTable>();
		id.setCellValueFactory(new PropertyValueFactory<ClientsTable, Integer>("id"));

		for (int i = 0; i < counter; i++) {
			list.add(new ClientsTable(i+1));
			System.out.println("the nre client:"+list.get(i).toString());

		}

		this.listProp=new SimpleListProperty<ClientsTable>();
		this.data= FXCollections.observableArrayList(list);
		this.listProp.set(data);

		//this.clientscombo.itemsProperty().bindBidirectional(listProp);
		this.clientscombo.getItems().addAll(listProp);

		//this.clientsTable.setItems(data);
		//this.clientsTable.editingCellProperty();
		//this.clientsTable.refresh();


	}

public int getAdi(){
	return ClientConnected.getX();

}


//**************************************************************
	@FXML
	public void initialize(){
		System.out.println("initializeush");
		id.setCellValueFactory(new PropertyValueFactory<ClientsTable, Integer>("id"));
		this.button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("get adi");
				System.out.println(getAdi());
				ObservableList<ClientsTable> deletedClient;

				deletedClient=clientsTable.getSelectionModel().getSelectedItems();
				int c = Character.getNumericValue(deletedClient.get(0).toString().charAt(2));
				System.out.println("c is   "+c);



				ClientConnected.setX(c-1);
				deleteIndex.set(c-1);

				ObservableList<ClientsTable> allClients;
				LinkedList<String> parms=new LinkedList<String>();
				allClients=clientsTable.getItems();

				deletedClient=clientsTable.getSelectionModel().getSelectedItems();

				for (int i = 0; i < deletedClient.size(); i++) {
					System.out.println("the clients that want to deklte:"+deletedClient.get(i));
					parms.add(deletedClient.get(i).toString());




				}
				String value= parms.removeFirst();
				int x = Character.getNumericValue(value.charAt(2));
				int deleteIndexed=x-1;
				final int y=deleteIndexed;
				System.out.println("id in the update"+deleteIndex);
				deleteIndex.set(y);
				//deleteClient(deleteIndex);

				deletedClient.forEach(allClients::remove);

			}
		});

		List<ClientsTable> list=new ArrayList<ClientsTable>();
		//list.add(new ClientsTable(1));
		System.out.println("number of clients:"+ this.numberOfClients.get());

		for (int i = 0; i < MyServer.getCounter(); i++) {
			list.add(new ClientsTable(i+1));
		}

		this.listProp=new SimpleListProperty<ClientsTable>();
		this.data= FXCollections.observableArrayList(list);
		this.listProp.set(data);
		//this.clientscombo.itemsProperty().unbindBidirectional(listProp);
		//this.clientscombo.getItems().addAll(listProp);
		//this.numberOfClients.set(6);

		//this.numberOfClients.set(7);


		this.clientsTable.itemsProperty().bindBidirectional(listProp);
		this.clientsTable.setItems(data);
		this.clientsTable.editingCellProperty();
		this.clientsTable.refresh();
	}


//********************************************
	//@Override
	//public void handle(ActionEvent event) {
		/*
		if(event.getSource()==this.)
		this.clients.DisconnectClient();
		*/

	//}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/*
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		index.set(-1);

		id.setCellValueFactory(new PropertyValueFactory<ClientsTable, Integer>("id"));
		this.clientsTable.setItems(this.data);
		this.clientsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				index.set(data.indexOf(newValue));
				System.out.println("ok index is"+""+data.indexOf(newValue));

			}
		});
/*
		this.data.addListener(new ListChangeListener<ClientsTable>(){

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends ClientsTable> c) {
				System.out.println("Changed on " + c);
	            if(c.next()){
	                System.out.println(c.getFrom());
	            }

			}

		});
		this.data.addAll(this.clientsList);
		this.clientsTable.setItems(data);

/*
		this.data.addListener(new ListChangeListener<Object>(){

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? > listChange) {
				System.out.println("change!");

			}

		});
		/*
		this.data.addListener(new ListChangeListener<Object>() {
		      @Override
		      public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
		        System.out.println("change!");
		      }
		    });

		    */

/*
		this.clientsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				index.set(data.indexOf(newValue));
				System.out.println("ok index is"+""+data.indexOf(newValue));

			}
		});
		*/
		}

