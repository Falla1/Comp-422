package q3_1.main;

import q3_1.classifier.Classifier;
import q3_1.classifier.Instancemaker;
import q3_1.reader.Reader;
import weka.core.Instances;

public class Main {

	public Main(String type){

		Reader r = new Reader();
		r.readAll(Boolean.valueOf(type));
		r.splitData();

		Instancemaker im = new Instancemaker();

		Instances training = im.getInstances(r.getTraining());
		Instances test = im.getInstances(r.getTest());

		Classifier classifier = new Classifier();

		classifier.trainClassifier(training);

		classifier.testClassifier(test);

	}

	public static void main(String args[]){

		new Main(args[0]);

	}

}
