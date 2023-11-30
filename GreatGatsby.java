import java.util.*;
import java.io.*;

public class GreatGatsby {

	public static void main(String[]args) {

		String book = "";

		//String way

		long stringStart = System.currentTimeMillis();

		try {
			File file = new File("GatsbyDeconstructed.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String text;

			while ((text = reader.readLine()) != null) {
				book += text;
			}

		} catch (Exception e) { e.printStackTrace(); }

		System.out.println("Execution time: " + (System.currentTimeMillis()-stringStart) + " ms");

		// StringBuilder way

		StringBuilder sb = new StringBuilder("");
		String SBbook = "";
		long sbStart = System.currentTimeMillis();

		try {
			File file = new File("GatsbyDeconstructed.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String text, output = "";

			while ((text = reader.readLine()) != null) {
				sb.append(text);
			}


		} catch (Exception e) { e.printStackTrace(); }

		SBbook = sb.toString();

		System.out.println("Execution time: " + (System.currentTimeMillis()-sbStart) + " ms");


	}

	/*
	try {
				File name = new File("CityDistances.txt");
				BufferedReader input = new BufferedReader(new FileReader(name));
				String text, output = "";

				while ((text=input.readLine()) != null) {
					output+=text + "\n";
				}

				split = output.split("\n");
			} catch (Exception e) { e.printStackTrace(); }

	*/

}