package network;

import java.io.OutputStream;
import java.io.PrintWriter;

public class WriterThread extends Thread {
	PrintWriter pw;
	
	public WriterThread(OutputStream os) {
		pw = new PrintWriter(os, true);
	}
	
	public void sendMessage(String messageToSend) {
		pw.println(messageToSend);
	}
	
	public PrintWriter getPrintWriter() {
		return pw;
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
