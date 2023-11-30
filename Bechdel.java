import java.util.*;
import java.io.*;

public class Bechdel{

	private Scanner reader;
	Map<Integer, ArrayList<Movie>> map;

	public Bechdel(){
		reader = new Scanner(System.in);
		map = new TreeMap<Integer, ArrayList<Movie>>();
		load("bechdel.txt");
	}

	public void load(String fileName){

		long startTime = System.currentTimeMillis();
		String line = "";

		try{
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			input.readLine();
			while((line=input.readLine())!= null){

				String a[] = line.split(",");
				int rating = Integer.parseInt(a[a.length-2]);
				int year = Integer.parseInt(a[a.length-1]);
				String title = "";

				Movie m;
				if (a.length > 6){//this is thre case when commas in title throw off split
					title = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
					m = new Movie(title,rating,year);

				} else{
					m = new Movie(a[1],rating,year);
				}

				if (!map.containsKey(year))
					map.put(year, new ArrayList<Movie>());
				map.get(year).add(m);

			}// end while

		} catch (IOException io) {
			System.err.println("File Error");
		}
		System.out.println("Load complete in "+(System.currentTimeMillis()-startTime)/1000.0+" seconds.  Ready to go.");

 	}

 	public void printData() {
		//System.out.println(map);
		for (Map.Entry<Integer, ArrayList<Movie>> entry: map.entrySet()) {

			ArrayList<Movie> arr = entry.getValue();
			for (int i = 0; i<arr.size(); i++)
				System.out.println(arr.get(i));

		}//*/
	}

 	public void printYear() {

		System.out.print("Input Year: ");
		int year = reader.nextInt();
		double score = 0;

		ArrayList<Movie> arr = map.get(year);
		for (int i = 0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
			score+=arr.get(i).rating;
		}

		score = score/arr.size();
		System.out.println("\n" + year + "'s Rating: " + (score));

		if (score >= 3)
			System.out.println(year + " was an AMAZING year for the movie industry!");
		else if (score >= 2)
			System.out.println(year + " was a good year for the movie industry!");
		else if (score >= 1)
			System.out.println(year + " was a bad year for the movie industry.");
		else if (score >= 0)
			System.out.println(year + " was a REALLY BAD year for the movie industry :(");

	}

	public double calculateYear(int year) {
		double score = 0;
		ArrayList<Movie> arr = map.get(year);
		for (int i = 0; i<arr.size(); i++) {
			score+=arr.get(i).rating;
		}

		score = score/arr.size();
		return score;
	}

	public void compareYears() { //compares last 10 years to another decade
		System.out.print("\nWhat decade of movies would you like to compare to the movies of the last decade?  ");
		int year = reader.nextInt();
		double score = 0, newScore = 0;
		int countOld = 0, countNew = 0;

		for (int i = 0; i<10; i++) {
			if (map.containsKey(year+i)) {
				score+= calculateYear(year+i);
				countOld++;
			}
			if (map.containsKey(2011+i)) {
				newScore+= calculateYear(2011+i);
				countNew++;
			}
		}

		score/=countOld;
		newScore/=countNew;

		System.out.println("From " + year + " to " + (year+10) + ", this decade had an average Bechdel score of " + score + " while the past decade (2011 - 2021) had a score of " + newScore);
		if (Math.abs(score - newScore) <= 03)
			System.out.println("Because the two decades have almost equal scores, both years performed relatively simliarly to each other.");
		else if (score > newScore)
			System.out.println("The years " + year + " to " + (year+10) + " scored better, this decade was ahead of their time, compared to the past decade (2011 - 2021)");
		else
			System.out.println("As expected, the movies from the 2011-2021 decade performed better than the movies of the " + year + " to " + (year+10) + " decade.");

	}

	public void compareAllData() {
		int year = 1870, largestYear = 0;
		double score = 0, newScore = 0;
		int countOld, countNew = 0;

		for (int i = 0; i<10; i++) {
			if (map.containsKey(2011+i)) {
				newScore+= calculateYear(2011+i);
				countNew++;
			}
		}

		newScore/=countNew;
		double largestScore = newScore;

		while (year <= 2005) {
			countOld = 0;
			score = 0;
			for (int i = 0; i<10; i++) {
				if (map.containsKey(year+i)) {
					score+= calculateYear(year+i);
					countOld++;
				}
			}

			score/=countOld;

			if (score > largestScore) {
				largestYear = year;
				largestScore = score;
			}
			year+=10;

		}

		if (largestScore != newScore)
			System.out.println("\nOut of all the movies since 1870, the movies from years " + largestYear + " to " + (largestYear+10) + " scored the highest when compared to the most recent decade (2010 - 2020). " + largestYear + "-" + (largestYear+10) + " had a whopping score of " + largestScore + " compared to the 2010s having a score of " + newScore);
		else
			System.out.println("\nNo decade had a greater score than the 2010-2020 decade");
	}

	private class Movie{
		//,title[1]rating[4],year[5]
		String title;
		int rating;
  		int year;

		public Movie(String t, int r, int y){
			title = t;
			rating = r;
      		year = y;
		}

		public String toString(){
			return title+" ["+year+"] ("+rating+")";
		}
	}

	public static void main(String[]args){
		Bechdel b = new Bechdel();
		//b.printData();
		b.printYear();
		//b.compareYears();
		b.compareAllData();


	}

}