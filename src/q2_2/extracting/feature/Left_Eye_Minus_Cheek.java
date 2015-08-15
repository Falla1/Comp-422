package q2_2.extracting.feature;

import ij.process.ImageProcessor;
import q2_2.extracting.Manipulation;

public class Left_Eye_Minus_Cheek implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double eyeTotal = 0;
		double cheekTotal = 0;
		int count = 0 ;

		for (int x=3; x < 6; x++) {
			for (int y=2; y < 5; y++) {
				eyeTotal += ip.getPixelValue(x, y);
				count++;
			}
		}

		for (int x=3; x < 6; x++) {
			for (int y=5; y < 8; y++) {
				cheekTotal += ip.getPixelValue(x, y);
				count++;
			}
		}


		return (eyeTotal/count) - (cheekTotal/count);
	}

}
