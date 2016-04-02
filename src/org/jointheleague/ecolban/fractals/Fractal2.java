package org.jointheleague.ecolban.fractals;

import java.awt.geom.AffineTransform;

public class Fractal2 implements FractalSpec{

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
		        new AffineTransform(0.33, 0.0, 0.0, 0.33, 0.33, 0.33), //Center
		        new AffineTransform(0.33, 0.0, 0.0, 0.33, 0.66, 0.0), //Up-Right
		        new AffineTransform(0.33, 0.0, 0.0, 0.33, 0.0, 0.0), //Up-Left
		        new AffineTransform(0.33, 0.0, 0.0, 0.33, 0.66, 0.66), //Down-Right
		        new AffineTransform(0.33, 0.0, 0.0, 0.33, 0.0, 0.66), //Down Left
		};
	}

	@Override
	public int getLevel() {
		return 5;
	}

}
