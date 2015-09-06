package org.jointheleague.ecolban.fractals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Fractal extends JPanel implements Runnable {

    private static final int FRAME_SIZE = 1000;
    private static final int MARGIN = 10;
    private static final double UNIT = FRAME_SIZE - 2 * MARGIN;
    private static final Rectangle2D UNIT_RECTANGLE = new Rectangle2D.Double(0.0, 0.0, UNIT, UNIT);

    private int level;

    private static final AffineTransform[] SIERPINSKI_TRANSFORMS = new AffineTransform[] {
                    new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.0, UNIT / 2.0),
                    new AffineTransform(0.5, 0.0, 0.0, 0.5, UNIT / 2.0, UNIT / 2.0),
                    new AffineTransform(0.5, 0.0, 0.0, 0.5, UNIT / 4.0, 0.0)
    };

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Fractal f = new Fractal();
        SwingUtilities.invokeAndWait(f);

        for (int i = 0; i < 11; i++) {
            f.level = i;
            f.repaint();
            Thread.sleep(300);
        }
    }

    public void run() {
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
        g2.setColor(Color.GRAY);
        g2.translate(MARGIN, MARGIN);
        drawFractal(SIERPINSKI_TRANSFORMS, g2, level);
    }

    private void drawFractal(AffineTransform[] transforms, Graphics2D g2, int level) {
        if (level == 0) {
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

}
