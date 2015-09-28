package org.jointheleague.ecolban.fractals;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.geom.AffineTransform;

public class Koch implements FractalSpec {

	@Override
	public AffineTransform[] getTransforms() {
		return new AffineTransform[] {
		        new AffineTransform(1.0 / 3.0, 0.0, 0.0, 1.0 / 3.0, 0.0, 0.0),
		        new AffineTransform(cos(PI / 3.0) / 3.0, sin(PI / 3) / 3,
		                -sin(PI / 3) / 3.0, cos(PI / 3.0) / 3.0, 1.0 / 3.0, 0.0),
		        new AffineTransform(
		                cos(2.0 * PI / 3.0) / 3.0, sin(2.0 * PI / 3) / 3,
		                sin(2.0 * PI / 3) / 3.0, -cos(2.0 * PI / 3.0) / 3.0,
		                2.0 / 3.0, 0.0),
		        new AffineTransform(1.0 / 3.0, 0.0, 0.0, 1.0 / 3.0, 2.0 / 3.0, 0.0)
		};
	}

	@Override
	public int getLevel() {
		return 5;
	}

}
