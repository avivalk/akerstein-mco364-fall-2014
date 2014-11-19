package kerstein.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton pickColor;
	private JButton eraser;
	private JPanel buttonPanel;
	private JButton clear;
	private JColorChooser chooser;
	private Color color;
	private JLabel whichColor;
	private JLabel stroke;
	private Canvas canvas;

	public Paint() {
		this.setSize(800, 600);
		this.setTitle("PAINT");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		Container container = getContentPane();
		container.setLayout(layout);
		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		chooser = new JColorChooser();
		pickColor = new JButton("Pick a color");
		pickColor.addActionListener(new ColorListener("pickcolor", canvas));

		add(pickColor, BorderLayout.SOUTH);

		eraser = new JButton("ERASER");
		clear = new JButton("CLEAR");
		eraser.addActionListener(new ColorListener("eraser", canvas));
		clear.addActionListener(new ColorListener("clear", canvas));

		buttonPanel = new JPanel();

		buttonPanel.add(eraser);
		buttonPanel.add(clear);
		buttonPanel.add(pickColor);

		whichColor = new JLabel();
		whichColor.setText("CURRENT COLOR");
		whichColor.setBackground(color);
		buttonPanel.add(whichColor);

		stroke = new JLabel("STROKE WIDTH:" + canvas.getStrokeWidth());
		buttonPanel.add(stroke);

		add(buttonPanel, BorderLayout.SOUTH);

		MouseActionListener listener = new MouseActionListener(canvas);
		canvas.addMouseMotionListener(listener);
		canvas.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int newStroke = (canvas.getStrokeWidth() + (e.getUnitsToScroll() * (-1)));
				if (newStroke < 1) {
					newStroke = 1;
				}
				canvas.setStrokeWidth(newStroke);
				stroke.setText("STROKE WIDTH:" + newStroke);

			}
		});

	}

	private class ColorListener implements ActionListener {
		private Canvas canvas;
		private String request;

		public ColorListener(String request, Canvas canvas) {
			this.canvas = canvas;
			this.request = request;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (request) {
			case "pickcolor":
				color = JColorChooser.showDialog(null, "Pick a color", getBackground());
				canvas.setPenColor(color);
				whichColor.setBackground(color);
				whichColor.setOpaque(true);

				whichColor.setText("CURRENT COLOR");
				break;
			case "eraser":
				canvas.setPenColor(Color.WHITE);
				break;
			case "clear":
				canvas.clear();
				break;
			}

		}
	}

	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
	}

}
