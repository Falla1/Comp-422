package q2_2.extracting.feature;

import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Parameter_Minus_Center implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		int total = 0 ;
		int i = 0;

		for (int x=0; x < w; x++) {
			for (int y=0; y < 1; y++) {
				double pixel_x = ip.getPixel(x, y);
				total += pixel_x;
				i++;
			}
		}

		for (int x=0; x < w; x++) {
			for (int y=h-1; y < h; y++) {
				double pixel_x = ip.getPixel(x, y);
				total += pixel_x;
				i++;
			}
		}

		for (int y=1; y < h-1; y++) {
			for (int x=0; x < 1; x++) {
				double pixel_x = ip.getPixel(x, y);
				total += pixel_x;
				i++;
			}
		}

		for (int y=1; y < h-1; y++) {
			for (int x=w-1; x < w; x++) {
				double pixel_x = ip.getPixel(x, y);
				total += pixel_x;
				i++;
			}
		}

		double parameter_mean = (total + 0.0)/i;

		int totalCenter = 0;
		int i_center = 0;

		for (int x=(w/2)-2; x < (w/2)+2; x++) {
			for (int y=(h/2)-2; y < (h/2)+2; y++) {
				double pixel_x = ip.getPixel(x, y);
				totalCenter += pixel_x;
				i_center++;
			}
		}

		return parameter_mean - ((totalCenter + 0.0)/i_center);

	}

}
