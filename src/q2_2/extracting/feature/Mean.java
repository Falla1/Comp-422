package q2_2.extracting.feature;

import ij.process.ImageProcessor;

public class Mean implements Feature{

	@Override
	public double valueOf(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;

		for (int x=0; x < w; x++) {
			for (int y=0; y < h; y++) {
				total += ip.getPixelValue(x, y);
			}
		}

		return total/(w*h);
	}

}
