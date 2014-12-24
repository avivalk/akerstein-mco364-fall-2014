package kerstein.paint.message;


public class MessageFactory {

	private PaintMessage paintMessage;
	
	public PaintMessage getMessage(String message){
		PaintMessage paintMessage = null;
		String[] action=message.split(" ");
		switch(action[0]){
		case "LINE":
			paintMessage=new LineMessage(Integer.parseInt(action[1]),Integer.parseInt(action[2]), Integer.parseInt(action[3]),Integer.parseInt(action[4]),Integer.parseInt(action[5]),Integer.parseInt(action[6]));
			break;
		}
		return paintMessage;
	}
}
