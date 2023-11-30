import java.io.*;
import java.util.*;

public class NYCBabies {

	private Scanner reader = new Scanner(System.in);
	private HashMap<String, ArrayList<BabyRecord>> map;

	public NYCBabies(){
		reader = new Scanner(System.in);
		map = new HashMap<String, ArrayList<BabyRecord>>();
		load("Popular_Baby_Names.csv");
		printName();
		printNameTotals();
	}

	public void load(String fileName){

		long startTime = System.currentTimeMillis();
		String line = "";

		try{
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			input.readLine();
			while((line=input.readLine())!= null){

				String [] data = line.split(",");
				String name = data[3].toUpperCase();
				BabyRecord bb = new BabyRecord(Integer.parseInt(data[0]), data[1], data[2], name, Integer.parseInt(data[4]), Integer.parseInt(data[5]));

				if (!map.containsKey(name))
					map.put(name, new ArrayList<BabyRecord>());
				map.get(name).add(bb);
			}

		}

		catch (IOException io)
		{
			System.err.println("File Error");
		}
		System.out.println("Load complete in "+(System.currentTimeMillis()-startTime)/1000.0+" seconds.  Ready to go.");

	 }

	 public void printNames() {

		 for (Map.Entry<String, ArrayList<BabyRecord>> set: map.entrySet()) {
			 ArrayList<BabyRecord> arr = set.getValue();
			 //System.out.println(set.getKey() + ": ");
			 for (int i = 0; i< arr.size(); i++) {
				 System.out.println(arr.get(i));
			 }
		 }
	 }

	 public void printName() {

		 System.out.print("Enter a name: ");
		 String name = reader.nextLine().toUpperCase();

		 ArrayList<BabyRecord> arr = map.get(name);
		 for (int i = 0; i< arr.size(); i++) {
			System.out.println(arr.get(i));
		 }

	 }

	 public void printNameTotals() {

		 int total = 0, mTotal = 0, fTotal = 0;
		 System.out.print("\nEnter a name: ");
		 String name = reader.nextLine().toUpperCase();

		 for (BabyRecord b: map.get(name)) {
			 total += b.count;
			 if (b.gender.equalsIgnoreCase("MALE"))
			 	mTotal += b.count;
			 if (b.gender.equalsIgnoreCase("FEMALE"))
			 	fTotal += b.count;
		 }

		 System.out.println(name + " Statistics: ");
		 System.out.println("Total # of Babies Named " + name + ": " + total);
		 System.out.println("# of Female Babies Named " + name + ": " + fTotal);
		 System.out.println("# of Male Babies Named " + name + ": " + mTotal);

	 }

	 private class BabyRecord {
		 // Year of Birth, Gender, Ethnicity, Child's First Name, Count, Rank
		 String gender, ethnicity, name;
		 int year, count, rank;

		 public BabyRecord(int year, String gender, String ethnicity, String name, int count, int rank) {
			 this.year = year;
			 this.gender = gender;
			 this.ethnicity = ethnicity;
			 this.name = name;
			 this.count = count;
			 this.rank = rank;
		 }

		 public String toString() {
			 return name +  ", " + year + ", " + count + ", " + rank + ", " + gender + ", " + ethnicity;
		 }
	 }

	  public static void main(String[]args){
		  NYCBabies nycb = new NYCBabies();

	  }



}