package q1_2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import q2_1.Q2_1;
import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.process.ImageProcessor;

public class Q1_2a {

	private int matrixSize = 5;

	public static void main(String args[]){

		Q1_2a q2 = new Q1_2a();

		ImagePlus image = new ImagePlus("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/1.2/ckt-board-saltpep.tif");
		//ImagePlus image = new ImagePlus("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/1.1/Test.jpg");

		ImageProcessor ip = image.getProcessor();

		q2.meanFilter(ip);

		final ImageWindow window = new ImageWindow(image);

		window.pack();

		window.addWindowStateListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				window.dispose();
			}
		}
				);


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

						pixel_x += copy.getPixelValue(x + filterX, y + filterY) * ((1+0.0)/(matrixSize*matrixSize));
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
