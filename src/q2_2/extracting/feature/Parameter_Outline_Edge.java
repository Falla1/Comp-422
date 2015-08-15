package q2_2.extracting.feature;

import ij.process.ImageProcessor;
import q2_2.extracting.Manipulation;

public class Parameter_Outline_Edge implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		Manipulation.edgeDect(ip);
		Manipulation.threshold(ip);

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

		return total/i;
	}

}
