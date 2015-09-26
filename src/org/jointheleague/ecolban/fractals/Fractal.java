package org.jointheleague.ecolban.fractals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import static java.lang.Math.*;

@SuppressWarnings("serial")
public class Fractal extends JPanel {

	private static final int FRAME_SIZE = 1000;
	private static final int MARGIN = 10;
	private static final double UNIT = FRAME_SIZE - 2 * MARGIN;
	private static final Rectangle2D UNIT_RECTANGLE =
	        new Rectangle2D.Double(0.0, 0.0, 1.0, 1.0);

	private int level;
	private Random rng = new Random();

	private static final AffineTransform[] SIERPINSKI_TRANSFORMS = new AffineTransform[] {
	        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.0, 0.5),
	        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.5, 0.5),
	        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.25, 0.0)
	};

	private static final AffineTransform[] KOCH_TRANSFORMS = new AffineTransform[] {
	        new AffineTransform(1.0 / 3.0, 0.0, 0.0, 1.0 / 3.0, 0.0, 0.0),
	        new AffineTransform(cos(PI / 3.0) / 3.0, sin(PI / 3) / 3,
	                -sin(PI / 3) / 3.0, cos(PI / 3.0) / 3.0, 1.0 / 3.0, 0.0),
	        new AffineTransform(
	                cos(2.0 * PI / 3.0) / 3.0, sin(2.0 * PI / 3) / 3,
	                sin(2.0 * PI / 3) / 3.0, -cos(2.0 * PI / 3.0) / 3.0,
	                2.0 / 3.0, 0.0),
	        new AffineTransform(1.0 / 3.0, 0.0, 0.0, 1.0 / 3.0, 2.0 / 3.0, 0.0)
	};
	
	private static final AffineTransform[] TEST_TRANSFORMS = new AffineTransform[] {
			// new AffineTransform(-0.38, 0.0, 0.0, 0.38, 1.0, 0.0),
			new AffineTransform(0.0, -0.6, 0.6, 0.0, 0.0, 0.6),
			new AffineTransform(-0.6, 0.0, 0.0, -0.6, 1.0, 1.0),
			new AffineTransform(0.38, 0.0, 0.0, -0.38, 0.0, 1.0)
	};


	public static void main(String[] args) throws InvocationTargetException,
	        InterruptedException {
		Fractal f = new Fractal();
		SwingUtilities.invokeAndWait(() -> f.buildGui());

		for (int i = 0; i < 8; i++) {
			f.level = i;
			f.repaint();
			Thread.sleep(1000);
		}
	}

	private void buildGui() {
		JFrame frame = new JFrame();
		setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.translate(MARGIN, MARGIN);
		g2.scale(UNIT, UNIT);
		drawFractal(KOCH_TRANSFORMS, g2, level);
	}

	private void drawFractal(AffineTransform[] transforms, Graphics2D g2,
	        int level) {
		if (level == 0) {
			g2.setColor(randomColor());
			g2.fill(UNIT_RECTANGLE);
		} else {
			AffineTransform cachedTransform = g2.getTransform();
			for (AffineTransform t : transforms) {
				g2.transform(t);
				drawFractal(transforms, g2, level - 1);
				g2.setTransform(cachedTransform);
			}
		}
	}
	
	private Color randomColor() {
		return new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
	}

}
