import java.util.ArrayList;
class StringIntegers {
	//BIG INTEGERS!!
  public static void main(String[] args) {

	// See the attached Java file for a primer on the idea of dealing with integers that are too big to stored as int or even long primitives.
	// Try to create a scheme to add them one digit at a time [using Integer.parseInt(String s)] and store the result in a new String.

	//CTRL + M = FIND MATCHING {} [] ()


    // Here are the bdiggest int and long values
    System.out.println("Biggest int => " + Integer.MAX_VALUE);
    System.out.println("Biggest long => " + Long.MAX_VALUE);

    // What if you want to use bigger numbers?
    String num1 = "1000002000202000017885";
    String num2 = "1000002000202000017836";
    ArrayList<Integer> digits = new ArrayList<Integer>();
    System.out.println("String num1 => " + num1);
    System.out.println("String num2 => " + num2);

	int holder = 0;
    for (int i = num1.length()-1; i>-1; i--) {
		int first = Integer.parseInt(Character.toString(num1.charAt(i)));
		int second = Integer.parseInt(Character.toString(num2.charAt(i)));
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
	for (int i = digits.size()-1; i>-1; i--) {
		sum = sum + String.valueOf(digits.get(i));
	}

	System.out.println("SUM: " + sum);

    /* How could you add these number Strings?   Is it generalizable?
     Try to calculate the sum of these large numbers and print as a
     String of numbers (NOT a double with an 'E')

     You can use this to help you check:
     https://www.calculator.net/big-number-calculator.html  */


  }

}
