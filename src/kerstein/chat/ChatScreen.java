package kerstein.chat;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatScreen extends JFrame {

	// GUI components
	private ArrayList<JLabel> convoPieces;
	private JPanel convoPanel;
	// private ConversationComponent convoComp;
	private JScrollPane convoScroll;
	private JTextField input;
	private JButton send;
	private int counter;

	// Chat components
	private Socket socket;
	private OutputStream out;
	private ClientListeningThread thread;

	public ChatScreen(String ip, int port) {

		// Set up the socket
		try {
			this.socket = new Socket(ip, port);
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.thread = new ClientListeningThread(this.socket, this);
		this.thread.start();

		// Set up the components
		this.convoPieces = new ArrayList<JLabel>();
		this.convoPanel = new JPanel();

		GridLayout gl = new GridLayout(5000, 1);
		gl.setVgap(3);
		this.convoPanel.setLayout(gl);

		JLabel label;

		for (int i = 0; i < 5000; i++) {
			label = new JLabel();
			label.setOpaque(true);
			this.convoPieces.add(label);
			this.convoPanel.add(label);
		}

		this.counter = 0;

		// this.convoComp = new ConversationComponent(this.convoPieces,
		// this.convoPanel);

		// JPanel conversationComponentPanel = new JPanel();
		// conversationComponentPanel.add(this.convoComp);
		this.convoScroll = new JScrollPane(convoPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.input = new JTextField(20);
		this.send = new JButton("SEND MESSAGE");

		// Create a bottom panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		// Add text field and button to the panel
		panel.add(this.input);
		panel.add(this.send);

		// Add components to the JFrame
		this.add(this.convoScroll, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);

		// Add action listener to send button
		this.send.addActionListener(new SendListener(this));

		// Set up basic JFrame stuff
		this.setTitle("CHAT WINDOW");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Get conversation component
	// public ConversationComponent getConvoComp(){
	// return this.convoComp;
	// }

	public void incrementCounter() {
		this.counter++;
	}

	// Get current label
	public JLabel getCurrentConvoLabel() {
		return this.convoPieces.get(counter);
	}

	private class SendListener implements ActionListener {

		private ChatScreen screen;

		private SendListener(ChatScreen screen) {
			this.screen = screen;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String text = input.getText();
			if (!"".equals(text) && text != null) {
				input.setText("");
				try {
					out.write((text + "\n").getBytes());
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static void main(String[] args) {
		ChatScreen screen = new ChatScreen("localhost", 3773);
	}

}
