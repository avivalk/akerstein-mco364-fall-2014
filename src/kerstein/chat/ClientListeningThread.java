package kerstein.chat;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListeningThread extends Thread {

	/*
	 * In order for the client to receive messages from the server, it must
	 * "listen out" for them. I am doing this in a separate thread, so that it
	 * can basically "run in the background," allowing the chat to be
	 * bidirectional. The client will be listening out for the server, and
	 * whenever the server sends it a message it will "print it out" -- but in
	 * the meantime the client can continue chatting without waiting for a
	 * response from the server.
	 */

	private Socket socket;
	private ChatScreen screen;

	public ClientListeningThread(Socket socket, ChatScreen screen) {
		this.screen = screen;
		this.socket = socket;
	}

	@Override
	public void run() {
		// Get the input stream
		InputStream input = null;
		try {
			input = this.socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		// In an infinite loop -- it should constantly listen!
		String text;
		try {
			while ((text = reader.readLine()) != null) {
				if (!"".equals(text)) {
					UpdateGUIThread update = new UpdateGUIThread(this.screen, text, Color.GREEN);
					update.start();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
