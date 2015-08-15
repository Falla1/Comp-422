package q2_2.extracting.feature;

import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Both_Eyes implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		Manipulation.threshold(ip);

		int w = ip.getWidth();
		int h = ip.getHeight();

		int total = 0 ;

		for (int x=0; x < w; x++) {
			for (int y=1; y < 6; y++) {

				double pixel_x = ip.getPixelValue(x, y);

				if(pixel_x == 0){
					total++;
				}
			}
		}




		return total;
	}

}
