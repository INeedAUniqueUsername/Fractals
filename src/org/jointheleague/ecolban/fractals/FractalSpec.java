package org.jointheleague.ecolban.fractals;

import java.awt.geom.AffineTransform;

public interface FractalSpec {
	
	AffineTransform[] getTransforms();
	
	int getLevel();
}
