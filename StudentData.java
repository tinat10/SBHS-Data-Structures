import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
public class StudentData {
	public StudentData() {

		Scanner reader = new Scanner(System.in);

		File name = new File("FormResponses.csv");  // File name must match that on computer
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}
			//System.out.println("FILE CONTENTS:\n"+output);

			ArrayList<Student> students = new ArrayList<Student>();
			String[] brokenUp = (output.split("\n"));
			String[] individual;

			for (int j = 1; j<brokenUp.length; j++) {

				//System.out.println(brokenUp[j]);

				individual = brokenUp[j].split(","); //saves entire array of split up data

				students.add(new Student(individual[1], individual[2], individual[3], individual[4]));
				// Student(String first, String last, String word, String sentence)

			}

			/*for (int k = 0; k<students.size(); k++)
				System.out.println(students.get(k));//*/

			System.out.print("Would you like to see a random student's data? (Y/N): " );
			// ALTERNATIVE: char resp = reader.nextLine().charAt(0);
			String resp = reader.nextLine();

			// ALTERNATIVE: if (resp.toLowerCase.equals("y"))
			if (resp.equalsIgnoreCase("y")) {
				int rand = (int)(Math.random()*students.size());
				Student chosen = students.get(rand);

				System.out.println("You are now viewing " + chosen.getFirst() + " " + chosen.getLast() + "'s data.\n");
				System.out.println("Full Name: " + chosen.getFirst() + " " + chosen.getLast());
				System.out.println(chosen.getFirst() + "'s Noun: " + students.get(rand).getNoun());
				System.out.println("One sentence to describe " + chosen.getFirst() + ": " + chosen.getThing() + " \n");
			}
			else if (resp.equalsIgnoreCase("n")) {}
			else { System.out.println("INPUT ERROR"); }

			System.out.print("Choose a data type to search (first name = FN, last name = LN, noun = N, sentence = SN): ");
			String dataType = reader.nextLine().toUpperCase();
			switch (dataType) {
				case "FN": {
					System.out.print("Input the person's first name to see their data: ");
					String nameResp = reader.nextLine();
					for (int i = 0; i<students.size(); i++) {
						if (nameResp.equalsIgnoreCase(students.get(i).getFirst()))
							System.out.println("\n" + students.get(i).displayData());
				}
				break;
				}

				case "LN": {
					System.out.print("Input the person's last name to see their data: ");
					String nameResp = reader.nextLine();
					for (int i = 0; i<students.size(); i++) {
						if (nameResp.equalsIgnoreCase(students.get(i).getLast()))
							System.out.println("\n" + students.get(i).displayData());
				}
				break;
				}


				case "N": {
					System.out.print("Input the person's noun to see their data: ");
					String nameResp = reader.nextLine();
					for (int i = 0; i<students.size(); i++) {
						if (nameResp.equalsIgnoreCase(students.get(i).getNoun()))
							System.out.println("\n" + students.get(i).displayData());
					}
					break;
				}

				case "SN": {
					System.out.print("Input the person's sentence to see their data: ");
					String nameResp = reader.nextLine();
					for (int i = 0; i<students.size(); i++) {
						if (nameResp.equalsIgnoreCase(students.get(i).getThing()))
							System.out.println("\n" + students.get(i).displayData());
					}
					break;
				}

				default: {
					System.out.println("INVALID DATA TYPE");
					break;
				}
			}

		}
		catch (IOException io) {
			System.err.println("Error reading file => "+io);
		}


		}

		public static void main(String[] args) {
			StudentData app = new StudentData();
		}


}
