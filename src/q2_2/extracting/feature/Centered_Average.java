package q2_2.extracting.feature;

import ij.process.ImageProcessor;

public class Centered_Average implements Feature {

	@Override
	public double valueOf(ImageProcessor ip) {

		int w = ip.getWidth();
		int h = ip.getHeight();

		int totalCenter = 0;
		int i_center = 0;

		for (int x=3; x < w-3; x++) {
			for (int y=h/2 - 2 ; y < h; y++) {
				double pixel_x = ip.getPixel(x, y);
				totalCenter += pixel_x;
				i_center++;
			}
		}

		return totalCenter/i_center;
	}

}
