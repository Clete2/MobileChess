package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.ServerSocketHandler;

public abstract class ReaderThread extends Thread {
	BufferedReader br;
	NetworkParser np; // Can be either server or client
	Socket mySocket;
	boolean running;
	
	public ReaderThread(Socket mySocket) {
		this.mySocket = mySocket;
		running = true;
		try {
			br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		String in = "";
		while(running) {
			try {
				if((in = br.readLine()) != null) {
					// So the server knows who to send to
					ServerSocketHandler.setLastMessenger(mySocket);
					np.parseInput(in);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
		try {
			br.close();
		} catch (IOException e) {
			// It's OK.
		}
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
