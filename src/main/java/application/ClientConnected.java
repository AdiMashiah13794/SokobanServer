package application;

import java.util.ArrayList;
import java.util.List;

public class ClientConnected {
	static int x;
	static List<Integer> clientsCon;

	public ClientConnected() {
x=-1;
clientsCon=new ArrayList<Integer>();
}
	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		ClientConnected.x = x;
	}
	public static List<Integer> getClientsCon() {
		return clientsCon;
	}
	public static void setClientsCon(List<Integer> clientsCon) {
		ClientConnected.clientsCon = clientsCon;
	}



}
