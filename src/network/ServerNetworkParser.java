package network;

import java.io.PrintWriter;

import server.ServerSocketHandler;

public class ServerNetworkParser extends NetworkParser {
	PrintWriter pw;

	public ServerNetworkParser(PrintWriter pw) {
		this.pw = pw;
	}

	public void parseInput(String in) {
		if(in.equalsIgnoreCase("C")) { // Client wants color
			if(ServerSocketHandler.getNumberOfConnections() == 1) { // First player to connect gets white player
				pw.println("W");
				return;
			} else {
				pw.println("B");
				return;
			}
		}
		// Relay message to other client.
		ServerSocketHandler.getRelaySocketHandler()
			.getWriterThread().sendMessage(in);
	}
}
