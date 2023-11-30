import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class MazeProject extends JPanel implements KeyListener, ActionListener
{
	private JFrame frame;
	private int size = 50, width = 1500, height = 1000, steps = 0; //i increased size from 30 to 50 to zoom up
	private char[][] maze;
	private Timer t;
	private Location start;
	private MazeElement finish; // finish line
	private ArrayList<MazeElement> fire = new ArrayList<MazeElement>();
	private Explorer  explorer; // main character
	private EvilDog monster;
	private boolean isDead = false;

	public MazeProject(){
		//Maze variables
		setBoard("maze0.txt");
		//fire = new ArrayList<MazeElement>();
		frame=new JFrame("A-Mazing Program");
		frame.setSize(width,height);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		t = new Timer(500, this);  // will trigger actionPerformed every 500 ms
		t.start();
	}

	// All Graphics handled in this method.  Don't do calculations here
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Graphics2D g3 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		g2.setColor(Color.GRAY);
		for(int r=0;r<maze.length;r++){
			for(int c=0;c<maze[0].length;c++){

				if (maze[r][c]=='#')
					g2.fillRect(c*size+size,r*size+size,size,size); //Wall
					//g2.fillRect(c*size*size,r*size*size,size,size); //Wall
				else
					g2.drawRect(c*size+size,r*size+size,size,size);  //Open

				//Paint MazeElements

				Location here = new Location(r, c); //row and col as location
				if (here.equals(finish.getLoc())) {
					g2.drawImage(finish.getImg(), c*size+size,r*size+size,size,size,null, this);
					//handle end of game
				}

				if (here.equals(explorer.getLoc()))
					g2.drawImage(explorer.getImg(), c*size+size,r*size+size,size,size,null, this);

				if (here.equals(monster.getLoc()))
					g2.drawImage(monster.getImg(), c*size+size,r*size+size,size,size,null, this); //check if this is wrong

				for (int i = 0; i<fire.size(); i++) {
					if (here.equals(fire.get(i).getLoc()))
						g2.drawImage(fire.get(i).getImg(), c*size+size,r*size+size,size,size,null, this);
				}

			}
		}

		// Display at bottom of page
		int hor = size;
		int vert = maze.length*size+ 2*size;
		g2.setFont(new Font("Arial",Font.BOLD,20));
		g2.setColor(Color.PINK);
		g2.drawString("Steps: " + steps,hor,vert);

		g3.setFont(new Font("Arial",Font.BOLD,20));
		g3.setColor(Color.WHITE);
		if (explorer.getLoc().equals(finish.getLoc())) {
			g3.drawString("GAME COMPLETED!", hor,vert+50);
		}
		if (isDead) {
			// Displays words if dead
			g3.drawString("YOU HAVE DIED. CLICK ANY KEY TO REPLAY.", hor,vert+100);
		}
	}


	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());

		if (isDead) { //
			backToStart();
			repaint();
			isDead = false;
			System.out.println("handling restart exp loc ="+explorer.getLoc());
			return;
		}

		int tempRow = explorer.getLoc().getR();
		int tempCol = explorer.getLoc().getC();

		explorer.move(e.getKeyCode(), maze);

		for (int i = 0; i<fire.size(); i++) {
			if (explorer.getLoc().equals(monster.getLoc()) || explorer.getLoc().equals(fire.get(i).getLoc())) {
				isDead = true;
			}
		}

		if (tempRow != explorer.getLoc().getR() || tempCol != explorer.getLoc().getC())
			steps++; //increments number of steps

		repaint();
	}

	/*** empty methods needed for interfaces **/
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void actionPerformed(ActionEvent e) {
		//System.out.println("reached actionPerformed");
		if (!isDead)
			monster.move(maze);

		if (explorer.getLoc().equals(monster.getLoc()))
			isDead = true;

		repaint();
/*
		if (monster.intersects(explorer)) {
			System.out.println("Intersection with monster location: " + monster.getLoc());
			//delay(4000);
		}*/
	}

	public void setBoard(String fileName){

		String [] stringTemp;
		char [][] temp = new char[1][1];
		//replace this code with load from file
		//make sure all rows have same number of cols

		File name = new File("maze0.txt");  // File name must match that on computer
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

			stringTemp = output.split("\n");  //  i need to throw an exception

			for (int i = 1; i < stringTemp.length; i++) {
				if (stringTemp[i].length() != stringTemp[i-1].length())
					throw new IOException("**NOT ALL LINES THE SAME LENGTH**");
			}

			temp = new char[stringTemp.length][stringTemp[0].length()];
			//System.out.println("***********");
			for (int i = 0; i < stringTemp.length; i++) {
				for (int k = 0; k<stringTemp[i].length(); k++) {

					char symbol = stringTemp[i].charAt(k);

					if (symbol != ' ' && symbol != '#') {
						if (stringTemp[i].charAt(k) == 'F') {
							finish = new MazeElement(new Location(i,k), size, "mazeTbone.png");
						}
						else if (stringTemp[i].charAt(k) == 'E') {
							explorer = new Explorer(new Location(i,k), size);
							start = new Location(i,k);
						}
						else if (stringTemp[i].charAt(k) == 'M') {
							monster = new EvilDog(new Location(i,k), size);
						}
						else if (stringTemp[i].charAt(k) == 'I') {
							fire.add(new MazeElement(new Location(i,k), size, "MazeFire.png"));
							System.out.println("HERE HERHE");
						}


						temp[i][k] = ' ';
					}
					else {
						temp[i][k] = symbol;
						//temp[i][k] = stringTemp[i].charAt(k);
					}
				}
			}

		} catch (Exception e) { e.printStackTrace(); }

		maze = temp;
	}

	public void backToStart() {
		explorer.setLoc(new Location(start.getR(), start.getC()));
		explorer.resetDir();
	}

	public static void main(String[] args){
		MazeProject app = new MazeProject();
	}
}