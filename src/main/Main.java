package main;

import q1_1.Q1_1;
import q1_2.Q1_2a;
import q1_2.Q1_2b;
import q1_3.Q1_3;
import q2_1.Q2_1;

public class Main {


	public static void main(String args[]){

		if(args.length != 2 && args.length != 3){
			System.out.println("Please read the README file");
			return;
		}

		if(args[0].equals("Q1_1")){
			new Q1_1(args[1]);
		}
		else if(args[0].equals("Q1_2a")){
			new Q1_2a(args[1]);
		}
		else if(args[0].equals("Q1_2b")){
			new Q1_2b(args[1]);
		}
		else if(args[0].equals("Q1_3")){
			new Q1_3(args[1]);
		}
		else if(args[0].equals("Q2_1")){
			new Q2_1(args[1]);
		}
		else if(args[0].equals("Q2_2")){
			if(args.length != 3){
				System.out.println("Please read the README file");
				return;
			}
			if(args[2].equals("Extract")){
				new q2_2.extracting.Main(args[1]);
			}
			else{
				new q2_2.naive.Main(args[1]);
			}
		}
		else if(args[0].equals("Q3_1")){
			if(args.length != 3){
				System.out.println("Please read the README file");
				return;
			}
			new q3_1.main.Main(args[2],args[1]);
		}

	}
}
