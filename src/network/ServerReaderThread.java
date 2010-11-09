package network;

import java.io.InputStream;
import java.io.PrintWriter;

public class ServerReaderThread extends ReaderThread {
	public ServerReaderThread(InputStream is, PrintWriter pw) {
		super(is);
		this.np = new ServerNetworkParser(pw);
	}
}
