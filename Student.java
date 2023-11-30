public class Student implements Comparable<Student>{

private String firstName, lastName, noun, oneThing;

	public Student(String first, String last, String word, String sentence) {
		firstName = first;
		lastName = last;
		noun = word;
		oneThing = sentence;
	}

	//COMPARABLE: 0 = SAME; x>0 = DIFFERENT. BUT AFTER; x<0 = DIFFERENT. BUT BEFORE/PRECEDING
	public int compareTo(Student other) {
		if (this.lastName.compareTo(other.lastName) == 0)
			return this.firstName.compareTo(other.firstName);
		return this.lastName.compareTo(other.lastName);
	}

	public String getFirst() {
		return firstName;
	}

	public String getLast() {
		return lastName;
	}

	public String getNoun() {
		return noun;
	}

	public String getThing() {
		return oneThing;
	}

	public String toString() {
		//return firstName + " " + lastName + " " + noun + " " + oneThing;
		return firstName + " " + lastName;
	}

	public String displayData() {
		return firstName + " " + lastName + "\nNoun: " + noun + "\nSentence: " + oneThing;
	}

}
