import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class TrieDisplay extends JPanel implements KeyListener
{
	private JFrame frame;
	private int size = 30, width = 1000, height = 600;
	private Trie trie;
	private String word;			// Word you are trying to spell printed in large font
	private char likelyChar;  		// Used for single most likely character
	private boolean wordsLoaded;  	// Use this to make sure words are alll loaded before you start typing


	public TrieDisplay(){

		frame=new JFrame("Trie Next");
		frame.setSize(width,height);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Default Settings
		word = "";
		likelyChar = ' ';  // Used for single most likely character
		wordsLoaded = false;

		trie = new Trie();
		try { //loading words into Trie
			BufferedReader input = new BufferedReader(new FileReader("trieWords.txt"));
			String text, output = "";

			while ((text=input.readLine()) != null) {
				output += text + "\n";
			}

			String [] words = output.split("\n");
			for (int i = 0; i<words.length; i++)
				trie.add(words[i]);

		} catch (Exception io) {
			io.printStackTrace();
			System.err.println("Error reading file => "+io);
		}

		wordsLoaded = true;   // Set flag to true indicating program is ready
		repaint();

	}

	// All Graphics handled in this method.  Don't do calculations here
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);								// Setup and Background
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		g2.setFont(new Font("Arial",Font.BOLD,60));				// Header
		g2.setColor(Color.WHITE);
		if (wordsLoaded)
			g2.drawString("Start Typing:",40,100);
		else
			g2.drawString("Loading... please wait",40,100);

		g2.setFont(new Font("Arial",Font.BOLD,100));			// Typed text:  White == valid partial word
		if (trie.contains(word))								//              Red == invalid
			g2.setColor(Color.GREEN);							//				Green == full word
		else
			if (likelyChar == '_')
				g2.setColor(Color.RED);
			else
				g2.setColor(Color.WHITE);
		g2.drawString(word,40,250);
		g2.setFont(new Font("Arial",Font.BOLD,24));

		g2.setColor(Color.WHITE);
		g2.drawString("MOST LIKELY LETTER: " + likelyChar, 40, 320);
		//g2.drawString();
		//  YOUR CODE HERE

		// Draw String below here for next most likely letter / letters
		// If there ae no possible next letters write something like "no further possibilities"

	}


	public void keyPressed(KeyEvent e){              // This handles key press
		int keyCode = e.getKeyCode();
		if (keyCode == 8 && word.length() > 0)  // Backspace -> remove last letetr
			word = word.substring(0,word.length()-1);
		if (keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z)  // alphabetic key
        	word += KeyEvent.getKeyText(keyCode);
        likelyChar = trie.mostLikelyNextChar(word);



        //System.out.println("keyCode =>"+keyCode+", word =>"+word+", likelyChar =>"+likelyChar);     // Uncomment to Debug
		repaint();
	}

	/*** empty methods needed for interfaces **/
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void actionPerformed(ActionEvent e) {}

	public static void main(String[] args){
		TrieDisplay app=new TrieDisplay();
	}
}