package q3_1.classifier;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {


	private List<ClassTypes> classData = new ArrayList<ClassTypes>();
	private int total = 0;

	public Evaluation(){
		for(int i = 0 ; i < 10 ; i ++ ){
			classData.add(new ClassTypes(i));
		}
	}

	public void addClassification(double expected, double actual){


		if(actual == expected){
			classData.get((int)expected).correct++;
		}
		else{
			classData.get((int)expected).incorrect++;
		}


	}

	public void outputResults() {


		for(ClassTypes ct : classData){
			ct.outputResults();
		}

	}

	private class ClassTypes{

		public int correct = 0;
		public int incorrect = 0;
		public int classNumber = -1;

		public ClassTypes(int ClassNumber){
			classNumber = ClassNumber;
		}

		public void outputResults() {

			System.out.println("Class Number: " + classNumber);
			System.out.println("Correct: " + (correct));
			System.out.println("Incorrect: " + (incorrect));
			System.out.println("Percentage: " + ((correct) + 0.0)/((incorrect) + (correct)) + "\n");

		}
	}

}
