package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import shared.Game;

public class ClientReaderThread extends Thread {
	BufferedReader br;
	ClientNetworkParser cnp;
	boolean running;
	
	public ClientReaderThread(InputStream is, Game theGame) {
		running = true;
		cnp = new ClientNetworkParser(theGame);
		br = new BufferedReader(new InputStreamReader(is));
	}
	
	public void run() {
		String in = "";
		while(running) {
			try {
				if((in = br.readLine()) != null) {
					cnp.parseInput(in);
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
		} catch (IOException e1) {
			// It's OK.
		}
		try {
			this.join();
		} catch (InterruptedException e) {
			// It's OK.
		}
	}
}
