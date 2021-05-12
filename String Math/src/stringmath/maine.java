package stringmath;

import java.util.Scanner;

public class maine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner r = new Scanner(System.in);
		String firstNum = "";
		//String secondNum = "";
		while(firstNum.toString() != "999"){
			
			System.out.print("Input first num: ");
			firstNum = r.nextLine();

			
			//System.out.print("Input second num: ");
			//secondNum = r.nextLine();
			long startTime = System.currentTimeMillis();
			
			//System.out.println("Addition: " + StringMath.add(firstNum, secondNum));
			
			//System.out.println("Subtraction: " + StringMath.subtract(firstNum, secondNum));
			
			//System.out.println("Multiplication: " + StringMath.multiply(firstNum, secondNum));
			
			//System.out.println("Division: " + StringMath.divide(firstNum, secondNum));
			
			System.out.println("Answer: " + StringMath.solveExpression(firstNum));
			
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println("Total time: " + totalTime + " milliseconds.");
			
			//System.out.println(isLessThan(new StringBuilder(firstNum), new StringBuilder(secondNum)) ? "FirstNum is less than SecondNum" : "SecondNum is less than or equal to FirstNum");
			
			
		}
		
		r.close();

	}

}
