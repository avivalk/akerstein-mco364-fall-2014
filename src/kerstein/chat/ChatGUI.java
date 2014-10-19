package kerstein.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame imp {
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
			socket = new Socket("localhost", 3773);
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
		ChatSocketThread thread = new ChatSocketThread(this, socket);
		thread.start();
		send.addActionListener(new SendListener(this));
	}

	private class SendListener implements ActionListener {
		private ChatGUI gui;;

		public SendListener(ChatGUI gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String output = typeMessage.getText();
			try {
				OutputStream out = socket.getOutputStream();
				out.write((output + "\n").getBytes());
				out.flush();
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

	public void printRecievedMessage(String line) {
		messages.append("\n" + line);
	}

	public static void main(String[] args) {
		ChatGUI gui = new ChatGUI();
		gui.setVisible(true);

	}

}
