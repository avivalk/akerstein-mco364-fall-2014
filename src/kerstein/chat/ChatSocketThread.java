package kerstein.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatSocketThread extends Thread {
	private Socket socket;
	private ChatGUI gui;

	public ChatSocketThread(ChatGUI gui, Socket socket) {
		this.gui = gui;
		this.socket = socket;
	}

	public void run() {
		InputStream in;
		try {
			in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				gui.printRecievedMessage(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
