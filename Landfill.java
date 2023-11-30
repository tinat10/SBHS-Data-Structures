import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Landfill {
	public static void main(String[]args) {

		File name = new File("NJMunicipalities.csv");
		String text,output="";

		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

		} catch(Exception e) {}

		String [] municIndividual = output.split("\n");

		ArrayList<Stack<Municipality>> countys = new ArrayList<Stack<Municipality>>();

		for (int i = 1; i < municIndividual.length; i++) {
			//String [] chopTemp = municIndividual[i].split(",");
			Stack<Municipality> temp = new Stack<Municipality>();
			Municipality mTemp = new Municipality(municIndividual[i]);

			boolean check = false; //checks if county has been added to an existing Stack. if it still equals 0, it will create a new Stack and be added to countys
			for (int j = 0; j<countys.size(); j++) {
				if (mTemp.getCounty().equals(countys.get(j).peek().getCounty())) {
					countys.get(j).push(mTemp);
					check = true;
				}
			}
			if (!check) {
				temp.push(mTemp);
				countys.add(temp);
			}

		}

		int r = (int)(Math.random()*countys.size());
		System.out.println("Selected " + countys.get(r).peek().getCounty() + " County");
		int roll = -1;
		while (roll != 1 && !countys.get(r).empty()) {
			roll = (int)(Math.random()*6)+1;
			if (roll!=1) {
				System.out.println(countys.get(r).peek().getType() + " of " + countys.get(r).pop().getName() + " rolled " + roll + ". No Landfill!");
			}
		}

		if (roll==1)
			System.out.println(countys.get(r).peek().getType() + " of " + countys.get(r).pop().getName() + " rolled 1. They get the Landfill.");
		else
			System.out.println("Each municipality in the county escapes the die roll. The State legislature will send the garbage to Pennsylvania.");



	}
}