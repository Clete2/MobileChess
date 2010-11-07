package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket clientSocket;
	BufferedReader input;
	boolean quit;
	short port;

	public Client() {
		port = 1337;		
		quit = false;
		startConnection(port);
	}

	private void startConnection(short port) {
		try {
			clientSocket = new Socket(InetAddress.getLocalHost(), port);
			input = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));

			String in = "";
			while(!quit){
				while((in = input.readLine()) != null) {
					System.out.print(in);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopClient() {
		try {
			clientSocket.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
