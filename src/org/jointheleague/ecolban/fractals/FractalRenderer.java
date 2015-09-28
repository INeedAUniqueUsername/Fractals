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

@SuppressWarnings("serial")
public class FractalRenderer extends JPanel {

	private static final Dimension PREFERRED_DIMENSION = new Dimension(1000, 1000);
	private static final int MARGIN = 10;
	private static final Random RNG = new Random();

	private int level;

	private FractalSpec fractal = new Sierpinski();

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		FractalRenderer f = new FractalRenderer();
		SwingUtilities.invokeAndWait(() -> f.buildGui());
		for (int i = 0; i <= f.fractal.getLevel(); i++) {
			f.level = i;
			f.repaint();
			Thread.sleep(1000);
		}
	}

	private void buildGui() {
		JFrame frame = new JFrame();
		setPreferredSize(PREFERRED_DIMENSION);
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
		g2.scale(getWidth() - 2 * MARGIN, getHeight() - 2 * MARGIN);
		fillFractal(fractal.getTransforms(), g2, level);
	}

	private void fillFractal(AffineTransform[] transforms, Graphics2D g2, int level) {
		if (level == 0) {
			g2.setColor(randomColor());
			g2.fillRect(0, 0, 1, 1);
		} else {
			AffineTransform cachedTransform = g2.getTransform();
			for (AffineTransform t : transforms) {
				g2.transform(t);
				fillFractal(transforms, g2, level - 1);
				g2.setTransform(cachedTransform);
			}
		}
	}

	private Color randomColor() {
		return new Color(RNG.nextInt(256), RNG.nextInt(256), RNG.nextInt(256));
	}

}
