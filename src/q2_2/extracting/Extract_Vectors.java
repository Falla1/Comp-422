package q2_2.extracting;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.process.ImageProcessor;

import java.io.File;

import q2_2.extracting.IO.Storage;
import q2_2.extracting.feature.Centered_Average;
import q2_2.extracting.feature.Feature;
import q2_2.extracting.feature.Histogram;
import q2_2.extracting.feature.Left_Eye;
import q2_2.extracting.feature.Centered_Standard_Dev;
import q2_2.extracting.feature.Left_Eye_Minus_Cheek;
import q2_2.extracting.feature.Mouth;
import q2_2.extracting.feature.Nose;
import q2_2.extracting.feature.Parameter_Outline_Edge;
import q2_2.extracting.feature.Right_Eye;
import q2_2.extracting.feature.Right_Eye_Minus_Cheek;

public class Extract_Vectors {


	public static final int featureSize = 10;
	private Feature[] features = new Feature[featureSize];
	private String type;

	public Extract_Vectors(String type){
		this.type = type;
		createFeatureArray();
		extract();
	}

	private void save(double[][] featuresOfImagesFaces, double[][] featuresOfImagesNonFaces) {
		Storage storage = new Storage("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/2.2/mit-cbcl-faces-balanced/TabDataSet/" + type + ".csv");

		storage.saveData(featuresOfImagesFaces,featuresOfImagesNonFaces);

	}

	private void extract(){
		File[] face = new File("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/2.2/mit-cbcl-faces-balanced/" + type + "/face")
		.listFiles();

		double[][] featuresOfImagesFaces = new double[face.length][featureSize];

		for(int i = 0 ; i <  face.length ; i ++){

			if(!face[i].isDirectory()){
				ImagePlus image = new ImagePlus(face[i].getAbsolutePath());

				Manipulation.threshold(image.getChannelProcessor());

				new ImageWindow(image);

				break;

				//featuresOfImagesFaces[i] = extractFeatures(image);
			}
		}

		File[] non_face = new File("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/2.2/mit-cbcl-faces-balanced/" + type + "/non-face")
		.listFiles();

		double[][] featuresOfImagesNonFaces = new double[non_face.length][featureSize];

		for(int i = 0 ; i <  non_face.length ; i ++){

			if(!non_face[i].isDirectory()){
				ImagePlus image = new ImagePlus(non_face[i].getAbsolutePath());

				featuresOfImagesNonFaces[i] = extractFeatures(image);
			}
		}


		save(featuresOfImagesFaces,featuresOfImagesNonFaces);
	}

	private double[] extractFeatures(ImagePlus image){

		double[] featureValues = new double[featureSize];

		ImageProcessor ip = image.getProcessor();


		for(int i = 0 ; i < features.length ; i ++){

			featureValues[i] = features[i].valueOf(ip);

		}

		return featureValues;
	}

	private void createFeatureArray() {
		features[0] = new Nose();
		features[1] = new Left_Eye();
		features[2] = new Right_Eye();
		features[3] = new Mouth();
		features[4] = new Parameter_Outline_Edge();
		features[5] = new Histogram();
		features[6] = new Centered_Average();
		features[7] = new Centered_Standard_Dev();
		features[8] = new Left_Eye_Minus_Cheek();
		features[9] = new Right_Eye_Minus_Cheek();
	}


}
