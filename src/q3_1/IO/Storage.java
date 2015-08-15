package q3_1.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import q2_2.extracting.Extract_Vectors;
import q3_1.data.ImageData;
import q3_1.reader.Reader;

public class Storage {


	private File file;

	public Storage (String filename){
		file = new File(filename);
	}

	public void saveData(List<ImageData> training, List<ImageData> test){

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			throw new Error(e);
		}

		for(int i = 0 ; i < Reader.featureSize ; i ++){
			pw.print(i + ",");
		}

		pw.println("class");

		


	}

}
