package org.jointheleague.ecolban.fractals;


import static java.lang.Math.sqrt;

import java.awt.geom.AffineTransform;

public class Koch implements FractalSpec {

	@Override
	public AffineTransform[] getTransforms() {
		double a = 1.0 / 6.0; // == cos(PI / 3.0) / 3.0
		double b = sqrt(3.0) / 6.0; // == sin(PI / 3.0) / 3.0
		double s = 1.0 / 3.0;
		return new AffineTransform[] {
				new AffineTransform(s, 0.0, 0.0, s, 0.0, 0.0),
				new AffineTransform(a, b, -b, a, s, 0.0),
				new AffineTransform(a, -b, b, a, 0.5, b),
				new AffineTransform(s, 0.0, 0.0, s, 2 * s, 0.0)
		};
	}

	@Override
	public int getLevel() {
		return 7;
	}

}
