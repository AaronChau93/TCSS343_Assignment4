import java.awt.Point;
import java.util.Arrays;

/**
 * 
 * Authors: 
 * Tyler Brent
 * William Almond
 * Aaron Chau
 *
 * This program finds the maximum square that can be created in a field of stones
 * as outlined in the challenge question 4. All matrices and necessary numbers
 * are output to the console for proper visualization.
 * 
 * Example input: java challenge 8 4 1 2 2 6 4 5 7 3
 * 
 * The first number is the size of the field.
 * The second number is the number of stones in the field.
 * The rest of the numbers are the coordinates to each stone.
 */

public class Challenge {
	
	int[][] myField;
	
	public static void main(String[]args) {
		//it's like safeco or qwest but more mathy and stuff
		Challenge barretoField = new Challenge((Integer.parseInt(args[0])));
				
		//get the stones
		for (int i = 2; i < args.length; i+=2) {
			int rowPos = Integer.parseInt(args[i]);
			int colPos = Integer.parseInt(args[i+1]);
			barretoField.myField[rowPos][colPos] = 0; 
	    }
		
		//printing stuff to make it look cool
		System.out.println("Starting matrix: ");
		barretoField.displayField(barretoField.myField);
		barretoField.findMaxSquare();
	}
	
	/**
	 * Overloaded contructor.
	 * 
	 * @param n is the size of the given field.
	 */
	public Challenge(int n) {
		//create the field, n is the size of the matrix.
		myField = new int[n][n];
		
		for(int[] row : myField) {
			Arrays.fill(row, 1);
		}
	}
	
	/**
	 * 
	 * This method finds the maximum size square that can be created in a given
	 * field of stones. It will return the size and the position of the square.
	 * 
	 * @return the maximum square size.
	 */
	public int findMaxSquare() {
		//initialize necessary values.
		//max represents our maximum square size.
		int max = -1;
		int count = 0;
		Point point = new Point(-1, -1);
		int size = myField.length - 1;
		int[][] matrix = myField.clone();
		
		//loop through the entire matrix of size n x n.
		for(int i = size; i >= 0; i--) {
			for(int j = size; j >= 0; j--) {
				if(matrix[i][j] == 0) {
					//we're on a rock, do nothing.
				}
				
				//if we're on a edge set the count at the square to 1.
				//no further calculations needed.
				else if(j == size || i == size) {
					matrix[i][j] = 1;
					
				//else perform the calculations
				} else {
					
					//check to the right, underneath, and across from the current square.
					count = matrix[i][j+1];
					if(matrix[i+1][j] < count) {
						count = matrix[i+1][j];
					}
					
					if(matrix[i+1][j+1] < count) {
						count = matrix[i+1][j+1];
					}
					
					count += 1;
					myField[i][j] = count;
					
					//if the count of the square is greater than the current max
					//set our new max to the count. The count of each square represents the max
					//square that can be created when starting at that space.
					if(count > max) {
						max = count;
						point.setLocation(i, j);
					}	
				}
			}
		}
		System.out.println("Resulting matrix: ");
		displayField(matrix);
		System.out.println("Maximum square size: " + max);
		System.out.println("At position: (" + (int)point.getX() + " , " + (int)point.getY() + ")");
		return max;
	}
	
	/**
	 * Prints a given matrix to the console.
	 * 
	 * @param theMatrix the matrix to be printed.
	 */
	public void displayField(int[][] theMatrix) {
		for(int[] row : theMatrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("\n");
	}
}
