package q1_3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import q1_2.Q1_2b;
import q2_1.Q2_1;
import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.process.*;


public class Q1_3 {


    int mask[][] = {{0,-1,0},
                       {-1,5,-1},
                       {0,-1,0}};


	public static void main(String args[]){

		Q1_3 q1 = new Q1_3();

		ImagePlus image = new ImagePlus("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/1.3/blurry-moon.tif");
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

                int val = (mask[0][0] * copy.getPixel(x-1,y-1)) + (mask[0][1] * copy.getPixel(x,y-1)) + (mask[0][2] * copy.getPixel(x+1,y-1)) +
                    (mask[1][0] * copy.getPixel(x-1,y))   + (mask[1][1] * copy.getPixel(x,y))   + (mask[1][2] * copy.getPixel(x+1,y)) +
                    (mask[2][0] * copy.getPixel(x-1,y+1)) + (mask[2][1] * copy.getPixel(x,y+1)) + (mask[2][2] * copy.getPixel(x+1,y+1));


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