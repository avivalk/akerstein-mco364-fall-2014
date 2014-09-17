package kerstein.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkRequest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("www.amazon.com", 80);// params are ip
															// address and port
															// number
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		String request="GET /index.html\n\n";
		out.write(request.getBytes());//converting String to byte array
		out.flush();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));//getting back the HTML of the page
		String line;
		while((line=reader.readLine())!=null){
		System.out.println(line);
		

	}

}
}
