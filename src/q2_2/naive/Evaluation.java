package q2_2.naive;

public class Evaluation {


	//Incorrect
	private int falsePositive = 0;
	private int falseNegative = 0;

	//How many were correct
	private int truePositive = 0;
	private int trueNegative = 0;

	public void addClassification(double expected, double actual){

		if(actual == 0){
			if(actual == expected){
				trueNegative ++;
			}
			else{
				falsePositive ++;
			}
		}
		else if (actual == 1){
			if(actual == expected){
				truePositive ++;
			}
			else{
				falseNegative ++;
			}
		}
		else{
			throw new Error("Should not get here");
		}

	}

	public void outputResults() {

		System.out.println("Correct: " + (truePositive+trueNegative));
		System.out.println("Incorrect: " + (falsePositive+falseNegative));
		System.out.println("Percentage: " + ((truePositive+trueNegative) + 0.0)/((truePositive+trueNegative) + (falsePositive+falseNegative)));

		System.out.println("True Positive Fraction: " + (truePositive+0.0)/(trueNegative+truePositive));
		System.out.println("False Positive Fraction: " + (falsePositive+0.0)/(trueNegative+truePositive));
	}

}
