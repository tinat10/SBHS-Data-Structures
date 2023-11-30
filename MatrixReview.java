import java.io.*;
import java.util.*;

public class MatrixReview {
	public static void main(String []args) {

		String [] numbers, splitNum;
		int [][] matrix;
		String revised = "";

		File name = new File("MatrixInput.txt");  // File name must match that on computer
			try {
				BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
				String text,output="";

				while((text=input.readLine())!= null) { // Keep reading until end of file (null)
					output+=text +"\n";  // Put into big String for now, mostly you will break into parts
				}

				//System.out.println(output);

				numbers = output.split("\n"); // splits into 2 matrices (EACH LINE IS BY ITSELF NOW)

				for (int i = 0; i<numbers.length; i++) {
					splitNum = numbers[i].split("\t"); //splits into individual matrix

					ArrayList<int[][]> matrixArrays = new ArrayList<int[][]>();

					for (int k = 0; k<splitNum.length; k++) {

						matrixArrays.add(get2DArray(splitNum[k]));

						System.out.println("Matrix " + (k+1) + ":");
						printMatrix(matrixArrays.get(matrixArrays.size()-1));

						if (matrixArrays.size()>1) {
							if (checkAddSub(matrixArrays) == true) {
								System.out.println("Sum: ");
								printMatrix(addMatrices(matrixArrays));

								System.out.println("Difference: ");
								printMatrix(subMatrices(matrixArrays));
							}
							else
								System.out.println("Sum: \nSum is not possible.\n\nDifference:\nDifferent is not possible.\n");

							if (checkMult(matrixArrays) == true) {
								System.out.println("Product: " );
								printMatrix(multMatrices(matrixArrays));
							}
							else
								System.out.println("Product: \nProduct is not possible.\n");
						}

					}

				}//*/


			} catch (IOException io)  {
				System.err.println("Error reading file => "+io);
				System.out.println("ERRROR HERE");
			}

		}

		public static int [][] get2DArray(String nums) {

			nums = nums.substring(1,nums.length()-1);
			nums = nums.replace("},{", "!");
			nums = nums.substring(1,nums.length()-1);

			String [] split = nums.split("!"); //splits into rows

			int row = split.length;
			int col = 0;

			String [] single;
			ArrayList<Integer> numHolder = new ArrayList<Integer>();

			for (int i = 0; i<split.length; i++) {
				single = split[i].split(","); //splits into individual numbers
				col = single.length;

				for (int r = 0; r<single.length; r++) {
					numHolder.add(Integer.parseInt(single[r]));
				}
			}

			int [][] finalArray = new int [row][col];
			int index = 0;
			for (int i = 0; i<row; i++) {

				for (int j = 0; j<col; j++) {

					finalArray[i][j] = numHolder.get(index);
					index++;
				}
			}

			//System.out.println(nums);

			return finalArray;
		}

		public static int[][] multMatrices (ArrayList<int[][]> array) {

			int [][] prod = new int[array.get(0).length][array.get(1)[0].length];
			int commonDimen = array.get(0)[0].length;
			int dotProduct = 0;

			for (int x = 0; x < prod[0].length; x++) {

				int index2 = 0;

				for (int i = 0; i < commonDimen; i++) { //ex. repeats 3 times

					dotProduct = 0;

					for (int k = 0; k < commonDimen; k++) { // ex. repeats 3 times
						dotProduct+=(array.get(0)[i][k] * array.get(1)[k][i]);
					}

					prod[x][index2] = dotProduct;
					index2++;

				}
			}

			return prod;
		}

		public static int[][] subMatrices (ArrayList<int[][]> array) {

			int [][] diff = new int[array.get(0).length][array.get(0)[0].length];

			for (int i = 0; i < array.get(0).length; i++) {
				for (int k = 0; k < array.get(0)[0].length; k++) {
					diff[i][k] = array.get(0)[i][k] - array.get(1)[i][k];
				}
			}

			return diff;
		}

		public static int[][] addMatrices (ArrayList<int[][]> array) {

			int [][] sum = new int[array.get(0).length][array.get(0)[0].length];

			for (int i = 0; i < array.get(0).length; i++) {
				for (int k = 0; k < array.get(0)[0].length; k++) {
					sum[i][k] = array.get(0)[i][k] + array.get(1)[i][k];
				}
			}

			return sum;
		}

		public static boolean checkAddSub (ArrayList<int[][]> array) { //checks whether matrices are same size

			return (array.get(0).length == array.get(1).length && array.get(0)[0].length == array.get(1)[0].length);

		}

		public static boolean checkMult (ArrayList<int[][]> array) { //checks whether first matrix's columns are equal to 2nd matrix's rows

			return (array.get(0).length == array.get(1)[0].length || array.get(0)[0].length == array.get(1).length);

		}


		public static void printMatrix(int [][] matrix) {
			for (int m = 0; m<matrix.length; m++) {
				for (int x = 0; x < matrix[m].length; x++) {
					System.out.print(matrix[m][x] + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}


}