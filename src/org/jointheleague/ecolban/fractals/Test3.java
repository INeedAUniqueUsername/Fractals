package org.jointheleague.ecolban.fractals;

import java.awt.geom.AffineTransform;

import static java.lang.Math.sqrt;

public class Test3 implements FractalSpec {
	//Set the panel dimension to 1292x1020 for best results

	@Override
	public AffineTransform[] getTransforms() {
		double golden = (-1.0 + sqrt(5.0)) / 2.0;
		double golden2 = golden * golden;
		return new AffineTransform[] {
		        new AffineTransform(0.0, -1.0, golden, 0, 0.0, 1.0),
		        new AffineTransform(0.0, golden, -golden2, 0.0, 1.0, golden2),
		};
	}

	@Override
	public int getLevel() {
		return 18;
	}

}
