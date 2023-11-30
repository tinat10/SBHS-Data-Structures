import java.io.*;
import java.util.*;
import java.util.ArrayList;

//used State class with this
public class Apportionment {

	public static void main(String[]args) {

		String[] allInfo;
		ArrayList<State> statesInfo = new ArrayList<State>();
		State greatest = new State(0);
		State least = new State(Integer.MAX_VALUE);

		File name = new File("CensusApportionment.txt");  // File name must match that on computer
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

			allInfo = output.split("\n");
			statesInfo = createStates(allInfo);
			statesInfo = alphabetize(statesInfo);

			for (int i = 0; i<statesInfo.size(); i++)
				System.out.println(statesInfo.get(i));

			for (int i = 0; i<statesInfo.size(); i++) {
				if (greatest.getRepPerDistr() < statesInfo.get(i).getRepPerDistr())
					greatest = statesInfo.get(i);

				if (least.getRepPerDistr() > statesInfo.get(i).getRepPerDistr())
					least = statesInfo.get(i);
			}

			System.out.println("\nA congressman from " + greatest.getName() + " represents " + greatest.getRepPerDistr() + " people, while a congressman from " + least.getName() + " only represents " + least.getRepPerDistr() + " people!");
//*/

		} catch (IOException io) {
			System.err.println("Error reading file => "+io);
			System.out.println("ERRROR HERE");
		}


	}

	public static ArrayList<State> alphabetize(ArrayList<State> states) {
		for (int i = 0; i < states.size(); i++) {
			for (int j = i + 1; j < states.size(); j++) {

				if (states.get(i).getName().compareTo(states.get(j).getName()) > 0) {

					State temp = new State(0);
					temp.setState(states.get(i));
					states.get(i).setState(states.get(j));
					states.get(j).setState(temp);
				}
			}
        }

        return states;
	}

	public static ArrayList<State> createStates (String[] allInfo) {
		String[] splitInfo;
		ArrayList<State> temp = new ArrayList<State>();

		for (int i = 1; i<allInfo.length; i++) {
			splitInfo = allInfo[i].split(" ");
			try {
				temp.add(new State(splitInfo[0], splitInfo[1], Integer.parseInt(splitInfo[2]), Integer.parseInt(splitInfo[3]))); //double word states are mixed up
			} catch (Exception e) {
				temp.add(new State(splitInfo[0] + " " + splitInfo[1], splitInfo[2], Integer.parseInt(splitInfo[3]), Integer.parseInt(splitInfo[4]))); //double word states are mixed up
			}
		}

		return temp;
	}


}
