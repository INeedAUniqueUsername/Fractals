package org.jointheleague.ecolban.fractals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Fractal extends JPanel{

	private static final int MARGIN = 10;
	private final FractalSpec fractalSpec;
	private int level;

	public static void main(String[] args) throws InvocationTargetException,
			InterruptedException {
		Fractal f = new Fractal(new Sierpinski5());
		SwingUtilities.invokeAndWait(() -> f.buildGui());
		for (int i = 0; i <= f.fractalSpec.getLevel(); i++) {
			f.level = i;
			f.repaint();
			Thread.sleep(1000);
		}
	}

	public Fractal(FractalSpec spec) {
		this.fractalSpec = spec;
	}

	private void buildGui() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		double h = 786;
		double w = 1000;
		setPreferredSize(new Dimension((int) w, (int) h));
		frame.add(this, BorderLayout.CENTER);
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
		fillFractal(g2, fractalSpec.getTransforms(), level);
	}

	private void fillFractal(Graphics2D g2, AffineTransform[] transforms, int level) {
		if (level == 0) {
			g2.setColor(randomColor());
			g2.fillRect(0, 0, 1, 1);
		} else {
			AffineTransform cachedTransform = g2.getTransform();
			for (AffineTransform t : transforms) {
				g2.transform(t);
				fillFractal(g2, transforms, level - 1);
				g2.setTransform(cachedTransform);
			}
		}
	}

	private Color randomColor() {
		ThreadLocalRandom rng = ThreadLocalRandom.current();
		return new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
	}

}
