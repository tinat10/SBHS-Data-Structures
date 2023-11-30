public class MoreThanMax{

	public static void main(String[] args) {
	    int max = Integer.MAX_VALUE;
	    int min = Integer.MIN_VALUE;


	    System.out.println("\nMin\t\tMax");
	    System.out.println(min+"\t "+max);

	    System.out.println("\nMin-1\t\tMax+1");
	    System.out.println((min-1)+"\t"+(max+1)+"\n");

	    System.out.println(safeAdd(min,-10));
		System.out.println(safeAdd(max,10));
		System.out.println(safeAdd(1000000,9999));
   		System.out.println(safeAdd(max,min));

 	}

 	public static int safeAdd(int a, int b){

	  long sum = a + b;

	  if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE)
	  	throw new ArithmeticException();
	  else
	  	return a + b;

	  //return 0; placeholder so code compiles

	}



}