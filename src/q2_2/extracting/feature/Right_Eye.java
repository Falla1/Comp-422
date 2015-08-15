package q2_2.extracting.feature;

import q2_2.extracting.Extract_Vectors;
import q2_2.extracting.Manipulation;
import ij.process.ImageProcessor;

public class Right_Eye implements Feature {

	@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;
		int count = 0;

		for (int x=0; x < w/2; x++) {
			for (int y=0; y < h/2; y++) {
				total += ip.getPixelValue(x, y);
				count++;
			}
		}

		return (total+0.0)/(count);

	}
	/*@Override
	public double valueOf(ImageProcessor main) {

		ImageProcessor ip = main.duplicate();

		Manipulation.threshold(ip);

		int w = ip.getWidth();
		int h = ip.getHeight();

		int total = 0 ;

		for (int x=w/2; x < w; x++) {
			for (int y=0; y < h/2; y++) {

				double pixel_x = ip.getPixel(x, y);

				if(pixel_x == 0){
					total++;
				}


			}
		}


		return total;
	}
*/
}
