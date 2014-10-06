package kerstein.chat;
	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

	public class ChatSocketThread extends Thread {
		private ServerSocket serverSocket;
		JTextArea messages;

		public ChatSocketThread(JTextArea messages) {
			this.messages=messages;
		}

		public void run() {
			try {
				serverSocket = new ServerSocket(8080);

				int clientNo = 1;
				while (true) {
					Socket socket = serverSocket.accept();
					try {

				InputStream in = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line;
				while ((line=reader.readLine())!=null) {
	            messages.append("\n" + line);	
	            
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

					clientNo++;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
