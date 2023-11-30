import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class IntegerReviewTasks {
	public static void main (String[]args) {

		//REVIEW TASK 1

		int greatest = -1;
		ArrayList<Integer> indices = new ArrayList<Integer>();

		File name = new File("lucas.txt");  // File name must match that on computer
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}
			System.out.println("FILE CONTENTS:\n"+output);

			String[] splitNumbers = (output.split("\n"));
			for (int i = 0; i<splitNumbers.length; i++) {
				indices.add(Integer.parseInt(splitNumbers[i])); // indices holds all the indices from the file
				if (greatest < indices.get(i))
					greatest = indices.get(i); //gets greatest index
			}

			//System.out.print(greatest);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//ArrayList<Integer> fibNum = new ArrayList<Integer>();
		ArrayList<String> fibString = new ArrayList<String>();
		//fibNum.add(2);
		//fibNum.add(1);
		fibString.add("2");
		fibString.add("1");
		for (int i = 2; i<greatest+1; i++) {

			String num1 = fibString.get(i-1);
			String num2 = fibString.get(i-2);
			ArrayList<Integer> digits = new ArrayList<Integer>();

			// if the string aren't equal in length
			while (num1.length() < num2.length()) {
				num1 = "0" + num1;
			}

			//System.out.println("LENGTH: " + num1.length() + " " + num2.length());

			int holder = 0;
			for (int j = num1.length()-1; j>-1; j--) {
				int first = Integer.parseInt(Character.toString(num1.charAt(j)));
				int second = Integer.parseInt(Character.toString(num2.charAt(j)));
				//System.out.println(first + " " + second + " ");
				int third = first + second + holder;
				//System.out.println(third);
				holder = 0;
				if (third > 9) {
					holder = 1;
					third = third%10;
				}
				digits.add(third);
			}
			for (int k = digits.size()-1; k>-1; k--)
			System.out.print("DIGITS: " + digits.get(k));



			String sum = "";
			for (int k = digits.size()-1; k>-1; k--)
				sum = sum + String.valueOf(digits.get(k));

				System.out.println("SUM: " + sum);

			fibString.add(sum);

		}

		for (int l = 0; l<indices.size(); l++) {
			System.out.println("[index " + indices.get(l) + "] = [" + fibString.get(indices.get(l)) + "]");
		}
//maybe do try/catch instead
			/*if (Long.valueOf(fibNum.get(i-2)) + Long.valueOf(fibNum.get(i-1)) > Long.valueOf(Integer.MAX_VALUE)) {
				String num1 = fibString.get(i-1);
				String num2 = fibString.get(i-2);
				ArrayList<Integer> digits = new ArrayList<Integer>();

				// if the string aren't equal in length
				while (num1.length() < num2.length())
					num1 = "0" + num1;

				System.out.println(num1.length() + " " + num2.length());

				int holder = 0;
				for (int j = num1.length()-1; j>-1; j--) {
					int first = Integer.parseInt(Character.toString(num1.charAt(j)));
					int second = Integer.parseInt(Character.toString(num2.charAt(j)));
					//System.out.println(first + " " + second + " ");
					int third = first + second + holder;
					//System.out.println(third);
					holder = 0;
					if (third > 9) {
						holder = 1;
						third = third%10;
					}
					digits.add(third);
				}

				String sum = "";
				for (int k = digits.size()-1; k>-1; k--)
					sum = sum + String.valueOf(digits.get(k));

				fibNum =

			}
			else {
				fibString.add(String.valueOf(fibNum.get(i-1) + fibNum.get(i-2)));
				fibNum.add(fibNum.get(i-1) + fibNum.get(i-2));
			}//*/
	}
}