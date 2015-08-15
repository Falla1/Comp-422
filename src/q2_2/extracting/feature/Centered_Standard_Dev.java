package q2_2.extracting.feature;

import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Centered_Standard_Dev implements Feature{

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;
		double count = 0;

		for (int x=3; x < w-3; x++) {
			for (int y=h/2 - 2 ; y < h; y++) {
				double pixel_x = ip.getPixel(x, y);
				total += pixel_x;
				count++;
			}
		}

		double mean = total/(count);

		double sum = 0;

		for (int x=3; x < w-3; x++) {
			for (int y=h/2 - 2 ; y < h; y++) {
				sum += Math.pow((ip.getPixelValue(x, y) - mean),2);
			}
		}

		return Math.sqrt(sum/(count));
	}

}
