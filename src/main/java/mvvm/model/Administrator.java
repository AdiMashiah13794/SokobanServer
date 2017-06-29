package mvvm.model;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class Administrator extends Observable {
	private Map<String, Socket> connectedClients =new HashMap<String, Socket>();

	private static final Administrator instance = new Administrator();

	//singelton
	private Administrator() {}


	public static Administrator getInstance() {
		return instance;
	}

	public void addClient(String clientId, Socket socket) {
		connectedClients.put(clientId, socket);
		setChanged();
		List<String> params = new LinkedList<String>();
		params.add("Add");
		params.add(clientId);
		notifyObservers(params);
	}

	public void removeClient(String clientId) {
		connectedClients.remove(clientId);
	}

	public void disconnectClient(String clientId) {
		System.out.println("i am in the disco client");
		Socket socket = connectedClients.get(clientId);
		this.connectedClients.remove(clientId);
		try {
			socket.close();
			connectedClients.remove(clientId);
			setChanged();
			List<String> params = new LinkedList<String>();
			params.add("Remove");
			params.add(clientId);
			notifyObservers(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
