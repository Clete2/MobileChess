package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class ReaderThread extends Thread {
	BufferedReader br;
	NetworkParser np; // Can be either server or client
	boolean running;
	
	public ReaderThread(InputStream is) {
		running = true;
		br = new BufferedReader(new InputStreamReader(is));
	}
	
	public void run() {
		String in = "";
		while(running) {
			try {
				if((in = br.readLine()) != null) {
					np.parseInput(in);
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
		} catch (IOException e) {
			// It's OK.
		}
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
