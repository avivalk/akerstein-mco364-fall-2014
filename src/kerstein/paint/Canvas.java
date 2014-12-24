package kerstein.paint;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Canvas extends JComponent {
	private Color color;
	private int strokeWidth;
	private DrawListener listener;
	private BufferedImage[] layers;
	private int layerSelected;
	private boolean clear;

	public Canvas() {
		layers = new BufferedImage[4];
		setLayerSelected(0);
		for (int i = 0; i < 4; i++) {
			layers[i] = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
			clear(i);
		}
		this.color = Color.BLACK;
		setDrawListener(new PencilListener(this));
	}

	public void setLayerSelected(int layeredSelected) {
		this.layerSelected = layeredSelected;
	}

	public int getLayerSelected() {
		return layerSelected;
	}

	public void setDrawListener(DrawListener dl) {
		this.removeMouseMotionListener(listener);
		this.removeMouseListener(listener);
		this.addMouseMotionListener(dl);
		this.addMouseListener(dl);
		this.listener = dl;
	}

	public BufferedImage[] getLayers() {
		return layers;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public Graphics2D getGraphicsPen() {
		BufferedImage[] images = getLayers();
		BufferedImage selected = getLayers()[getLayerSelected()];
		Graphics2D gr = (Graphics2D) selected.getGraphics();
		gr.setColor(this.color);
		return gr;

	}

	public void setStrokeWidth(int width) {
		this.strokeWidth = width;
		BasicStroke basic = new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		getGraphicsPen().setStroke(basic);
	}

	public Color getColor() {
		return color;

	}

	public void setPenColor(Color color) {
		this.color = color;
	}

	public void clear(int layer) {
		if (layer == 0) {
			Graphics2D g = (Graphics2D) layers[0].getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);
			g.setColor(this.color);
		} else {
			Graphics2D graphics = (Graphics2D) layers[layer].getGraphics();
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
			graphics.fillRect(0, 0, 800, 600);
			// reset composite
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			graphics.setColor(this.color);
		}

		repaint();
		clear=true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 4; i++) {
			g.drawImage(layers[i], 0, 0, null);
			if(clear==false && i==layerSelected){
				g.setColor(this.color);
				setStrokeWidth(this.strokeWidth);
				listener.drawPreview((Graphics2D) g);
			}
		}
		
	}
}
