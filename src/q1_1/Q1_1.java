package q1_1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import q1_2.Q1_2b;
import q2_1.Q2_1;
import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.process.*;


public class Q1_1 {


    private int sobel_x[][] = {{-1,0,1},
                       {-2,0,2},
                       {-1,0,1}};

    private int sobel_y[][] = {{-1,-2,-1},
                       {0,0,0},
                       {1,2,1}};


	public static void main(String args[]){

		Q1_1 q1 = new Q1_1();

		ImagePlus image = new ImagePlus("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/1.1/test-pattern.tif");
		//ImagePlus image = new ImagePlus("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/1.1/Test.jpg");

		ImageProcessor ip = image.getProcessor();

		q1.run(ip);

		final ImageWindow window = new ImageWindow(image);

		window.pack();

		window.addWindowStateListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				window.dispose();
			}
		}
				);
	}


    public void run(ImageProcessor ip) {


        int w = ip.getWidth();
        int h = ip.getHeight();
        ImageProcessor copy = ip.duplicate();
        for (int x=1; x < w-2; x++) {
            for (int y=1; y < h-2; y++) {

                int pixel_x;
                int pixel_y;

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