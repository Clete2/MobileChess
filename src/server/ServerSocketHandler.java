package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketHandler extends Thread {
	private ServerSocket servingSocket;
	private SocketHandler[] playerSocket;
	private static short connections;
	private short port;
	private boolean shutdown;

	public ServerSocketHandler(short portToListen) {
		this.port = portToListen;
		ServerSocketHandler.connections = 0;
		this.shutdown = false;
	}

	public void run() {
		initializeSockets();
		listen();
	}

	private void initializeSockets() {
		playerSocket = new SocketHandler[2];
		playerSocket[0] = new SocketHandler();
		playerSocket[1] = new SocketHandler();
		try {
			servingSocket = new ServerSocket(port);
			servingSocket.setSoTimeout(500);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void listen() {
		while(connections < 2 && !shutdown) {
			try {
				Socket mySocket = servingSocket.accept();
				playerSocket[connections].setSocket(mySocket); 
				playerSocket[connections].start();
				connections++;
			} catch (SocketTimeoutException e) {
				// It's OK.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			servingSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getNumberOfConnections() {
		return ServerSocketHandler.connections;
	}
	
	public void shutdown() { 
		if (isAlive()) {
			if(playerSocket[0].isAlive()) {
				playerSocket[0].shutdown();
			}
			if(playerSocket[1].isAlive()) {
				playerSocket[2].shutdown();
			}
			this.shutdown = true;
			this.interrupt();
			try {
				this.join();
			} catch (InterruptedException e) {
				// It's OK.
			}
		}
	}
}