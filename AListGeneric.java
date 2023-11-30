public class AListGeneric<E> {

	// AList is a simplified version of the ArrayList. stores generic data

	public static final int INITIAL_CAPACITY = 4;
	private int capacity; //makes array dynamic
	private int size; //grows array periodically instead of creating new array for every single newly added String
	private Object[] list; //OBject is a superclass of ALL classes is Java

	public AListGeneric() {
		capacity = INITIAL_CAPACITY;
		list = new Object[capacity];
		size = 0;
	}

	public void append(AListGeneric<E> newList) {

		for (int i = 0; i<newList.getSize(); i++) {
			add(newList.get(i));
		}
	}

	public void add(E val) {
		if (size >= capacity) {
			capacity*=1.5;
			Object[] temp = new Object[capacity];
			for (int i = 0; i<size; i++)
				temp[i] = list[i];

			list = temp;
		}
		list[size] = val;
		size++;
		// else handles capacity increase
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (index<0 || index >= size)
			throw new IndexOutOfBoundsException();
		return (E)list[index];
	}


	public String toString() {
		String str = "[";
		for (int i = 0; i < size-1; i++)
			str += list[i] + ", ";
		if (size > 0) //add last element to protect against empty case
			str += list[size-1];

		return str + "]";
	}

	public int getSize() {
		return size;
	}
/*
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

	/************************** UNIT TESTING **************************/
	public static void main(String[]args) {

		@SuppressWarnings("unchecked")
		AListGeneric<String> al = new AListGeneric();
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

		//System.out.println(al.set(1,"T"));
		System.out.println(al);

		//System.out.println(al.remove(2));
		//System.out.println(al);

		@SuppressWarnings("unchecked")
		AListGeneric<Double> nums = new AListGeneric();
		nums.add(3.2);
		nums.add(4.7);
		nums.add(5.0);
		System.out.println("\n\n" + nums);
		System.out.println("nums sum: " + (nums.get(0) + nums.get(1) + nums.get(2)));

		AListGeneric<String> list1  = new AListGeneric<String>();
		list1.add("apple");
		list1.add("banana");

		AListGeneric<String> list2 = new AListGeneric<String>();
		list2.add("spinach");
		list2.add("kale");

		list1.append(list2);
		System.out.println(list1);

	}



}