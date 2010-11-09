package client;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ClientWriterThread extends Thread {
	PrintWriter pw;
	
	public ClientWriterThread(OutputStream os) {
		pw = new PrintWriter(os, true);
	}
	
	public void sendMessage(String messageToSend) {
		pw.println(messageToSend);
	}
	
	public void shutdown() {
		pw.close();
		try {
			this.join();
		} catch (InterruptedException e) {
			// It's OK.
		}
	}
}
