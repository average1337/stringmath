package stringmath;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp = "";
		Scanner r = new Scanner(System.in);
		
		while(!exp.equals("000")){
		
			
			System.out.print("Enter an expression: ");
		
			exp = r.nextLine();
		
			System.out.println(StringMath.solveExpression(exp));
		
		}
		
		r.close();
		
		
	}

}
