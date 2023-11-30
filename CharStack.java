import java.util.EmptyStackException;

public class CharStack {

	private String str;

	public CharStack() {
		str = "";
	}

	public void push(char c) {
		str+=c;
	}

	public char peek() {
		if (empty())
			throw new EmptyStackException();

		return str.charAt(str.length()-1);
	}

	public char pop() {
		if (empty())
			throw new EmptyStackException();

		char last = str.charAt(str.length()-1);
		str = str.substring(0, str.length()-1);
		return last;
	}

	public boolean empty(){
		if (str.length()==0)
			return true;
		return false;
	}

	public static void main(String[]args) {

		CharStack cs = new CharStack();
	   	//System.out.println(cs.peek());
		cs.push('!');
		cs.push('A');
		cs.push('L');
		cs.push('O');
		cs.push('H');
		System.out.println(cs.peek()+"I!");
		while (!cs.empty())
			System.out.print(cs.pop());
		System.out.println();

	}
}
