package q2_2.extracting.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import q2_2.extracting.Extract_Vectors;
import q3_1.data.ImageData;

public class Storage {


	private File file;

	public Storage (String filename){
		file = new File(filename);
	}

	public void saveData(double[][] featuresOfImagesFaces, double[][] featuresOfImagesNonFaces){

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			throw new Error(e);
		}

		for(int i = 0 ; i < Extract_Vectors.featureSize ; i ++){
			pw.print(i + ",");
		}

		pw.println("class");

		for(int i = 0 ; i < featuresOfImagesFaces.length ; i ++){
			for(int j = 0 ; j < featuresOfImagesFaces[i].length ; j ++){
				pw.print(featuresOfImagesFaces[i][j] + ",");
			}
			pw.println("face");
			pw.flush();
		}

		for(int i = 0 ; i < featuresOfImagesNonFaces.length ; i ++){
			for(int j = 0 ; j < featuresOfImagesNonFaces[i].length ; j ++){
				pw.print(featuresOfImagesNonFaces[i][j] + ",");
			}
			pw.println("non-face");
			pw.flush();
		}



	}

}
