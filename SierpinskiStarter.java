import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class SierpinskiStarter extends JPanel implements KeyListener
{
	JFrame frame;
	ArrayList<Point> points;  // Need to wite Point as an internal class
	int currX,currY;
	public SierpinskiStarter()
	{
		frame=new JFrame("SierpinskiStarter Triangle Simulator");
		frame.add(this);
		frame.setSize(1200,700);
		points=sierpinskiPointGenerator();  // generate framework for points
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(), frame.getHeight());

		for (Point p: points) {
			g.setColor(p.color);
			g.fillOval(p.x,p.y,2,2);
		}


		//Paint Background  -> g.fillRect(x,y,size,size)
		// Paint all points in ArrayList -> g.fillOval(x,y,size,size);

	}
	public ArrayList<Point> sierpinskiPointGenerator()
	{
		ArrayList<Point> points=new ArrayList<Point>();
		// Set initial points as corner of Triangle

		int x1 = 2;//frame.getWidth()/2;
		int y1 = 100;

		int x2 = 400;
		int y2 = frame.getHeight()-100;

		int x3 = 1000;//frame.getWidth()-100;
		int y3 = frame.getHeight()-100;

		// Create a polygon with the 3 points of Triangle

		points.add(new Point(x1, y1, Color.WHITE));
		points.add(new Point(x2, y2, Color.WHITE));
		points.add(new Point(x3, y3, Color.WHITE));

		int [] x = {x1, x2, x3};
		int [] y = {y1, y2, y3};
		Polygon boundary = new Polygon(x,y,3);

		do {
			currX = (int)(Math.random()*frame.getWidth());
			currY = (int)(Math.random()*frame.getHeight());
		} while (!boundary.contains(currX, currY));

		points.add(new Point(currX, currY, Color.WHITE));


		// Find and add a random point within the Triangle polygon

		return points;
	}

	public void addPoints(int num)
	{
		Color [] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE};

		for (int i = 0; i<num; i++) {
			Point corner = points.get((int)(Math.random()*3));
			int xNew = (corner.x+currX)/2;
			int yNew = (corner.y+currY)/2;
			int colorNum = (int)(Math.random()*colors.length);
			points.add(new Point(xNew, yNew, colors[colorNum]));
			currX = xNew;
			currY = yNew;

		}

	}

	public void keyPressed(KeyEvent e)
	{
		System.out.println("Key Pressed = "+e.getKeyCode());
		if(e.getKeyCode()==32)// 32 --> space bar
			addPoints(100);

		if(e.getKeyCode()==10)// 10 --> enter key
			addPoints(100);

		// You can use other keys to add multiple points for speed
		repaint();
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}

	public static void main(String[] args)
	{
		SierpinskiStarter app=new SierpinskiStarter();
	}

	public class Point
	{
		int x, y;
		Color color;
		// Write an internal point class with x-coord, y-coord and color

		public Point(int x, int y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
}