package q3_1.data;

import java.util.ArrayList;
import java.util.List;

public class ImageData {

	private List<Double> features;
	private String classType;

	public ImageData(int featureSize, int lineNumber){
		features = new ArrayList<Double>();
		classType = swapIntToString(lineNumber);
	}

	public void setFeatureValue(double value){
		features.add(value);
	}

	public List<Double> getFeatures() {
		return features;
	}

	public String getClassType() {
		return String.valueOf(classType);
	}

	private String swapIntToString(int value){

		switch(value){
		case 0:
			return "zero";
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";

		}

		throw new Error("Did not find any value");

	}

}
