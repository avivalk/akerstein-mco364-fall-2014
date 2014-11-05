package kerstein.paint;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Paint extends JFrame {

	private static final long serialVersionUID = 1L;

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
		MouseListener listener=new MouseListener(canvas);
		canvas.addMouseMotionListener(listener)
;	}

	public static void main(String[] args) {
		Paint paint = new Paint();
		paint.setVisible(true);
	}

}
