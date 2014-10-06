package kerstein.chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	String output;
	
	public ChatClient(String output) throws UnknownHostException, IOException{
	this.output=output;
	Socket socket=new Socket("192.168.1.106", 8080);
	OutputStream out = socket.getOutputStream();
	out.write(output.getBytes());
	out.flush();
	out.close();
}
}
