package q2_2.extracting.feature;

import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Standard_Deviation implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		Manipulation.threshold(ip);


		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;
		double count = 0;

		for (int x=0; x < w; x++) {
			for (int y=0 ; y < h; y++) {
				total += ip.getPixelValue(x, y);
				count++;
			}
		}

		double mean = total/(count);

		double sum = 0;

		for (int x=0; x < w; x++) {
			for (int y=0 ; y < h; y++) {
				sum += Math.pow((ip.getPixelValue(x, y) - mean),2);
			}
		}

		return Math.sqrt(sum/(count));
	}

}
