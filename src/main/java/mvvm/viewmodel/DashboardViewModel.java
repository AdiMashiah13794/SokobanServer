package mvvm.viewmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mvvm.model.Administrator;

public class DashboardViewModel implements Observer {
	private Administrator adminModel;
	private ObservableList<String> observableList;
	public ListProperty<String> clientsList;

	public DashboardViewModel(Administrator adminModel) {
		this.adminModel = adminModel;
		observableList = FXCollections.observableArrayList();
		clientsList = new SimpleListProperty<String>();
		clientsList.set(observableList);

		observableList.addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				  while (c.next()) {
				if (c.getRemovedSize() > 0) {

					for (String client: c.getRemoved()) {
						//adminModel.removeClient(client);
						adminModel.disconnectClient(client);
					}
				}

			}}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == adminModel) {
			List<String> params = (LinkedList<String>)arg;
			String op = params.get(0);
			String clientName = params.get(1);
			System.out.println("client name is:"+clientName);

			if (op.equals("Add")){
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						observableList.add(clientName);
					}
				});

			}
			else
				observableList.remove(clientName);
		}
	}

}
