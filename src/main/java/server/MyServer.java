package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import application.ClientConnected;
import application.Clients;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyServer  {
	private int port;
	private ClientHandler clientHandler;
	private volatile boolean stop;
	ServerSocket server=null;
	private static int threadSize=10;
	ArrayList<MyClientHandler> clientsHandlerList =new ArrayList<MyClientHandler>();
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    Clients clients;
    static int counter=0;
    static int  index;
  IntegerProperty clientsConnected;
	IntegerProperty indexToDelete;

	StringProperty clientsStringProperty;
	StringProperty deleteStringProperty;
	ClientConnected adi;
	MyClientHandler clientsHandlerArray[] =new MyClientHandler[10];
	int sizeArray=0;
	int sizeStringArray=0;
	String fromClientToServerAll[]=new String[30];





	public MyServer(int port,ClientHandler ch,Clients clients,ClientConnected adi) {
		this.clientsConnected=new SimpleIntegerProperty();
		this.indexToDelete=new SimpleIntegerProperty();
		this.port=port;
		this.clientHandler=ch;
		stop=false;
		this.adi=adi;
		this.clients=clients;
		//this.indexToDelete=index;
		//this.clientsConnected=clientsconn;
		this.clientsStringProperty=new SimpleStringProperty();
		this.deleteStringProperty=new SimpleStringProperty();
		//this.clientsStringProperty=clientsProp;



	}



	public static int getIndex() {
		return index;
	}


public void limitPool(int limit){
	System.out.println(((ThreadPoolExecutor)executorService).getCorePoolSize());
	((ThreadPoolExecutor)executorService).setCorePoolSize(limit);
	System.out.println(((ThreadPoolExecutor)executorService).getCorePoolSize());
}

	public IntegerProperty getIndexToDelete() {
		return indexToDelete;
	}



	public StringProperty getClientsStringProperty() {
		return clientsStringProperty;
	}



	public StringProperty getDeleteStringProperty() {
		return deleteStringProperty;
	}



	public ClientConnected getAdi() {
		return adi;
	}



	public MyClientHandler[] getClientsHandlerArray() {
		return clientsHandlerArray;
	}



	public int getSizeArray() {
		return sizeArray;
	}



	public static void setIndex(int index) {
		MyServer.index = index;
	}



	public void setClientsConnected(IntegerProperty clientsConnected) {
		this.clientsConnected = clientsConnected;
		System.out.println("change the value of conn in server to:" +this.clientsConnected.get());
	}



	public void setIndexToDelete(IntegerProperty indexToDelete) {
		this.indexToDelete = indexToDelete;
	}



	public void setClientsStringProperty(StringProperty clientsStringProperty) {
		this.clientsStringProperty = clientsStringProperty;
	}



	public void setDeleteStringProperty(StringProperty deleteStringProperty) {
		this.deleteStringProperty = deleteStringProperty;
	}



	public void setAdi(ClientConnected adi) {
		this.adi = adi;
	}



	public void setClientsHandlerArray(MyClientHandler[] clientsHandlerArray) {
		this.clientsHandlerArray = clientsHandlerArray;
	}



	public void setSizeArray(int sizeArray) {
		this.sizeArray = sizeArray;
	}



	public void bindClientstoInt(IntegerProperty clients){
		this.clientsStringProperty.bind(clients.asString());
		System.out.println(clientsStringProperty.get());
	}

	public void indexUpdate(){
		boolean flag=true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(flag){
				int i=indexToDelete.get();
				System.out.println("the index to delete:    "+i);
				System.out.println("the size of the list before delete:"+clientsHandlerList.size());
				if(clientsHandlerList.size()>0&&i>=0){
					if(clientsHandlerList.size()>=i+1)
						clientsHandlerList.remove(i);
				}
				System.out.println("the size of the list after delete:"+clientsHandlerList.size());


			}
			}
		}).start();
	}
	public void bindIndexToDelete(IntegerProperty index){
		this.deleteStringProperty.bind(index.asString());
		this.indexToDelete=index;
		int i=index.get();
		System.out.println("the index to delete:    "+i);
		System.out.println("the size of the list before delete:"+this.clientsHandlerList.size());
		if(this.clientsHandlerList.size()>0&&i>=0){
			if(this.clientsHandlerList.size()>=i+1)
				this.clientsHandlerList.remove(i);
		}
		System.out.println("the size of the list after delete:"+this.clientsHandlerList.size());

	}


	public void bindClients(IntegerProperty clients) {
		//Bindings.bindBidirectional(clients,clientsConnected);
		//System.out.println("the number of clients are after binding"+this.clientsConnected.toString());
	}




public static int getCounter() {
		return counter;
	}






	public static void setCounter(int counter) {
		MyServer.counter = counter;
	}






public Clients getClients() {
		return clients;
	}




	public void setClients(Clients clients) {
		this.clients = clients;
	}




public int getPort() {
		return port;
	}




	public ClientHandler getClientHandler() {
		return clientHandler;
	}




	public boolean isStop() {
		return stop;
	}




	public ServerSocket getServer() {
		return server;
	}




	public ArrayList<MyClientHandler> getClientsHandlerList() {
		return clientsHandlerList;
	}




	public ExecutorService getExecutorService() {
		return executorService;
	}








	public void setPort(int port) {
		this.port = port;
	}




	public void setClientHandler(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
	}




	public void setStop(boolean stop) {
		this.stop = stop;
	}



public static void changeClientsHandler(int index){



}
	public void setServer(ServerSocket server) {
		this.server = server;
	}




	public void setClientsHandlerList(ArrayList<MyClientHandler> clientsHandlerList) {
		this.clientsHandlerList = clientsHandlerList;
	}




	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}



public void disco(int index){
	this.clientsHandlerList.get(index).setFlag(true);
	this.clientsHandlerList.get(index).setStop(true);
	this.clientsHandlerList.remove(index);
	System.out.println(this.clientsHandlerList.size());
	System.out.println("finallyyyyyyedfjwefihweoifsopiifohpr");
	setCounter(counter--);



ClientConnected.setX(-1);

}




public void runServer() throws Exception {

	this.server=new ServerSocket(this.port);
	server.setSoTimeout(1000);
	System.out.println("server connected");


	Socket aClient=null;


	while(!stop){
		try {
		if(ClientConnected.getX()>=0){
			//System.out.println("x is in the server:"+ClientConnected.getX());
			//System.out.println("size of clients:"+ClientConnected.getClientsCon().size());

				//this.clientsHandlerList.get(ClientConnected.getX()).setFlag(true);
			//disco(ClientConnected.getX());
			this.clientsHandlerArray[ClientConnected.getX()].setStop(true);
			this.clientsHandlerArray[ClientConnected.getX()]=null;

			/******/
				//this.clientsHandlerList.get(ClientConnected.getX()).setStop(true);
				//this.clientsHandlerList.remove(ClientConnected.getX());
				//System.out.println(this.clientsHandlerList.size());
			System.out.println(this.clientsHandlerArray.length);
				System.out.println("finallyyyyyyedfjwefihweoifsopiifohpr");
				setCounter(counter-1);
				this.clientsConnected.set(counter);

				sizeArray--;


			ClientConnected.setX(-1);
		}
		//for (int i = 0; i < clientsHandlerArray.length; i++) {
			//if(clientsHandlerArray[i]!=null){
				//for (int j = 0; j < this.clientsHandlerArray[i].getFromClientToServer().length; j++) {
					//if(this.clientsHandlerArray[i].getFromClientToServer()[j]!=null){
				//	this.fromClientToServerAll[sizeStringArray++]=this.clientsHandlerArray[i].getFromClientToServer()[j];
					//this.clientsHandlerArray[i].getFromClientToServer()[j]=null;
					//}
				//}

			//}

		//}
		aClient=server.accept();// blocking call
		if(ClientConnected.getX()>=0){
			//System.out.println("x is in the server:"+ClientConnected.getX());
			//System.out.println("size of clients:"+ClientConnected.getClientsCon().size());

				//this.clientsHandlerList.get(ClientConnected.getX()).setFlag(true);
			//disco(ClientConnected.getX());
			this.clientsHandlerArray[ClientConnected.getX()].setStop(true);
			this.clientsHandlerArray[ClientConnected.getX()]=null;

			/******/
				//this.clientsHandlerList.get(ClientConnected.getX()).setStop(true);
				//this.clientsHandlerList.remove(ClientConnected.getX());
				//System.out.println(this.clientsHandlerList.size());
			System.out.println(this.clientsHandlerArray.length);
				System.out.println("finallyyyyyyedfjwefihweoifsopiifohpr");
				setCounter(counter-1);
				this.clientsConnected.set(counter);


sizeArray--;

			ClientConnected.setX(-1);
		}



		System.out.println("im am here");
		//System.out.println("bindinggggg poatrt"+this.clientsConnected.get());
		if(this.clients.isFlag()){
			System.out.println("in the if flag in the server");
			this.clientsHandlerList=this.clients.getClientsHandler();

			this.clients.setFlag(false);


		}
		System.out.println("connecting to server");
		MyClientHandler ch=new MyClientHandler();
		this.clientsHandlerArray[sizeArray++]=ch;
		this.clientsHandlerList.add(ch);
		this.clients.setClientsHandler(this.clientsHandlerList);

		setCounter(counter+1);
		String id="client "+counter;//System.out.println("the counter is" +counter);
		this.clientsConnected.set(counter);



		//this.clients.setCounter(counter);
		//System.out.println("changed the counter");
		 executorService.submit(new ServiceRequest(aClient,ch,id));

		}


		catch(Exception ex) {

		}


	}
		//server.close();
	aClient.close();

}

public void start(){
	new Thread(new Runnable() {

		@Override
		public void run() {
			try {
				runServer();
				//indexUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}).start();
}
public void stop() throws IOException{

	System.out.println("int the stop method");
	for (int i = 0; i < this.clientsHandlerList.size(); i++) {
		System.out.println("change the value to false");
		this.clientsHandlerList.get(i).setStop(true);
		this.clientsHandlerList.get(i).setFlag(true);

	}
    executorService.shutdown();
    try {
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		stop=true;
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


    this.server.close();

    System.out.println("The server is closed now");
}
public void disconnect(int id){
	this.clientsHandlerList.remove(id);


}
class ServiceRequest implements Runnable {

    private Socket socket;
    private ClientHandler handler;
    String id;

    public ServiceRequest(Socket connection, ClientHandler handler,String id) {
        this.socket = connection;
        this.handler=handler;
        this.id=id;
    }

    public void run() {
    	InputStream inFromClient;
		try {
			inFromClient = socket.getInputStream();
			OutputStream outToClient=socket.getOutputStream();
			this.handler.handleClient(this.socket,this.id,inFromClient, outToClient);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



    }
}
/*
@Override
public void update(Observable o, Object arg) {
	System.out.println("updatae:");
	@SuppressWarnings("unchecked")
	LinkedList<String> params=(LinkedList<String>) arg;
	String value= params.removeFirst();
	int x = Character.getNumericValue(value.charAt(2));
	System.out.println("id in the update"+x);
	disconnect(x);

}
*/
public IntegerProperty getClientsConnected() {
	return this.clientsConnected;
}
}


