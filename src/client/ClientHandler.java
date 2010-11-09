package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import network.ClientReaderThread;
import network.ClientWriterThread;

import shared.Game;


public class ClientHandler {
	Socket mySocket;
	ClientWriterThread cwt;
	ClientReaderThread crt;
	Game theGame;
	short port;
	byte [] ip;
	boolean running;

	public ClientHandler(byte[] ipAsByteArray, short port, Game theGame) {
		this.ip = ipAsByteArray;
		this.port = port;
		this.theGame = theGame;
		initializeSocket();
		startIO();
	}

	public ClientHandler(Socket socket) {
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

	private void startIO() {
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
	
	public void shutdown() {
		try {
			mySocket.close();
		} catch (IOException e) {
			// It's OK.
		}
		cwt.shutdown();
		crt.shutdown();
	}
	
	public void sendMessage(String line) {
		cwt.sendMessage(line);
	}
}
