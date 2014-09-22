package kerstein.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ResponseServer {

	public static void main(String[] args) throws IOException {

		Socket socket = null;
		int counter = 0;
		ServerSocket serverSocket = new ServerSocket(8080);// x need ip address
															// because using
															// local address,
															// just
		// standard port 8080

		while (true) {// server should constantly be listening
			socket = serverSocket.accept();// when connect to serversocket,
			// a socket is returned
			counter++;
			SocketThread thread = new SocketThread(socket, counter);
			thread.start();
		}
	}
}
