import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
/**
 * TCSS 343 Project
 * @author Aaron Chau
 * @author Will Almond
 * @author Tyler Brent
 * @version Spring 2016
 */
public class tcss343 {

	/**
	 * Main method gets reads the file and drives each method.
	 */
	public static void main(String[] args) {
		FileReader fileReader = null; 
		BufferedReader buffReader = null;
		String line = "";
		int[][] tradingPosts = null;
		try {
			// Get file to read
			fileReader = new FileReader(args[0]);
			buffReader = new BufferedReader(fileReader);
			
			// Get first line
			line = buffReader.readLine();
			// Find out how many post we have. 
			final int size = line.split("\t").length;
			tradingPosts = new int[size][size];
			
			// Add the cost values of each trading post
			for (int i = 0; i < size; i++) {
				String[] values = line.split("\t");
				for (int j = 0; j < size; j++) {
					tradingPosts[i][j] = values[j].equals("NA") ? 
							Integer.MAX_VALUE : Integer.parseInt(values[j]);
				}
				line = buffReader.readLine();
			} 
			
			// Brute force method
			brutePath(tradingPosts);
			
			// Divide and Conquer
			
			
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to find file.");
		} catch (IOException ex) {
			System.out.println("Error while reading file.");
		}
	}
	/**
	 * Bute force method of trading posts.
	 */
	public static void brutePath(int[][] tradingPosts) {
		// Number of trading posts. 
		int size = tradingPosts.length;
		
		// Array to store paths.
		int[] path = new int[size];
		
		// Initialize new cost array with the price of traveling from the 
		// first post to the nth post.
		int[] cost = new int[size];
		for (int i = 0; i < cost.length; i++) {
			cost[i] = tradingPosts[0][i];
			path[i] = 0;
		}
		
		// Traveling to the first post cost $0. You're already there.
		cost[0] = 0;
		// For each other post.
		for (int j = 1; j < size; j++) {
			// Get the price
			int[] row = tradingPosts[j];
			for (int i = j+1; i < size; i++) {
				// If the price at the previous post plus the price
				// at the current post is less than the current cost at 
				// the current post then...
				if (row[i] + cost[i-1] < cost[i]) {
					// Switch canoes
					cost[i] = row[i] + cost[i-1];
					// Track which post we switched to.
					path[i] = j;
				} // else stay on the same canoe.
			}
		} // O(n^2)

		printPath(path, cost);
	}
	
	public static void divideAndConquerPath(int[][] tradingPosts) {
		int[] cost = new int[tradingPosts.length];
		for (int i = 0; i < cost.length; i++){
			cost[i] = Integer.MAX_VALUE;
		}
		divideAndConquerPathHelper(tradingPosts, cost);
	}
	
	public static void divideAndConquerPathHelper(int[][] tradingPosts, int[] cost) {
		
	}
	
	public static void printPath(int[] path, int[] cost) {
		System.out.print("The cheapest path is to travel from posts ");
		System.out.print("1-");
		for (int i = 1; i < path.length; i++) {
			if (path[i-1] != path[i]) {
				System.out.print(i);
				System.out.print(", " + i + "-");
			}
		}
		System.out.println(path.length + " and it cost $" + cost[cost.length-1] + ".");
	}
	
	public static void printArray(int[] arr) {
		System.out.print("[" + arr[0]);
		for (int i = 1; i < arr.length; i++) {
			System.out.print(", " + arr[i]);
		}
		System.out.println("]");
	}

}
