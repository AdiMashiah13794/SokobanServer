package application;

import javafx.beans.property.SimpleIntegerProperty;

public class ClientsTable {

	private final SimpleIntegerProperty clientId;

	public ClientsTable(int id) {
		this.clientId=new SimpleIntegerProperty(id);
	}

	public Integer getId() {
		return clientId.get();
	}


	public void setClientId(Integer clientId) {
		this.clientId.set(clientId);
	}
@Override
public String toString() {
StringBuilder str=new StringBuilder();
str.append("id"+this.clientId.get());
return str.toString();
}



}
