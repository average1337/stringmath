package stringmath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringMath {
	
	private static int decimalAccuracy = 20;
	private static final char[] OPERATORS = {'*', '/', '+', '-'};
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String add(String firstNum, String secondNum){
		if(!checkValidity(firstNum) || !checkValidity(secondNum)) return "Invalid Numbers";
		StringBuilder stringOne = new StringBuilder(firstNum);
		StringBuilder stringTwo = new StringBuilder(secondNum);
		StringBuilder answer = new StringBuilder();
		
		if(stringOne.charAt(0) == '-'){
			if(stringTwo.charAt(0) == '-'){
				answer = decimalAdd(stringOne.replace(0, 1, ""), stringTwo.replace(0, 1, "")).insert(0, '-');
			}
			else{
				answer = decimalSub(stringTwo, stringOne.replace(0, 1, ""));
			}
		}
		
		else if(stringTwo.charAt(0) == '-'){
			answer = decimalSub(stringOne, stringTwo.replace(0, 1, ""));
		}
		
		else{
			answer = decimalAdd(stringOne, stringTwo);
		}
		
		return shaveAnswer(answer).toString();
		
	}
	
	public static String subtract(String firstNum, String secondNum){
		if(!checkValidity(firstNum) || !checkValidity(secondNum)) return "Invalid Numbers";
		StringBuilder stringOne = new StringBuilder(firstNum);
		StringBuilder stringTwo = new StringBuilder(secondNum);
		StringBuilder answer = new StringBuilder();
		
		if(stringOne.charAt(0) == '-'){
			if(stringTwo.charAt(0) == '-'){
				answer = decimalSub(stringTwo.replace(0, 1, ""), stringOne.replace(0, 1, ""));
			}
			else{
				answer = decimalAdd(stringOne.replace(0, 1, ""), stringTwo).insert(0, '-');
			}
		}
		
		else if(stringTwo.charAt(0) == '-'){
			answer = decimalAdd(stringOne, stringTwo.replace(0, 1, ""));
		}
		
		else{
			answer = decimalSub(stringOne, stringTwo);
		}
		
		return shaveAnswer(answer).toString();
	}
	
	public static String multiply(String firstNum, String secondNum){
		if(!checkValidity(firstNum) || !checkValidity(secondNum)) return "Invalid Numbers";
		StringBuilder stringOne = new StringBuilder(firstNum);
		StringBuilder stringTwo = new StringBuilder(secondNum);
		StringBuilder answer = new StringBuilder();
		
		if(stringOne.charAt(0) == '-'){
			if(stringTwo.charAt(0) == '-'){
				answer = decimalMultiply(stringOne.replace(0, 1, ""), stringTwo.replace(0, 1, ""));
			}
			else{
				answer = decimalMultiply(stringOne.replace(0, 1, ""), stringTwo).insert(0, '-');
			}
		}
		
		else if(stringTwo.charAt(0) == '-'){
			answer = decimalMultiply(stringOne, stringTwo.replace(0, 1, "")).insert(0, '-');
		}
		
		else{
			answer = decimalMultiply(stringOne, stringTwo);
		}
		
		return shaveAnswer(answer).toString();
	}
	
	public static String divide(String firstNum, String secondNum){
		if(!checkValidity(firstNum) || !checkValidity(secondNum)) return "Invalid Numbers";
		StringBuilder stringOne = new StringBuilder(firstNum);
		StringBuilder stringTwo = new StringBuilder(secondNum);
		StringBuilder answer = new StringBuilder();
		
		if(stringOne.charAt(0) == '-'){
			if(stringTwo.charAt(0) == '-'){
				answer = decimalDivide(stringOne.replace(0, 1, ""), stringTwo.replace(0, 1, ""));
			}
			else{
				answer = decimalDivide(stringOne.replace(0, 1, ""), stringTwo).insert(0, '-');
			}
		}
		
		else if(stringTwo.charAt(0) == '-'){
			answer = decimalDivide(stringOne, stringTwo.replace(0, 1, "")).insert(0, '-');
		}
		
		else{
			answer = decimalDivide(stringOne, stringTwo);
		}
		
		return shaveAnswer(answer).toString();
	}
	
	public static String mod(String firstNum, String secondNum) {
		if(!checkValidity(firstNum) || !checkValidity(secondNum)) return "Invalid Numbers";
		StringBuilder stringOne = new StringBuilder(firstNum);
		StringBuilder stringTwo = new StringBuilder(secondNum);
		StringBuilder answer = new StringBuilder();
		
		if(stringOne.charAt(0) == '-'){
			if(stringTwo.charAt(0) == '-'){
				answer = modTwoStrings(stringOne.replace(0, 1, ""), stringTwo.replace(0, 1, ""));
			}
			else{
				answer = modTwoStrings(stringOne.replace(0, 1, ""), stringTwo).insert(0, '-');
			}
		}
		
		else if(stringTwo.charAt(0) == '-'){
			answer = modTwoStrings(stringOne, stringTwo.replace(0, 1, "")).insert(0, '-');
		}
		
		else{
			answer = modTwoStrings(stringOne, stringTwo);
		}
		
		return shaveAnswer(answer).toString();
	}
	
	private static boolean checkValidity(String s) {
		return s.matches("[-]?[0-9]*[.]?[0-9]+") || s.matches("[-]?[0-9]+[.]?[0-9]*") ;
	}
	
	public static String baseConvert(String num, int base) {
		if(base > 36) return num;
		if(base <= 1) return num;
		if(num.charAt(0) == '-') return "-" + baseConvert(num.substring(1), base);
		if(!checkValidity(num) || num.indexOf('.') != -1) return num;
		
		StringBuilder answer = new StringBuilder("");
		while(!num.equals("0")) {
			answer.append(ALPHABET.charAt(Integer.parseInt(mod(num, String.valueOf(base)))));
			num = integerDivision(num, String.valueOf(base));
		}
		
		return answer.reverse().toString();
	}
	
	
	public static String convertToBaseTen(String s, int curBase) {
		StringBuilder power = new StringBuilder("1");
		String digit= "";
		String answer = "0";
		if(s.charAt(0) == '-') return "-" + convertToBaseTen(s.substring(1), curBase);
		for(int i = s.length() - 1; i >= 0; i--) {
			digit = String.valueOf(ALPHABET.indexOf(s.charAt(i)));
			answer = add(answer, multiply(digit, power.toString()));
			power = multiplyTwoStrings(power, new StringBuilder(String.valueOf(curBase)));
		}	
		
		return answer;
		
	}
	
	public static String integerDivision(String firstNum, String secondNum) {
		
		String answer = divide(firstNum,secondNum);
		if(answer.indexOf('.') != -1) {
			answer = answer.substring(0,  answer.indexOf('.'));
		}
		
		if(answer.equals("")) answer = "0";
		
		return answer;
		
	}
	
	
	
	
	private static StringBuilder shaveAnswer(StringBuilder answer){
		
		if(answer.charAt(0) == '-'){
			return shaveAnswer(answer.replace(0, 1, "")).insert(0, '-');
		}
		
		if(answer.charAt(answer.length() - 1) == '.'){
			answer.replace(answer.length() - 1, answer.length(), "");
		}
		
		int wholeNumLength = 0;
		int decimalLength = 0;
		int decimalLoc = answer.indexOf(".");
		
		if(!isDecimal(answer)){
			wholeNumLength = answer.length();
		}
		
		else{
			//decimalLoc = answer.indexOf(".");
			wholeNumLength = answer.substring(0, decimalLoc).length();
			if(decimalLoc < answer.length()){
				decimalLength = answer.substring(decimalLoc + 1,  answer.length()).length();
			}
		}
		
		if(decimalLength > decimalAccuracy) {//truncate after 20 decimals
			answer.replace(decimalLoc + decimalAccuracy + 1, answer.length() , "");
		}
		
		while(wholeNumLength > 1 && answer.charAt(0) == '0'){//0000000000005.5 -> 5.5
			if(answer.charAt(1) != '.'){
				answer.replace(0, 1, "");
				wholeNumLength--;
			}
		}
		
		while(decimalLength > 1 && answer.charAt(answer.length() - 1) == '0'){//5.5000000000 -> 5.5
			if(answer.charAt(answer.length() - 2) != '.'){
				answer.replace(answer.length() - 1, answer.length(), "");
				decimalLength--;
			}
		}
		
		if(answer.charAt(0) == '.') {//.123123 -> 0.123123
			answer.insert(0, '0');
		}
		
		if(answer.toString().equals("0.0")) {//0.0 -> 0
			answer = new StringBuilder("0");
		}
		
		
		
		return answer;
		
	}
	
	private static StringBuilder decimalAdd(StringBuilder firstNum, StringBuilder secondNum){
		
		if(!isDecimal(firstNum)) firstNum.append(".");
		if(!isDecimal(secondNum)) secondNum.append(".");
		
		int firstIndex = firstNum.indexOf(".");
		int secondIndex = secondNum.indexOf(".");
		
		StringBuilder temp1 = new StringBuilder(firstNum.substring(firstIndex + 1));
		StringBuilder temp2 = new StringBuilder(secondNum.substring(secondIndex + 1));
		
		while(temp1.length() < temp2.length()){
			temp1 = temp1.append('0');
		}
		
		while(temp2.length() < temp1.length()){
			temp2 = temp2.append('0');
		}
		firstNum = firstNum.replace(firstIndex, firstNum.length(), "").append(temp1);
		secondNum = secondNum.replace(secondIndex, secondNum.length(), "").append(temp2);
		
		int backIndex = firstNum.length() - firstIndex;
		StringBuilder answer = addTwoStrings(firstNum, secondNum);
		
		answer.insert(answer.length() - backIndex, '.');
		
		
		return 	shaveAnswer(answer);
	}
	
	private static StringBuilder decimalSub(StringBuilder firstNum, StringBuilder secondNum){
		
		if(!isDecimal(firstNum)) firstNum.append(".");
		if(!isDecimal(secondNum)) secondNum.append(".");
		
		int firstIndex = firstNum.indexOf(".");
		int secondIndex = secondNum.indexOf(".");
		
		StringBuilder temp1 = new StringBuilder(firstNum.substring(firstIndex + 1));
		StringBuilder temp2 = new StringBuilder(secondNum.substring(secondIndex + 1));
		
		while(temp1.length() < temp2.length()){
			temp1 = temp1.append('0');
		}
		
		while(temp2.length() < temp1.length()){
			temp2 = temp2.append('0');
		}
		firstNum = firstNum.replace(firstIndex, firstNum.length(), "").append(temp1);
		secondNum = secondNum.replace(secondIndex, secondNum.length(), "").append(temp2);
		
		int backIndex = firstNum.length() - firstIndex;
		StringBuilder answer = subTwoStrings(firstNum, secondNum);
		
		answer.insert(answer.length() - backIndex, '.');
		
	
		
		return 	shaveAnswer(answer);
	}
	
	private static StringBuilder decimalMultiply(StringBuilder firstNum, StringBuilder secondNum){
		
		if(!isDecimal(firstNum)) firstNum.append(".");
		if(!isDecimal(secondNum)) secondNum.append(".");
		
		int firstIndex = firstNum.indexOf(".");
		int secondIndex = secondNum.indexOf(".");
		
		StringBuilder temp1 = new StringBuilder(firstNum.substring(firstIndex + 1));
		StringBuilder temp2 = new StringBuilder(secondNum.substring(secondIndex + 1));
		
		
		while(temp1.length() < temp2.length()){
			temp1 = temp1.append('0');
		}
		
		while(temp2.length() < temp1.length()){
			temp2 = temp2.append('0');
		}
		
		int totalDecimal = (temp1.length() + temp2.length()) * -1;
		
		firstNum = firstNum.replace(firstIndex, firstNum.length(), "").append(temp1);
		secondNum = secondNum.replace(secondIndex, secondNum.length(), "").append(temp2);
		
		//int backIndex = firstNum.length() - firstIndex;
		StringBuilder answer = multiplyTwoStrings(firstNum, secondNum);
		
		//answer.insert(answer.length() - totalDecimal, '.');
		placeDecimal(answer, answer.length(), totalDecimal);
		//System.out.println(firstNum + " " + secondNum);
		
		
		return shaveAnswer(answer);
	}
	
private static StringBuilder decimalDivide(StringBuilder firstNum, StringBuilder secondNum){
		
		if(!isDecimal(firstNum)) firstNum.append(".");
		if(!isDecimal(secondNum)) secondNum.append(".");
		
		int firstIndex = firstNum.indexOf(".");
		int secondIndex = secondNum.indexOf(".");
		
		StringBuilder temp1 = new StringBuilder(firstNum.substring(firstIndex + 1));
		StringBuilder temp2 = new StringBuilder(secondNum.substring(secondIndex + 1));
		
		
		int totalDecimal = (temp1.length() * -1) + temp2.length();
		
		firstNum = firstNum.replace(firstIndex, firstNum.length(), "").append(temp1);
		secondNum = secondNum.replace(secondIndex, secondNum.length(), "").append(temp2);
		
		if(firstNum.charAt(0) == '.') {
			firstNum.insert(0, '0');
		}
		
		if(secondNum.charAt(0) == '.') {
			secondNum.insert(0, '0');
		}
		
		//int backIndex = firstNum.length() - firstIndex;
		StringBuilder answer = divideTwoStrings(firstNum, secondNum);
		
		placeDecimal(answer, answer.indexOf("."), totalDecimal);
		
		
		return shaveAnswer(answer);
	}

	
	private static StringBuilder addTwoStrings(StringBuilder firstNum, StringBuilder secondNum){
		
		while(firstNum.length() < secondNum.length()){
			firstNum = firstNum.insert(0, '0');
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum = secondNum.insert(0, '0');
		}
		
		int num1 = 0;
		int num2 = 0;
		int total = 0;
		int carry = 0;
		
		StringBuilder answer = new StringBuilder("");
		
		for(int i = firstNum.length() - 1; i >= 0; i--){
			num1 = (int)(firstNum.charAt(i)) - 48;
			num2 = (int)(secondNum.charAt(i)) - 48;
			
			total = num1 + num2 + carry;
			answer.append((char)((total % 10) + 48));
			if(total / 10 != 0) carry = total / 10;
			else carry = 0;
		}
		
		if(carry != 0) answer.append((char)(carry + 48));
		
		return answer.reverse();
		
	}
	
	private static StringBuilder subTwoStrings(StringBuilder firstNum, StringBuilder secondNum){
		
		if(isLessThan(firstNum, secondNum)){
			return subTwoStrings(secondNum, firstNum).insert(0, "-");
		}
		
		while(firstNum.length() < secondNum.length()){
			firstNum.insert(0, "0");
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum.insert(0, "0");
		}
		
		
		int num1 = 0;
		int num2 = 0;
		int value = 0;
		int carry = 0;
		StringBuilder answer = new StringBuilder("");
		
		for(int i = firstNum.length() - 1; i >= 0; i--){
			num1 = (int)firstNum.charAt(i) - 48;
			num2 = (int)secondNum.charAt(i) - 48;
			value = num1 - num2 - carry;
			carry = 0;
			if(value < 0){
				value += 10;
				carry += 1;
			}
			answer.append(value);
		}
		
		return answer.reverse();
	}
	
	private static StringBuilder multiplyTwoStrings(StringBuilder firstNum, StringBuilder secondNum){
		
		
		while(firstNum.length() < secondNum.length()){
			firstNum.insert(0, "0");
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum.insert(0, "0");
		}
		
		StringBuilder answer = new StringBuilder("0");
		StringBuilder temp = new StringBuilder("");
		int trailingZeros = 0;
		int carry = 0;
		int value = 0;
		int count = 0;
		
		for(int i = secondNum.length() - 1; i >= 0; i--){
			
			for(int n = firstNum.length() - 1; n >= 0; n--){
				int firstInt = (int)firstNum.charAt(n) - 48;
				int secondInt = (int)secondNum.charAt(i) - 48;
				value = (firstInt * secondInt) + carry;
				carry = 0;
				
				if(value >= 10){
					carry = value / 10;
					value %= 10;
				}
				temp.append(value);
			}
			
			if(carry != 0){
				temp.append(carry);
				carry = 0;
			}
			temp = temp.reverse();
			
			count = trailingZeros;
			while(count > 0){
				temp.append('0');
				count--;
			}
			
			trailingZeros++;
			answer = addTwoStrings(answer, temp);
			temp.delete(0, temp.length());
			
		}
		
		//System.out.println(answer);
		return shaveAnswer(answer);
	}
	
	private static StringBuilder divideTwoStrings(StringBuilder firstNum, StringBuilder secondNum){
		
		shaveAnswer(firstNum);
		shaveAnswer(secondNum);
		
		if(firstNum.length() < 10 && Double.parseDouble(firstNum.toString()) == 0){
			return new StringBuilder("0");
		}
		
		if(secondNum.length() < 10 && Double.parseDouble(secondNum.toString()) == 0){
			return new StringBuilder("Error: Cannot Divide by Zero");
		}
		
		int decimalOffset = 0;
		
		while(firstNum.length() < secondNum.length()){
			firstNum.insert(firstNum.length(), "0");
			decimalOffset--;
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum.insert(secondNum.length(), "0");
			decimalOffset++;
		}
		
		StringBuilder answer = new StringBuilder("");
		StringBuilder temp = new StringBuilder("0");
		int count = 0;
		int value = 0;
		boolean answered = false;
		
		//temp = subTwoStrings(firstNum, secondNum);
		
		while(!answered && count < (decimalAccuracy + Math.abs(decimalOffset) + 1)){
			while(temp.charAt(0) != '-'){
				temp = subTwoStrings(firstNum, secondNum);
				
				if(temp.charAt(0) != '-'){
					value++;
					firstNum = temp;
				}

				
				if(temp.length() < 5 && Double.parseDouble(temp.toString()) == 0){
					answered = true;
				}
			
				
			}	
			
			answer.append(value);
			firstNum.append("0");
			//if(count != 0) decimalOffset--;
			temp = new StringBuilder(firstNum);
			value = 0;
			count++;
		}
		
		//answer = answer.reverse();
		answer = placeDecimal(answer, 1, decimalOffset);
		
		return answer;
	}
	
	private static StringBuilder modTwoStrings(StringBuilder firstNum, StringBuilder secondNum) {
		shaveAnswer(firstNum);
		shaveAnswer(secondNum);
		
		if(firstNum.length() < 10 && Double.parseDouble(firstNum.toString()) == 0){
			return new StringBuilder("0");
		}
		
		if(secondNum.length() < 10 && Double.parseDouble(secondNum.toString()) == 0){
			return new StringBuilder("Error: Cannot Mod By 0");
		}
		
		/*while(firstNum.length() < secondNum.length()){
			firstNum = firstNum.insert(0, '0');
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum = secondNum.insert(0, '0');
		}*/
		
		StringBuilder answer = new StringBuilder("");
		StringBuilder q = new StringBuilder();
		
		if(isLessThan(firstNum, secondNum)) {
			return firstNum;
		}
		
		//a % b = a - b*trunc(a/b)
		q = new StringBuilder(integerDivision(firstNum.toString(), secondNum.toString()));
		//System.out.println(q);
		
		answer = decimalSub(firstNum, decimalMultiply(secondNum, q));
		
		
		return shaveAnswer(answer);
			
			
		}
		
	
	/**DecimalLoc must be >= 0 && <= num.length()*/
	private static StringBuilder placeDecimal(StringBuilder num, int decimalLoc, int offset){
		
		if(decimalLoc == -1 || decimalLoc > num.length()){
			decimalLoc = num.length();
		}
		
		if(decimalLoc < num.length() && num.charAt(decimalLoc) == '.'){
			num.replace(decimalLoc, decimalLoc + 1, "");
		}
		
		
		while(offset != 0){
			if(decimalLoc == num.length() && offset > 0){
				num.append(0);
				decimalLoc++;
				offset--;
			}
			
			else if(decimalLoc == 0 && offset < 0){
				num.insert(0, 0);
				offset++;
			}
			
			else if(offset < 0){
				decimalLoc--;
				offset++;
			}
			
			else if(offset > 0){
				decimalLoc++;
				offset--;
			}
		}
		
		return num.insert(decimalLoc, '.');
		
	}
	
	/**Not configured to work with decimals, need to line up decimal places*/
	private static boolean isLessThan(StringBuilder firstNum, StringBuilder secondNum){
		
		shaveAnswer(firstNum);
		shaveAnswer(secondNum);
		
		if(firstNum.charAt(0) == '-'){
			if(secondNum.charAt(0) == '-'){
				return isLessThan(secondNum.replace(0, 1, ""), firstNum.replace(0, 1, ""));
			}
			else {
				return true;
			}
		}
		
		if(secondNum.charAt(0) == '-'){
			return false;
		}
		
		/*if(firstNum.length() < secondNum.length()){
			return true;
		}
		
		if(secondNum.length() < firstNum.length()){
			return false;
		}*/
		
		while(firstNum.length() < secondNum.length()){
			firstNum.insert(0, "0");
		}
		
		while(secondNum.length() < firstNum.length()){
			secondNum.insert(0, "0");
		}
		
		
		
		int firstChar = 0;
		int secondChar = 0;
		
		for(int i = 0; i < firstNum.length(); i++){
			
			firstChar = (int)firstNum.charAt(i) - 48;
			secondChar = (int)secondNum.charAt(i) - 48;
			
			if(firstNum.charAt(i) == '.'){
				if(secondNum.charAt(i) != '.'){
					return true;
				}
			}
			
			if(secondNum.charAt(i) == '.'){
				if(firstNum.charAt(i) != '.'){
					return false;
				}
			}
			
			if(firstChar < secondChar){
				return true;
			}
		
			if(secondChar < firstChar){
				return false;
			}
		}
		
		return false;
		
		
	}
	
	//INPUT: A expression string, preferred with spaces surrounding operators
		//OUTPUT: A string that is the solution to the given expression
		public static String solveExpression(String exp) {
			System.out.println("Solving: " + exp);
			if(exp.matches("(.*)[a-zA-Z](.*)")) return exp;
			exp = formatExpression(exp);
			//Find the index of the last left parenthesis
			int leftParen = exp.lastIndexOf('(');
			
			//Find the closest right parenthesis
			int rightParen = exp.substring(leftParen + 1).indexOf(')') + leftParen + 1;
			
			while(leftParen < rightParen) {
			
				//System.out.println(exp);
			
				//If the parenthesis are valid, then solve inside of them recursively, the solve the rest of the function
				if(leftParen < rightParen){
					exp = exp.substring(0, leftParen) + solveExpression(exp.substring(leftParen + 1, rightParen)) + exp.substring(rightParen + 1);
				}
				
				//Find the index of the last left parenthesis
				leftParen = exp.lastIndexOf('(');
				
				//Find the closest right parenthesis
				rightParen = exp.substring(leftParen + 1).indexOf(')') + leftParen + 1;
			
			}
			
			//Split the string by the spaces in order to isolate the numbers and operators
			String[] expSplit = exp.split(" ");
			List<String> expression = new LinkedList<String>(Arrays.asList(expSplit));
			System.out.println(Arrays.toString(expSplit));
			
			
			//Weird for loop because we need to be able to find a '*' and '/' or a '+' and a '-'
			for(int operator = 0; operator <= OPERATORS.length - 2; operator += 2) {
				
				//get the 2 current operators
				char operator1 = OPERATORS[operator];
				char operator2 = OPERATORS[operator + 1];
				
				//iterate through the expression and find those operators
				for(int i = 0; i < expression.size(); i++) {
				
					//check for multiplication or addition
					String check = expression.get(i);
					if(check.charAt(0) == operator1 && check.length() == 1) {
					
						switch(expression.remove(i).charAt(0)) {
						
						//multiply or add the two strings and delete the old values from the list
						case '*': expression.add(i - 1, multiply(expression.remove(i - 1), expression.remove(i - 1)));
						break;
						
						case '+': expression.add(i - 1, add(expression.remove(i - 1), expression.remove(i - 1)));
						break;
						}
						
						//deiterate i to compensate for lost values in array
						i -= 2;
						
					}
					
					//check for subtraction or division
					else if(check.charAt(0) == operator2 && check.length() == 1) {
						
						//divide or subtract the two strings and delete the old values from the list
						switch(expression.remove(i).charAt(0)) {
						
						case '/': expression.add(i - 1, divide(expression.remove(i - 1), expression.remove(i - 1)));
						break;
						
						case '-': expression.add(i - 1, subtract(expression.remove(i - 1), expression.remove(i - 1)));
						break;
					
						}
						
						//deiterate i to compensate for lost values in array
						i -= 2;
					}
					
					
				}
				
			}
			
			//System.out.println("Answer: " + expression.get(0));
			return expression.get(0);
			
		}
		
		private static String round(String num) {
			int decimal = num.indexOf('.');
			if(decimal == -1) return num;
			
			String temp = num.substring(decimal + 1, num.length());
			if(temp.length() <= decimalAccuracy) return num;
			
			int digit = (int)(temp.charAt(decimalAccuracy));
			//String temp = num.substring(decimal + 1, num.length());
				
			if(digit >= 5) {
				digit = (int)(temp.charAt(decimalAccuracy - 1)) + 1;
			}
			
			temp = num.substring(decimal + 1, decimalAccuracy) + digit;
			
			return num.substring(0, decimal + 1) + temp.substring(0, decimalAccuracy);
			
		}
		
		private static String formatExpression(String exp){
			//iterate through each operator
			//System.out.println(exp);
			StringBuilder expression = new StringBuilder(exp);
			
			int index = 0;
			//int from = 0;
			for(int i = 0; i < OPERATORS.length; i++){
				index = expression.indexOf(String.valueOf(OPERATORS[i]));
					//System.out.println(exp);
					while(index != -1){
						
						if(index != 0 && expression.charAt(index - 1) != ' '){
							expression.insert(index, ' ');
							index++;
						}
					
						if(index != expression.length() - 1 && expression.charAt(index + 1) != ' '){
							expression.insert(index + 1, ' ');
						}
					
						index = expression.indexOf(String.valueOf(OPERATORS[i]), index + 1);
					}
				
			}
			
			for(int i = 0; i < expression.length(); i++) {
				
				if(expression.charAt(i) == '-') {
					
					if(i < 2) expression.replace(i + 1, i + 2, "");
					
					else if(isOperator(expression.charAt(i - 2))) {
						
						if(expression.charAt(i + 1) == ' ') {
							
							expression.replace(i + 1, i + 2, "");
							
						}
					}
					
					else if(expression.charAt(i - 2) == '(') {
						expression.replace(i - 1, i + 2, "-");
					}
					
				}
			}
			
			int leftParenCount = countCharacter('(', expression);
			int rightParenCount = countCharacter(')', expression);
			
			while(rightParenCount < leftParenCount){
				expression.append(")");
				rightParenCount++;
			}
			
			while(leftParenCount < rightParenCount){
				expression.insert(0,"(");
				leftParenCount++;
			}
			
			return expression.toString();
		}
		
		
		public static boolean isOperator(char c) {
			for(int i = 0; i < OPERATORS.length; i++) {
				if(c == OPERATORS[i]) {
					return true;
				}
			}
			
			return false;
		}
		
		public static int countCharacter(char c, String num){
			int count = 0;
			for(int i = 0; i < num.length(); i++){
				if(num.charAt(i) == c){
					count++;
				}
			}
			
			return count;
		}
		
		private static int countCharacter(char c, StringBuilder num){
			int count = 0;
			for(int i = 0; i < num.length(); i++){
				if(num.charAt(i) == c){
					count++;
				}
			}
			
			return count;
		}
	
	
	private static boolean isDecimal(StringBuilder num){
		return num.indexOf(".") >= 0;
	}
	

}
