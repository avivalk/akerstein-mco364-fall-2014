package kerstein.chat;

import java.awt.Color;

import javax.swing.JLabel;

public class UpdateGUIThread extends Thread {

	private JLabel convoPiece;
	private ChatScreen screen;
	// private ConversationComponent convoComponent;
	private String text;
	private Color color;

	public UpdateGUIThread(ChatScreen screen, String text, Color color) {
		this.screen = screen;
		// this.convoComponent = convoComp;
		this.convoPiece = screen.getCurrentConvoLabel();
		this.text = text;
		this.color = color;
	}

	@Override
	public void run() {
		this.screen.incrementCounter();
		this.convoPiece.setOpaque(true);
		this.convoPiece.setText(this.text);
		this.convoPiece.setBackground(this.color);
		this.convoPiece.repaint();
		this.screen.repaint();

	}

}
