package network;

import java.io.PrintWriter;
import java.net.Socket;

public class ServerReaderThread extends ReaderThread {
	public ServerReaderThread(Socket mySocket, PrintWriter pw) {
		super(mySocket);
		this.np = new ServerNetworkParser(pw);
	}
}
