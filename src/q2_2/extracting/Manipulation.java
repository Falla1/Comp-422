package q2_2.extracting;

import ij.process.ImageProcessor;

public class Manipulation {



	public static void threshold(ImageProcessor ip) {

		double threshold = calculateThreshold(ip);

		int w = ip.getWidth();
		int h = ip.getHeight();

		for (int x=0; x < w; x++) {
			for (int y=0; y < h; y++) {

				double pixel_x;

				pixel_x = ip.getPixel(x, y);
				int value = 0;

				if(pixel_x < threshold)
				{
					value = 0;
				}

				else if(pixel_x >= threshold)
				{
					value = 255;
				}

				ip.putPixelValue(x,y,value);

			}
		}

	}

	private static double calculateThreshold(ImageProcessor ip) {

		int w = ip.getWidth();
		int h = ip.getHeight();

		double total = 0;

		for (int x=0; x < w; x++) {
			for (int y=0; y < h; y++) {
				total += ip.getPixelValue(x, y);
			}
		}

		return (total+0.0)/(w*h);
	}

	public static void edgeDect(ImageProcessor ip){

		String title = null;
		int sobel_x[][] = {{-1,0,1},
				{-2,0,2},
				{-1,0,1}};
		int sobel_y[][] = {{-1,-2,-1},
				{0,0,0},
				{1,2,1}};
		int pixel_x;
		int pixel_y;

		int w = ip.getWidth();
		int h = ip.getHeight();

		ImageProcessor copy = ip.duplicate();

		for (int x=1; x < w-2; x++) {
			for (int y=1; y < h-2; y++) {
				pixel_x = (sobel_x[0][0] * copy.getPixel(x-1,y-1)) + (sobel_x[0][1] * copy.getPixel(x,y-1)) + (sobel_x[0][2] * copy.getPixel(x+1,y-1)) +
						(sobel_x[1][0] * copy.getPixel(x-1,y))   + (sobel_x[1][1] * copy.getPixel(x,y))   + (sobel_x[1][2] * copy.getPixel(x+1,y)) +
						(sobel_x[2][0] * copy.getPixel(x-1,y+1)) + (sobel_x[2][1] * copy.getPixel(x,y+1)) + (sobel_x[2][2] * copy.getPixel(x+1,y+1));
				pixel_y = (sobel_y[0][0] * copy.getPixel(x-1,y-1)) + (sobel_y[0][1] * copy.getPixel(x,y-1)) + (sobel_y[0][2] * copy.getPixel(x+1,y-1)) +
						(sobel_y[1][0] * copy.getPixel(x-1,y))   + (sobel_y[1][1] * copy.getPixel(x,y))   + (sobel_y[1][2] * copy.getPixel(x+1,y)) +
						(sobel_y[2][0] * copy.getPixel(x-1,y+1)) + (sobel_y[2][1] * copy.getPixel(x,y+1)) + (sobel_x[2][2] * copy.getPixel(x+1,y+1));

				int val = (int)Math.sqrt((pixel_x * pixel_x) + (pixel_y * pixel_y));

				if(val < 0)
				{
					val = 0;
				}

				if(val > 255)
				{
					val = 255;
				}

				ip.putPixel(x,y,val);
			}
		}

	}
}
