package q2_2.extracting.feature;

import ij.process.ImageProcessor;
import q2_2.extracting.Manipulation;

public class Histogram implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;

		for (int x=0; x < w; x++) {
			for (int y=0; y < h; y++) {
				total += ip.getPixelValue(x, y);
			}
		}

		return (1/(w*h)) * total;

	}
}
