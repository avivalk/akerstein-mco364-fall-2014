package kerstein.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread extends Thread {
	Socket socket;
	int counter;

	public SocketThread(Socket socket, int counter) {
		this.socket = socket;
		this.counter = counter;
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
				System.out.println(line);
			}
			OutputStream out = socket.getOutputStream();
			String response = "<h2> This is request number " + counter + "<h2>";
			out.write("HTTP/1.1 200 OK\n".getBytes());
			out.write("Content-Type: text/html; charset=utf-8\n".getBytes());
			out.write((("Content-Length: " + response.length() + "\n\n").getBytes()));
			out.write(response.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
