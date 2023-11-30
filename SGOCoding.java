import java.util.*;

public class SGOCoding {
	public static void main (String[]args) {

// 1
		double [] prices = {1.0, 2.5, 4.7, 9.6, 14.3};
		Stack<Double> stack = new Stack<Double>();

		for (int i = 0; i<prices.length; i++)
			stack.push(prices[i]);

		while (!stack.isEmpty())
			System.out.print(stack.pop() + "\t");

		System.out.println("\n");

// 2
		int [] nums = {0, 1, 2, 3, 4, 5, 6, 7};
		Queue<Integer> evenIndex = new LinkedList<Integer>();
		Queue<Integer> oddIndex = new LinkedList<Integer>();

		for (int i = 0; i<nums.length; i++) {
			if (i%2==0)
				evenIndex.add(nums[i]);
			else
				oddIndex.add(nums[i]);
		}

		while (!oddIndex.isEmpty() || !evenIndex.isEmpty()) {
			System.out.print(evenIndex.poll() * oddIndex.poll() + "\t");
		}

		System.out.println("\n");

// 3
		int [] numbers = {1, 34, 24, 6, 1, 51, 2, 62, 24, 73};
		Set<Integer> unique = new HashSet<Integer>();

		for (int i = 0; i<numbers.length; i++)
			unique.add(numbers[i]);

		System.out.println("Array Size: " + numbers.length + "\nSet Size: " + unique.size());

		System.out.print("\n");

// 4
		Character [] chars;
		Set<Character> ascii = new HashSet<Character>();

// 5
		HashMap<Character, Integer> vowelCount = new HashMap<Character, Integer>();
		vowelCount.put('a', 0);
		vowelCount.put('e', 0);
		vowelCount.put('i', 0);
		vowelCount.put('o', 0);
		vowelCount.put('u', 0);

		String [] strings = {"abercrombie", "rapunzel", "mothers", "abracadabra", "houston", "everything", "igloo"};

		for (int i = 0; i<strings.length; i++) {
			String word = strings[i];

			for (int j = 0; j<word.length(); j++) {

				char c = word.charAt(j);
				if (vowelCount.containsKey(c))
					vowelCount.put(c, vowelCount.get(c)+1);
			}
		}

		for (Map.Entry<Character, Integer> entry : vowelCount.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}


	}
}