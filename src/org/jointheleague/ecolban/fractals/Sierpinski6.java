package org.jointheleague.ecolban.fractals;

import java.awt.geom.AffineTransform;

public class Sierpinski6 implements FractalSpec{

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
		        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.5, 0.5),
		        new AffineTransform(0.5, 0.5, 0.0, 0.5, 0.5, 0.5),
		        new AffineTransform(0.5, 0.0, 0.0, 0.5, 0.25, 0.25)
		};
	}

	@Override
	public int getLevel() {
		return 10;
	}

}
