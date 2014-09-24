package kerstein.chat;
	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

	import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

	public class ChatSocketThread extends Thread {
		Socket socket;
		JTextArea messages;

		public ChatSocketThread(Socket socket, JTextArea messages) {
			this.socket = socket;
			this.messages=messages;
		}

		public void run() {

			InputStream in;
			try {
				in = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));// getting
																						// back
																						// the
																						// HTML
																						// of
																						// the
																						// page
				String line;
				while (!"".equals((line = reader.readLine()))) {
	            messages.append("\n" + line);	
	            
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

