package kerstein.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton red;
	private JButton green;
	private JButton blue;
	private JPanel buttonPanel;

	public Paint() {
		this.setSize(800, 600);
		this.setTitle("PAINT");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		Container container = getContentPane();
		container.setLayout(layout);
		Canvas canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		red = new JButton();
		red.setBackground(Color.RED);
		blue = new JButton();
		blue.setBackground(Color.BLUE);
		green = new JButton();
		green.setBackground(Color.GREEN);
		buttonPanel = new JPanel();
		buttonPanel.add(red);
		buttonPanel.add(blue);
		buttonPanel.add(green);
		add(buttonPanel, BorderLayout.SOUTH);

		MouseActionListener listener = new MouseActionListener(canvas);
		canvas.addMouseMotionListener(listener);
		red.addActionListener(new ColorListener("red", canvas));
		green.addActionListener(new ColorListener("green", canvas));
		blue.addActionListener(new ColorListener("blue", canvas));
	}

	private class ColorListener implements ActionListener {
		private String color;
		private Canvas canvas;

		public ColorListener(String color, Canvas canvas) {
			this.color = color;
			this.canvas = canvas;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (color) {
			case "red":
				canvas.setPenColor(Color.RED);
				break;
			case "green":
				canvas.setPenColor(Color.GREEN);
				break;
			case "blue":
				canvas.setPenColor(Color.BLUE);
				break;
			default:
				canvas.setPenColor(Color.BLACK);
				break;
			}
		}

	}

	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
	}

}
