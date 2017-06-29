package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ClientHandler {

	//public void handleClient(InputStream inFromClient,OutputStream outToClient) throws IOException, InterruptedException;
	public void handleClient(Socket socket,String id,InputStream inFromClient,OutputStream outToClient) throws IOException, InterruptedException;

}
