package kerstein.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ResponseServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket(8080);//x need ip address because using local address, just standard port 8080
		Socket socket=serverSocket.accept();//when connect to serversocket, a socket is returned
        
		
		InputStream in = socket.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));//getting back the HTML of the page
		String line;
		while((line=reader.readLine())!=null){
		System.out.println(line);
	}
		OutputStream out=socket.getOutputStream();
		out.write("Hello World".getBytes());
		out.flush();
		out.close();
}
}
