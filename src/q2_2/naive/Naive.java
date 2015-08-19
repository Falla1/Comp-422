package q2_2.naive;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.CSVLoader;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

public class Naive {

	Classifier bayes;
	Instances training;

	/**
	 * 0 = Face
	 * 1 = Non-Face
	 */
	public Naive(){
		setup();
		loadTrainingSet();
		testSet();
	}

	private void testSet() {

		CSVLoader loader = new CSVLoader();
		Instances test;

		Evaluation eva;

		try {
			loader.setSource(new File("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/2.2/mit-cbcl-faces-balanced/TabDataSet/test.csv"));
			test = loader.getDataSet();
			test.setClass(test.attribute("class"));

			eva = new Evaluation(training);

			eva.evaluateModel(bayes, test);

			String strSummary = eva.toSummaryString();
			System.out.println(strSummary);

			double[][] matrix = eva.confusionMatrix();

			try {
				System.out.println(eva.toClassDetailsString());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("\t\tFace\tNon-Face");
			for(int i = 0 ; i < matrix.length ; i ++){
				if(i == 0)
					System.out.print("Face \t\t");
				else
					System.out.print("Non-Face\t");
				for(int j = 0 ; j < matrix[i].length ; j ++){
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}

			ThresholdCurve tc = new ThresholdCurve();
			int classIndex = 0;
			Instances result = tc.getCurve(eva.predictions(), classIndex);

			// plot curve
			ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
			vmc.setROCString("(Area under ROC = " +
					Utils.doubleToString(tc.getROCArea(result), 4) + ")");
			vmc.setName(result.relationName());
			PlotData2D tempd = new PlotData2D(result);
			tempd.setPlotName(result.relationName());
			tempd.addInstanceNumberAttribute();
			// specify which points are connected
			boolean[] cp = new boolean[result.numInstances()];
			for (int n = 1; n < cp.length; n++)
				cp[n] = true;
			try {
				tempd.setConnectPoints(cp);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// add plot
			try {
				vmc.addPlot(tempd);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// display curve
			String plotName = vmc.getName();
			final javax.swing.JFrame jf =
					new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
			jf.setSize(500,400);
			jf.getContentPane().setLayout(new BorderLayout());
			jf.getContentPane().add(vmc, BorderLayout.CENTER);
			jf.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					jf.dispose();
				}
			});
			jf.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e);
		}


	}

	private void setup() {
		bayes = new NaiveBayes();
	}

	private void loadTrainingSet(){
		CSVLoader loader = new CSVLoader();

		try {
			loader.setSource(new File("/u/students/shawmarc/Desktop/2015/Comp422/A1/project1-images/2.2/mit-cbcl-faces-balanced/TabDataSet/train.csv"));
			training = loader.getDataSet();
			training.setClass(training.attribute("class"));
			bayes.buildClassifier(training);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e);
		}

	}

}
