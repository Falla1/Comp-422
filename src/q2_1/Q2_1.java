package q2_1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.process.ImageProcessor;

public class Q2_1 {


	private double sobel_x[][];

	private int matrixSize = 3;

	private double sobel_value = 1.0/(matrixSize * matrixSize);

	private final int threshold = 170;

	public Q2_1(String dir){

		ImagePlus image = new ImagePlus(dir  + "2.1/hubble.tif");

		ImageProcessor ip = image.getProcessor();

		sobel_x = new double[matrixSize][matrixSize];

		for(int i = 0 ; i < matrixSize ; i ++){
			for(int j = 0 ; j < matrixSize ; j ++){
				sobel_x[i][j] = sobel_value;
			}
		}

		meanFilter(ip);

		threshold(ip);

		final ImageWindow window = new ImageWindow(image);

		window.pack();

		window.addWindowStateListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				window.dispose();
			}
		}
				);


	}


	private void threshold(ImageProcessor ip) {


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
				else{
					throw new Error("Unsure why this would happen. Debugging purposes.");
				}

				ip.putPixelValue(x,y,value);

			}
		}

	}


	public void meanFilter(ImageProcessor ip) {

		int w = ip.getWidth();
		int h = ip.getHeight();

		ImageProcessor copy = ip.duplicate();

		int startX = matrixSize/2;

		for (int x=startX; x < w - startX; x++) {
			for (int y=startX; y < h - startX; y++) {

				int pixel_x = 0;

				for(int filterX = -startX; filterX < startX + 1; filterX++) {
					for(int filterY = -startX; filterY < startX + 1; filterY++) {

						pixel_x += copy.getPixelValue(x + filterX, y + filterY) * sobel_x[filterX+startX][filterY+startX];
					}
				}

				if(pixel_x < 0)
				{
					pixel_x = 0;
				}

				if(pixel_x > 255)
				{
					pixel_x = 255;
				}

				ip.putPixelValue(x,y,pixel_x);

			}
		}

	}
}