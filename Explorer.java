import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Explorer extends MazeElement
{

	private int direction;
	private int steps;
	public static final String[] IMG_NAMES = {"MazeDogNorth.png", "MazeDogEast.png", "MazeDogSouth.png","MazeDogWest.png"}; //add one for each direction
	private BufferedImage[] images;

	public Explorer(Location loc, int size)
	{
		super(loc, size, IMG_NAMES[0]);
		direction = 1; //faces east by default
		steps = 0;

		/* Load files into the BufferedImage array based on the file names in IMG_NAMES   */

		images = new BufferedImage[IMG_NAMES.length];

		for (int i = 0; i<IMG_NAMES.length; i++) {
			try {  // Loads images
				images[i] = ImageIO.read(new File(IMG_NAMES[i]));
			} catch (IOException e) {
				System.out.println("Image not loaded");
			}

		}

	}

	@Override
	public BufferedImage getImg()
	{
		return images[direction];
	}

	//Explorer ~ Explorer must have a move() method (which will be called in the keyPressed method in your runner) and store the current position as a Location.
	public void move(int key, char[][] maze) {

		switch (key) {
			case 39: { //right arrow, so rotate 90 degrees
				if (direction < 3)
					direction++;
				else
					direction = 0;
				break;
			}
			case 37: { //left arrow, so rotate -90 degrees
				if (direction > 0)
					direction--;
				else
					direction = 3;
				break;
			}
			case (38): { //up arrow, moving forwards

				int r = getLoc().getR();
				int c = getLoc().getC();

				switch (direction) {
					case 1: {
						if (c+1 < maze[r].length && maze[r][c+1] == ' ')
							getLoc().incC(1);  //check loca
						break;
					}
					case 2: {
						if (r+1 < maze.length && maze[r+1][c] == ' ')
							getLoc().incR(1);  //check loca
						break;
					}
					case 3: {
						if (c-1 > -1 && maze[r][c-1] == ' ')
							getLoc().incC(-1);  //check loca
						break;
					}
					case 0: {
						if (r-1 > -1 && maze[r-1][c] == ' ')
							getLoc().incR(-1);  //check loca
						break;
					}
				}
			}

		}

	}

	public void resetDir() {
		direction = 1;
	}

	/*public Explorer(Location loc, int size,String imgString)
	{
		this.loc=loc;
		this.size=size;
		try {
			images  = ImageIO.read(new File(imgString));
		} catch (IOException e) {
				System.out.println("Image not loaded");
		}
	}

	public boolean intersects(Explorer other){

		return this.loc.equals(other.loc);
	}

	@Override
	public void move(int key, char[][] maze) {} // DOES NOTHING BY DEFAULT COMPLETE FOR MOVING ELEMENTS

*/
}