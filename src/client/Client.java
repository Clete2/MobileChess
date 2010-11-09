package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	Socket mySocket;
	short port;
	byte [] ip;
	boolean running;

	public Client(byte[] ipAsByteArray, short port) {
		this.ip = ipAsByteArray;
		this.port = port;
		initializeSocket();
		startIO();
	}

	public Client(Socket socket) {
		this.mySocket = socket;
		startIO();
	}

	private void initializeSocket() {
		try {
			this.mySocket = new Socket(InetAddress.getByAddress(ip), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startIO() {
		try {
			ClientWriterThread cwt = new ClientWriterThread(mySocket.getOutputStream());
			cwt.start();
			ClientReaderThread crt = new ClientReaderThread(mySocket.getInputStream());
			crt.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
