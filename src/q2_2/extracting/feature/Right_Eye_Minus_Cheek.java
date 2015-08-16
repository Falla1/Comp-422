package q2_2.extracting.feature;

import ij.process.ImageProcessor;

public class Right_Eye_Minus_Cheek implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double eyeTotal = 0;
		double cheekTotal = 0;
		int count = 0 ;

		for (int x=14; x < 17; x++) {
			for (int y=2; y < 5; y++) {
				eyeTotal += ip.getPixelValue(x, y);
				count++;
			}
		}

		for (int x=14; x < 17; x++) {
			for (int y=11; y < 14; y++) {
				cheekTotal += ip.getPixelValue(x, y);
			}
		}


		return (eyeTotal/count) - (cheekTotal/count);
	}

}