package application;

import javafx.beans.property.SimpleStringProperty;

public class TaskTable {

	private final SimpleStringProperty taskId;

	public TaskTable(String task) {
		this.taskId=new SimpleStringProperty(task);
	}

	public SimpleStringProperty getTaskId() {
		return taskId;
	}



}
