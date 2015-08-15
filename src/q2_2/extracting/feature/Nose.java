package q2_2.extracting.feature;

import q2_2.extracting.Extract_Vectors;
import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Nose implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		Manipulation.threshold(ip);

		int w = ip.getWidth();
		int h = ip.getHeight();

		int total = 0 ;

		for (int x=(w/2)-3; x < (w/2)+3; x++) {
			for (int y=4; y < 8; y++) {

				double pixel_x = ip.getPixel(x, y);

				if(pixel_x == 0){
					total++;
				}


			}
		}


		return total;
	}

}
