package mvvm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import mvvm.viewmodel.DashboardViewModel;

public class DashboardController  {



	@FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private ListView<String> myListView;

    private DashboardViewModel vm;



    public DashboardController() {
		// TODO Auto-generated constructor stub
	}

    public void setViewModel(DashboardViewModel vm) {
    	this.vm = vm;
    	myListView.itemsProperty().bind(vm.clientsList);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    	myListView.getItems().remove(myListView.getSelectionModel().getSelectedItem());
    }

}
