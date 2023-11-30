import java.util.EmptyStackException;
public class SuperList<E> {


	private ListNode<E> root, end;
	private int size;

	public SuperList() { //don't specify the type of ListNode
		root = end = null;
		size = 0;
	}

	public static void main (String[]args) { // MAIN METHOD HERE
		SuperList sl = new SuperList();
		for (int i = 0; i<5; i++) {
			sl.add(i);
		}
		System.out.println(sl + "\nSize: " + sl.getSize() + "\n");

		sl.add(1, "hi");
		sl.add(3, "ha");
		System.out.println(sl + "\nSize: " + sl.getSize() + "\n");

		sl.remove(2);
		System.out.println(sl + "\nRemoved: Index 2\nSize: " + sl.getSize() + "\n");

		System.out.println("Contains 4: " + sl.contains(4) + "\n");

		sl.clear();
		System.out.println("isEmpty: " + sl.isEmpty() + "\n");

		for (int i = 0; i<5; i++) {
			sl.add(i);
		}

		sl.push(sl.get(0));
		System.out.println(sl + "\nSize: " + sl.getSize() + "\n");

		sl.poll();
		System.out.println(sl + "\nSize: " + sl.getSize() + "\n");

	}

	public void push (E val){
		ListNode<E> ln = new ListNode<E>(val);

		ListNode temp = end;
		temp.setNext(ln);
		ln.setPrev(temp);
		end = ln;

		size++;
	}

	public E pop() { //RETURN VALUES
		if (size==0)
			throw new EmptyStackException();
		else {
			size--;
			ListNode realEnd = end;
			remove(size-1);
			return (E)realEnd.getVal();
		}
	}

	public E poll() { //RETURN VALUES
		if (size==0)
			return null;
		else {
			size--;
			ListNode temp = root;
			remove(0);
			return (E)temp.getVal();
		}
	}

	public E queuePeek() {
		if (size==0)
			return null;
		return root.getVal();
	}

	public E stackPeek() {
		if (size==0)
			throw new EmptyStackException();
		return end.getVal();
	}

	public E get(int index) {
		if (index>size-1 || index<0)
			throw new ArrayIndexOutOfBoundsException();
		else {
			ListNode<E> ln = root;
			for (int i = 0; i<index; i++)
				ln = root.getNext();

			return ln.getVal();
		}
	}

	public E set(int index, E val) {
		if (index>size-1 || index<0)
			throw new ArrayIndexOutOfBoundsException();
		ListNode<E> ln = root;
		for (int i = 0; i<index; i++)
			ln = root.getNext();

		E oldVal = ln.getVal();
		ln.setVal(val);
		return oldVal;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public void clear() {
		size=0;
		root = end = null;
	}

	public boolean contains(E val) {
		ListNode<E> newNode = root;
		for (int i = 0; i<=size+1; i++) {
			if (newNode.getVal().equals(val))
				return true;
			newNode = newNode.getNext();
		}
		return false;
	}

	public void add(E val) {
		ListNode<E> newNode = new ListNode<E>(val);
		if (size == 0) {
			root = newNode;
			end = newNode;
			size = 1;
		}
		else {
			ListNode<E> oldEnd = end;
			end = newNode;
			oldEnd.setNext(newNode);
			newNode.setPrev(oldEnd);
			size++;
		}
	}

	public void add(int index, E val) {
		if (index > 0 && index<=size) {
			ListNode<E> newNode = new ListNode<E>(val);
			if (index == 0) {
				newNode.setNext(root);
				root.setPrev(newNode);
				root = newNode;
				size++;
			}
			else if (index == size) {
				add(val);
			}
			/*else if (index < size) {
				newNode.setPrev(end);
				end.setNext(newNode);
				end = newNode;
				size++;
			}*/
			else {
				ListNode<E> pNode = root;
				for (int i = 0; i < index-1; i++) {
					pNode = pNode.getNext();
				}
				ListNode<E> nNode = pNode.getNext();
				pNode.setNext(newNode);
				newNode.setNext(nNode);
				newNode.setPrev(pNode);
				nNode.setPrev(newNode);
			}
		}
		else
			throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
	}

	public void remove(int index) {
		ListNode<E> pNode = root;
		for (int i = 0; i<index-1; i++) {
			pNode = pNode.getNext();
		}
		ListNode<E> nNode = pNode.getNext().getNext();
		pNode.setNext(nNode);
		nNode.setPrev(pNode);

		size--;
	}

	public int getSize() {
		return size;
	}

	public String toString() {
		String s = "";
		ListNode<E> newNode = root;
		for (int i = 0; i<size; i++) {
			if (i==0) {
				s+="[" + root.getVal();
				newNode = newNode.getNext();
			}
			else if (i==size-1)
				s+=(", " + end.getVal() + "]");
			else {
				s+=(", " + newNode.getVal());
				newNode = newNode.getNext();

			}
		}
		return s;//String.valueOf("[" + root.getVal() + " " + end.getVal() + "]");
	}

	// ListNode<E> is an inner class
	class ListNode<E> {
		private E val;
		private ListNode<E> previous;
		private ListNode<E> next;

		private ListNode(E val) {
			this.val = val;
			previous = next = null;

		}

		// GETTERS
		public E getVal() {
			return val;
		}
		public ListNode<E> getPrev() {
			return previous;
		}
		public ListNode<E> getNext() {
			return next;
		}

		// SETTERS
		public void setVal(E val) {
			this.val = val;
		}
		public void setPrev(ListNode<E> node) {
			previous = node;
		}
		public void setNext(ListNode<E> node) {
			next = node;
		}

		// BOOLEAN METHODS
		public boolean hasNext() {
			return (next != null);
		}
		public boolean hasPrev() {
			return (previous != null);
		}

	}



	/*
	ListNode embedded class which should:
		~ Have a class variable for value
		~ Be declared to function wiht generics (use type E)
		~ has a constructor that sets an initial generic element E
		~ getVal method
		~ setPrev & setNext methods as well their respective "get" accessor methods
		~ hasNext & hasPrev methods (returns booleans)
	*/

}