package q2_2.extracting.feature;

import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Left_Eye_Position implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;
		int count = 0 ;

		for (int x=0; x < (w/2); x++) {
			for (int y=3; y < 6; y++) {
				total += ip.getPixelValue(x, y);
				count++;
			}
		}

		return total/count;
	}

}
