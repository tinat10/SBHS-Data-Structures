import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class EvilDog extends MazeElement
{

	public EvilDog(Location loc, int size)
	{
		super(loc, size, "MazeEvilDog4.png");
		//super(loc, size, "MazeFire.png");
	}

	//EvilDog ~ EvilDog must have a move() method (which will be called in the keyPressed method in your runner) and store the current position as a Location.
	public void move(char[][] maze) {
	//	public void move(char[][] maze) {

		Location prior = getLoc();
		int r = getLoc().getR();
		int c = getLoc().getC();

		boolean check = false;

		while (!check) {
			int direction = (int)(Math.random()*4); // 0 = left, 1 = right, 2 = up, 3 = down
			System.out.println("D" + direction);
			switch (direction) {
				case 0:
					if (c-1 > -1 && maze[r][c-1] == ' ') {
						getLoc().incC(-1);
						check = true;
					}
					break;

				case 1:
					if (c+1 < maze[r].length && maze[r][c+1] == ' ') {
						getLoc().incC(1);
						check = true;
					}
					break;


				case 2:
					if (r-1 > -1 && maze[r-1][c] == ' ') {
						getLoc().incR(-1);
						check = true;
					}
					break;


				case 3:
					if (r+1 < maze.length && maze[r+1][c] == ' ') {
						getLoc().incR(1);
						check = true;
					}
					break;


				default: {
					System.out.println("STUCK!");
					break;
				}
			}

		}

	}


}