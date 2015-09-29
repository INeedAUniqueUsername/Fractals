package org.jointheleague.ecolban.fractals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class FractalRenderer extends JPanel implements Printable {

	// private static final Dimension PREFERRED_DIMENSION = new Dimension(1000,
	// 1000);
	private static final int MARGIN = 10;
	private static final Random RNG = new Random();

	private int level;

	private FractalSpec fractal = new Test3();

	public static void main(String[] args) throws InvocationTargetException,
	        InterruptedException {
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
		frame.setLayout(new BorderLayout());
		double h = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 2
		        * MARGIN - 50;
		double w = h * 1.272;
		setPreferredSize(new Dimension((int) w, (int) h));
		frame.add(this, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		JButton printButton = new JButton("Print");
		printButton.addActionListener(e -> printFractal());
		buttonPanel.add(printButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
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
		fillFractal(g2, fractal.getTransforms(), level);
	}

	private void fillFractal(Graphics2D g2, AffineTransform[] transforms,
	        int level) {
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
		return new Color(RNG.nextInt(256), RNG.nextInt(256), RNG.nextInt(256));
	}

	private void printFractal() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)
	        throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX(), pf.getImageableY());
		g2.scale(pf.getImageableWidth(), pf.getImageableHeight());
		fillFractal(g2, fractal.getTransforms(), fractal.getLevel());

		return PAGE_EXISTS;
	}

}
