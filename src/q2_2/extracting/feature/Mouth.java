package q2_2.extracting.feature;

import q2_2.extracting.Extract_Vectors;
import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Mouth implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();
		
		Manipulation.threshold(ip);

		double total = 0;
		int count = 0;

		for (int x=0; x < w; x++) {
			for (int y=h/2; y < h; y++) {
				total += ip.getPixelValue(x, y);
				count++;
			}
		}

		return (total+0.0)/(count);

	}

}
