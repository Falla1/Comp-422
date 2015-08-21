package q2_2.extracting;

import ij.process.ImageProcessor;

public class Manipulation {


	/**
	 * Threshold preprocess
	 * Convert into binary image
	 * @param ip
	 */
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

	/**
	 * Get the mean of the image
	 * @param ip
	 * @return
	 */
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

	/**
	 * Use Sobel Edge Detection on image
	 * @param ip
	 */
	public static void edgeDect(ImageProcessor ip){

		String title = null;
	    int mask_X[][] = {{-1,0,1},
                {-2,0,2},
                {-1,0,1}};

	    int mask_Y[][] = {{-1,-2,-1},
                {0,0,0},
                {1,2,1}};


        int w = ip.getWidth();
        int h = ip.getHeight();
        ImageProcessor copy = ip.duplicate();

        //Getting the offset value
		int startX = mask_X.length/2;


        for (int x=1; x < w-2; x++) {
            for (int y=1; y < h-2; y++) {

                int pixel_x = 0;
                int pixel_y = 0;

                //Looping around the center node
                //Dependent on the offset value
				for(int filterX = -startX; filterX < startX + 1; filterX++) {
					for(int filterY = -startX; filterY < startX + 1; filterY++) {

						pixel_x += copy.getPixelValue(x + filterX, y + filterY) * mask_X[filterX+startX][filterY+startX];
					}
				}

				for(int filterX = -startX; filterX < startX + 1; filterX++) {
					for(int filterY = -startX; filterY < startX + 1; filterY++) {

						pixel_y += copy.getPixelValue(x + filterX, y + filterY) * mask_Y[filterX+startX][filterY+startX];
					}
				}

				//Calculating the new value
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
