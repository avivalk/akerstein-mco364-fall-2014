package kerstein.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {
	private JTextArea messages;
	private JTextField typeMessage;
	private JButton send;
	private JPanel panel;
	private Socket socket;

	public ChatGUI() {
		this.setTitle("Chat");
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			socket = new Socket("localhost", 5050);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		ChatSocketThread thread = new ChatSocketThread(messages, socket);
		thread.start();
		send.addActionListener(new SendListener(typeMessage, messages));
	}

	private class SendListener implements ActionListener {
		private JTextField typeMessage;
		private JTextArea messages;

		public SendListener(JTextField typeMessage, JTextArea messages) {
			this.typeMessage = typeMessage;
			this.messages = messages;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String output = typeMessage.getText() + "\n";
			try {
				ChatClient client = new ChatClient(output, getSocket());
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

	public Socket getSocket() {
		return socket;
	}

	public static void main(String[] args) {
		ChatGUI gui = new ChatGUI();
		gui.setVisible(true);

	}

}
