package kerstein.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {
	private JTextArea messages;
	private JTextField typeMessage;
	private JButton send;
	private JPanel panel;

	public ChatGUI() throws IOException {
		this.setTitle("Chat");
		this.setSize(600, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		messages = new JTextArea("OUR CHAT");
		typeMessage = new JTextField();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		send = new JButton("SEND");
		this.setLayout(new BorderLayout());
		this.add(messages, BorderLayout.NORTH);
		panel.add(typeMessage, BorderLayout.CENTER);
		panel.add(send, BorderLayout.EAST);
		this.add(panel, BorderLayout.SOUTH);
       send.addActionListener(new SendListener(typeMessage, messages));
	}
	private class SendListener implements ActionListener{
		private JTextField typeMessage;
		private JTextArea messages;
		public SendListener(JTextField typeMessage, JTextArea messages){
			this.typeMessage=typeMessage;
			this.messages=messages;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0){
			String output=typeMessage.getText();
			messages.append("\n" + output);
			try {
				ChatClient client= new ChatClient(output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			typeMessage.setText("");
		}
		
	}

	public JTextField getTypeMessages() {
		return typeMessage;
	}

	public JTextArea getMessages() {
		return messages;

	}

	public static void main(String[] args) {
		ChatGUI gui;
		try {
			gui = new ChatGUI();
			gui.setVisible(true);

			JTextField typeMessage = gui.getTypeMessages();
			JTextArea messages = gui.getMessages();
			Socket socket = null;
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true) {// server should constantly be listening
				socket = serverSocket.accept();
				ChatSocketThread thread = new ChatSocketThread(socket,  messages);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
