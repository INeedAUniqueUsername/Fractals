package org.jointheleague.ecolban.fractals;

import java.awt.geom.AffineTransform;

public class Test1 implements FractalSpec {

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
				// new AffineTransform(-0.38, 0.0, 0.0, 0.38, 1.0, 0.0),
				new AffineTransform(0.0, -0.6, 0.6, 0.0, 0.0, 0.6),
				new AffineTransform(-0.6, 0.0, 0.0, -0.4, 1.0, 1.0),
				new AffineTransform(0.4, 0.0, 0.0, -0.4, 0.0, 1.0)
		};
	}

	@Override
	public int getLevel() {
		return 10;
	}

}
