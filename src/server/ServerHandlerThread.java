package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandlerThread extends Thread{
	private Socket mySocket;
	private PrintWriter output;
	private boolean shutdown;

	public ServerHandlerThread() {
		shutdown = false;
	}

	public void run() {	
		while(true) {
			if(shutdown) {
				break;
			}

			assert(mySocket.isConnected() == true);

			try {
				output = new PrintWriter(mySocket.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.println("Connected.");
			System.out.println("Told client: Connected.");
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// It's ok.
			}

			if(shutdown) {
				break;
			}
		}
	}

	public void setSocket(Socket mySocket) {
		this.mySocket = mySocket;
	}

	public void shutdown() { 
		if (isAlive()) {
			this.shutdown = true;
			try {
				mySocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			output.close();
			this.interrupt();
			try {
				this.join();
			} catch (InterruptedException e) {
				// It's ok.
			}
		}
	}
}