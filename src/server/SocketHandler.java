package server;

import java.io.IOException;
import java.net.Socket;

import network.ServerReaderThread;
import network.WriterThread;

public class SocketHandler {
	private Socket mySocket;
	private WriterThread wt;
	private ServerReaderThread srt;

	public void initialize() {	
		assert(mySocket.isConnected() == true);

		try {
			wt = new WriterThread(mySocket.getOutputStream());
			wt.start();
			srt = new ServerReaderThread(mySocket, wt.getPrintWriter());
			srt.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setSocket(Socket mySocket) {
		this.mySocket = mySocket;
	}
}