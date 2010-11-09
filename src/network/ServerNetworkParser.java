package network;

import java.io.PrintWriter;

public class ServerNetworkParser extends NetworkParser {
	PrintWriter pw;
	
	public ServerNetworkParser(PrintWriter pw) {
		this.pw = pw;
	}
	
	public void parseInput(String in) {
		if(in.equalsIgnoreCase("C")) { // Client wants color
		}
	}
}
