package kerstein.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JButton drawLine;
	private JPanel shapesPanel;

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
		
		MouseDrawLineActionListener lineListener= new MouseDrawLineActionListener(canvas);
		MouseDrawRectangleListener rectListen = new MouseDrawRectangleListener(canvas);
		
		
		chooser = new JColorChooser();
		pickColor = new JButton("Pick a color");
		pickColor.addActionListener(new ButtonListener("pickcolor", canvas,lineListener,rectListen));

		add(pickColor, BorderLayout.SOUTH);

		eraser = new JButton("ERASER");
		clear = new JButton("CLEAR");
		eraser.addActionListener(new ButtonListener("eraser", canvas,lineListener,rectListen));
		clear.addActionListener(new ButtonListener("clear", canvas,lineListener,rectListen));

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
		
		shapesPanel=new JPanel();
		shapesPanel.setLayout(new GridLayout(5,1));
		
		drawRectangle = new JButton("Draw a Rectangle");
		drawRectangle.addActionListener(new ButtonListener("drawrectangle", canvas,lineListener,rectListen));
		shapesPanel.add(drawRectangle);
		
		drawLine=new JButton("Draw A Line");
		drawLine.addActionListener(new ButtonListener("drawline", canvas,lineListener,rectListen));
		shapesPanel.add(drawLine);

       add(shapesPanel,BorderLayout.EAST);
		
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
		MouseDrawLineActionListener lineListener ;
		MouseDrawRectangleListener rectListen;

		public ButtonListener(String request, Canvas canvas,MouseDrawLineActionListener lineListener, MouseDrawRectangleListener rectListen) {
			this.canvas = canvas;
			this.request = request;
			this.lineListener=lineListener;
			this.rectListen=rectListen;
		

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
			case"drawline":
				canvas.removeMouseMotionListener(rectListen);
				canvas.removeMouseListener(rectListen);
				canvas.addMouseMotionListener(lineListener);
				break;
			case "drawrectangle":
				canvas.removeMouseMotionListener(lineListener);
				canvas.addMouseMotionListener(rectListen);
				break;
				
			}

		}
	}

	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
	}

}
