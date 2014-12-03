package kerstein.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
	private JButton drawRectangle;
	private JButton pencil;
	private JPanel shapesPanel;
	private JButton fillRect;
	private JButton drawOval;
	private JButton fillOval;
	private JButton drawLine;

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

		PencilListener pencilListener = new PencilListener(canvas);
		MouseDrawRectangleListener rectListen = new MouseDrawRectangleListener(canvas);
		MouseDrawOvalListener ovalListen = new MouseDrawOvalListener(canvas);
		MouseDrawLineListener lineListen = new MouseDrawLineListener(canvas);

		chooser = new JColorChooser();
		pickColor = new JButton("Pick a color");
		pickColor.addActionListener(new ButtonListener("pickcolor", canvas, pencilListener, rectListen, ovalListen,
				lineListen));

		add(pickColor, BorderLayout.SOUTH);

		eraser = new JButton("ERASER");
		clear = new JButton("CLEAR");
		eraser.addActionListener(new ButtonListener("eraser", canvas, pencilListener, rectListen, ovalListen,
				lineListen));
		clear.addActionListener(new ButtonListener("clear", canvas, pencilListener, rectListen, ovalListen, lineListen));

		buttonPanel = new JPanel();

		buttonPanel.add(eraser);
		buttonPanel.add(clear);
		buttonPanel.add(pickColor);

		whichColor = new JLabel();
		whichColor.setText("CURRENT COLOR");
		whichColor.setBackground(Color.BLACK);
		whichColor.setOpaque(true);

		buttonPanel.add(whichColor);

		stroke = new JLabel("STROKE WIDTH:" + canvas.getStrokeWidth());
		buttonPanel.add(stroke);

		add(buttonPanel, BorderLayout.SOUTH);

		shapesPanel = new JPanel();
		shapesPanel.setLayout(new GridLayout(2, 5));

		pencil = new JButton("Pen");
		pencil.addActionListener(new ButtonListener("pencil", canvas, pencilListener, rectListen, ovalListen,
				lineListen));
		shapesPanel.add(pencil);

		drawRectangle = new JButton("Draw a Rectangle OutLine");
		drawRectangle.addActionListener(new ButtonListener("drawrectangle", canvas, pencilListener, rectListen,
				ovalListen, lineListen));
		shapesPanel.add(drawRectangle);

		fillRect = new JButton("Draw a Full Rectangle");
		fillRect.addActionListener(new ButtonListener("drawfillrectangle", canvas, pencilListener, rectListen,
				ovalListen, lineListen));
		shapesPanel.add(fillRect);

		drawOval = new JButton("Draw an Oval Outline");
		drawOval.addActionListener(new ButtonListener("drawoval", canvas, pencilListener, rectListen, ovalListen,
				lineListen));
		shapesPanel.add(drawOval);

		fillOval = new JButton("Draw a Full Oval");
		fillOval.addActionListener(new ButtonListener("drawfulloval", canvas, pencilListener, rectListen, ovalListen,
				lineListen));
		shapesPanel.add(fillOval);

		drawLine = new JButton("Draw a Line");
		drawLine.addActionListener(new ButtonListener("drawline", canvas, pencilListener, rectListen, ovalListen,
				lineListen));
		shapesPanel.add(drawLine);

		add(shapesPanel, BorderLayout.NORTH);

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

	private class ButtonListener implements ActionListener {
		private Canvas canvas;
		private String request;
		private PencilListener lineListener;
		private MouseDrawRectangleListener rectListen;
		private MouseDrawOvalListener ovalListen;
		private MouseDrawLineListener lineListen;

		public ButtonListener(String request, Canvas canvas, PencilListener lineListener,
				MouseDrawRectangleListener rectListen, MouseDrawOvalListener ovalListen,
				MouseDrawLineListener lineListen) {
			this.canvas = canvas;
			this.request = request;
			this.lineListener = lineListener;
			this.rectListen = rectListen;
			this.ovalListen = ovalListen;
			this.lineListen = lineListen;

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
				canvas.setDrawListener(lineListener);
				break;
			case "clear":
				canvas.clear();
				canvas.setPenColor(color);
				break;
			case "pencil":
				canvas.setDrawListener(lineListener);
				break;
			case "drawrectangle":
				canvas.setDrawListener(rectListen);
				rectListen.setFillShape(false);
				break;
			case "drawfillrectangle":
				canvas.setDrawListener(rectListen);
				rectListen.setFillShape(true);
				break;
			case "drawoval":
				canvas.setDrawListener(ovalListen);
				ovalListen.setFillShape(false);
				break;
			case "drawfulloval":				
				canvas.setDrawListener(ovalListen);
				ovalListen.setFillShape(true);
				break;
			case "drawline":
				canvas.setDrawListener(lineListen);
				break;

			}

		}
	}

	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
	}

}
