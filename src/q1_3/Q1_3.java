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


	public Q1_3(String dir){

		ImagePlus image = new ImagePlus(dir  + "1.3/blurry-moon.tif");

		ImageProcessor ip = image.getProcessor();

		run(ip);

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
        ImageProcessor dup = ip.duplicate();
        for (int x=1; x < w-2; x++) {
            for (int y=1; y < h-2; y++) {

                int val = (mask[0][0] * dup.getPixel(x-1,y-1)) + (mask[0][1] * dup.getPixel(x,y-1)) + (mask[0][2] * dup.getPixel(x+1,y-1)) +
                    (mask[1][0] * dup.getPixel(x-1,y))   + (mask[1][1] * dup.getPixel(x,y))   + (mask[1][2] * dup.getPixel(x+1,y)) +
                    (mask[2][0] * dup.getPixel(x-1,y+1)) + (mask[2][1] * dup.getPixel(x,y+1)) + (mask[2][2] * dup.getPixel(x+1,y+1));


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