package server;

import java.io.IOException;
import java.net.Socket;

import network.ServerReaderThread;
import network.WriterThread;

public class SocketHandler extends Thread{
	private Socket mySocket;
	private WriterThread wt;
	private ServerReaderThread srt;
	private boolean shutdown;

	public SocketHandler() {
		shutdown = false;
	}

	public void run() {	
		while(true) {
			if(shutdown) {
				break;
			}

			assert(mySocket.isConnected() == true);

			try {
				wt = new WriterThread(mySocket.getOutputStream());
				wt.start();
				srt = new ServerReaderThread(mySocket.getInputStream(), wt.getPrintWriter());
				srt.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				sleep(500);
			} catch (InterruptedException e) {
				// It's OK.
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
			wt.shutdown();
			this.interrupt();
			try {
				this.join();
			} catch (InterruptedException e) {
				// It's ok.
			}
		}
	}
}