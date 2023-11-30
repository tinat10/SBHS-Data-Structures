import java.util.*;

public class HashSetAssignment {
	public static void main(String[]args) {

		ArrayList<HashSet> nums = new ArrayList<HashSet>();
		int size = (int)(Math.random()*8)+3;
		for (int i = 0; i < size; i++) {

			HashSet<Integer> hashSet = new HashSet<Integer>();
			while (hashSet.size() !=10) {
				hashSet.add((int)(Math.random()*30)+1);
				//hashSet.add((int)(Math.random()*15)+1);
			}

			nums.add(hashSet);
		}

		int [] indices = new int[2];
		int rando = -1;
		for (int i = 0; i<2; i++) {

			rando = (int)(Math.random()*size);

			if (i==1) {
				while (rando==indices[0])
					rando = (int)(Math.random()*size);
			}

			indices[i] = rando;
			System.out.println("Set " + rando + ": ");

			Iterator<Integer> iterator = nums.get(rando).iterator();
			HashSet<Integer> h = nums.get(rando);
			for (Integer j: h)
				System.out.print(j + " ");
			System.out.println("\n");
		}



		HashSet<Integer> inter = Intersection(nums.get(indices[0]), nums.get(indices[1]));
		Iterator<Integer> iterator = inter.iterator();

		System.out.println("Intersection of Sets:");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.println("\n");


		HashSet<Integer> union = Union(nums.get(indices[0]), nums.get(indices[1]));
		iterator = union.iterator();

		System.out.println("Union of Sets:");
		while (iterator.hasNext())
			System.out.print(iterator.next() + " ");
		System.out.println("\n");


	}

	public static HashSet<Integer> Intersection (HashSet<Integer> one, HashSet<Integer> two) {

		HashSet<Integer> h = new HashSet<Integer>();
		Iterator<Integer> it = one.iterator();

		while (it.hasNext()) {
			int num = (int)it.next();
			if (two.contains(num)) {
				h.add(num);
			}
		}

		return h;

	}

	public static HashSet<Integer> Union (HashSet<Integer> one, HashSet<Integer> two) {

		HashSet<Integer> h = Intersection(one, two);
		HashSet<Integer> finalH = new HashSet<Integer>();

		Iterator<Integer> it = one.iterator();
		while (it.hasNext()) {
			finalH.add(it.next());
		}

		it = two.iterator();
		while (it.hasNext()) {
			finalH.add(it.next());
		}

		it = h.iterator();
		while (it.hasNext()) {
			finalH.remove(it.next());
		}

		return finalH;

	}

		/*
		Fill an ArrayList with a random number (between 3 and 8) of HashSets that each hold exactly 10 random numbers between 1 and 30. All of the sets must have sizes equal to 10.  Print each set.
		Make a method that will receive two sets and return a set of the intersection of the sets. The intersection will consist only of the common values contained within all sets.
		  - Method calls must eventually build the complete intersection of all of the sets.
		Make a method that will receive two sets and return a set of the union of the sets. The union will consist only of the unique values contained within all sets.Method calls must eventually build the complete union of all of the sets.
		Use the methods created in step 2 & 3 to create and print the intersection and union sets for all of the sets in the ArrayList
	*/
}