package q3_1.classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import q2_2.extracting.Extract_Vectors;
import q3_1.data.ImageData;
import q3_1.reader.Reader;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class Instancemaker {

	private File tempFile;

	public Instances getInstances(List<ImageData> data){


		CSVLoader loader = new CSVLoader();
		Instances instances;

		try{
			tempFile = new File("tmp");

			PrintWriter pw = null;

			try {
				pw = new PrintWriter(tempFile);
			} catch (FileNotFoundException e) {
				throw new Error(e);
			}

			for(int i = 0 ; i < Reader.featureSize ; i ++){
				pw.print(i + ",");
			}

			pw.println("class");

			for(int i = 0 ; i < data.size() ; i ++){

				List<Double> features = data.get(i).getFeatures();
				for(int j = 0 ; j < features.size() ; j ++){
					pw.print(features.get(j) + ",");
				}

				pw.println(data.get(i).getClassType());
				pw.flush();
			}

			loader.setSource(new File("tmp"));
			instances = loader.getDataSet();
			instances.setClass(instances.attribute("class"));


			return instances;

		} catch (IOException e) {
			throw new Error(e);
		}
		finally {
			if(tempFile != null){
				tempFile.delete();
				tempFile = null;
			}
		}

	}

}
