package q3_1.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import q3_1.data.ImageData;

public class Reader {

	public static int featureSize;
	private List<ImageData> imageData = new ArrayList<ImageData>();

	private List<ImageData> trainingSet = new ArrayList<ImageData>();
	private List<ImageData> testSet = new ArrayList<ImageData>();

	String dir;

	public Reader(String dir) {
		this.dir = dir;
	}

	/**
	 *
	 * @param sixMorph
	 */
	public void readAll(boolean sixMorph){

		File[] files;

		if(!sixMorph){
			files = new File(dir + "/3.1/mfeat-digits/")
			.listFiles();
			featureSize = 649;
		} else{
			files = new File[]{new File(dir + "/3.1/mfeat-digits/mfeat-mor")};
			featureSize = 6;
		}

		//Read the file in
		for(int i = 0 ; i < files.length ; i ++){

			Scanner sc = null;

			try {
				sc = new Scanner(files[i]);
				String nextLine = "";
				int lineNumber = 0;
				ImageData usedData;

				while(sc.hasNextLine()){

					if(i == 0){
						imageData.add(new ImageData(featureSize,lineNumber/200));
					}

					nextLine=sc.nextLine();
					String[] split = nextLine.split("\\s+");

					usedData = imageData.get(lineNumber);

					for(int j = 0 ; j < split.length ; j ++){
						if(split[j].equals("")){

						}
						else {
							usedData.setFeatureValue(Double.valueOf(split[j]));
						}
					}

					lineNumber ++;

				}

			} catch (FileNotFoundException e) {
				throw new Error(e);
			}
			finally{
				if(sc != null){
					sc.close();
				}
			}

		}
	}

	/**
	 * Split the data evenly
	 * 100 per class for test and training
	 * 1000 instances in training and test
	 */
	public void splitData(){

		int imageDataSize = imageData.size();

		List<Integer> items = new ArrayList<Integer>();


		for(int i = 0 ; i < 200 ; i ++ ){
			items.add(i);
		}


		for(int j = 0 ; j < 10 ; j ++){

			int offset = j * 200;

			Collections.shuffle(items);

			for(int i =  0 ; i < 100 ; i ++){
				testSet.add(imageData.get(offset + items.get(i)));
			}

			for(int i = 100 ; i < 200 ; i ++){
				trainingSet.add(imageData.get(offset + items.get(i)));
			}


		}
	}

	public List<ImageData> getTraining() {
		return trainingSet;
	}

	public List<ImageData> getTest() {
		return testSet;
	}


}
