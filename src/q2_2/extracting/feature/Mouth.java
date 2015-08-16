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

		for (int x=0; x < w; x++) {
			for (int y=h/2; y < h; y++) {
				double pixel_x = ip.getPixel(x, y);

				if(pixel_x == 0){
					total++;
				}
			}
		}

		return (total);

	}

}
