public class AList {

	// AList is a simplified version of the ArrayList. It only stores String data.

	public static final int INITIAL_CAPACITY = 4;
	private int capacity; //makes array dynamic
	private int size; //grows array periodically instead of creating new array for every single newly added String
	private String[] list;

	public AList() {
		capacity = INITIAL_CAPACITY;
		list = new String[capacity];
		size = 0;
	}

	public void add(String s) {
		if (size >= capacity) {
			capacity*=1.5;
			String[] temp = new String[capacity];
			for (int i = 0; i<size; i++)
				temp[i] = list[i];

			list = temp;
		}
		list[size] = s;
		size++;
		// else handles capacity increase
	}

	public int size() {
		return size;
	}

	public String set(int index, String s) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		String saved = list[index];
		list[index] = s;
		return saved;
	}

	public boolean contains(String s) {
		for (int i = 0; i<size; i++) {
			if (list[i].equals(s))
				return true;
		}
		return false;
	}

	public String remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		for (int i = index; i<size-1; i++)
			list[i] = list[i+1];

		String saved = list[index];
		size--;
		return saved;
	}

	public String toString() {
		String str = "[";
		for (int i = 0; i < size-1; i++)
			str += list[i] + ", ";
		if (size > 0) //add last element to protect against empty case
			str += list[size-1];

		return str + "]";
	}

	public String get(int index) {

		if (index<0 || index >= size)
			throw new IndexOutOfBoundsException();
		return list[index];
	}

	//************************** UNIT TESTING **************************//
	public static void main(String[]args) {

		AList al = new AList();
		System.out.println(al);

		al.add("A");
		al.add("B");
		al.add("C");
		al.add("D");
		al.add("E");
		al.add("F");
		System.out.println(al);
		System.out.println(al.get(2));
		System.out.println(al.get(4));

		System.out.println(al.set(1,"T"));
		System.out.println(al);

		System.out.println(al.remove(2));
		System.out.println(al);

	}



}