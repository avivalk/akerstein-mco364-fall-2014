package kerstein.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class SocketHandler extends Thread {
   private Socket socket;
   private  BlockingQueue<String>messages;
   
   public SocketHandler(Socket socket,BlockingQueue<String>messages){
	   this.socket=socket;
	   this.messages=messages;
   }
   public void run() {

		InputStream in;
		try {
			in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line=reader.readLine())!=null) {
}                 messages.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
}
