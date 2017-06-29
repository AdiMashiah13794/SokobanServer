package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.MyServer;

public class Task {
	String fromClientCommands[]=new String[30];
	int sizeArray=0;
	@FXML
	TableView<TaskTable> tasksTable;



	@FXML
	TableColumn<TaskTable, String> task;

	@FXML
	Button stopTask;


	public Task() {

	}
/*

	@FXML
	public void initialize(){
		System.out.println("initializeush");
		task.setCellValueFactory(new PropertyValueFactory<TaskTable, String>("task"));
		this.stopTask.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//System.out.println("get adi");
				//System.out.println(getAdi());
				ObservableList<ClientsTable> deletedCommand;

				deletedCommand=clientsTable.getSelectionModel().getSelectedItems();
				int c = Character.getNumericValue(deletedCommand.get(0).toString().charAt(2));
				System.out.println("c is   "+c);



				ClientConnected.setX(c-1);
				deleteIndex.set(c-1);

				ObservableList<ClientsTable> allClients;
				LinkedList<String> parms=new LinkedList<String>();
				allClients=clientsTable.getItems();

				deletedClient=clientsTable.getSelectionModel().getSelectedItems();

				for (int i = 0; i < deletedCommand.size(); i++) {
					System.out.println("the clients that want to deklte:"+deletedCommand.get(i));
					parms.add(deletedCommand.get(i).toString());




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

*/

}
