package dynamic;

import java.util.Arrays;

public class Knapsack01 {

	public static void main(String args[]) {
		int val[] = new int[] { 160, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int W = 50;
		int n = val.length;
		System.out.println(knapSack(W, wt, val, n));
	}

	public static int knapSack(int W, int[] wt, int[] val, int n) {
		int tmp[][] = new int[n + 1][W + 1];

		for (int j = 1; j <= W; j++) {
			for (int i = 1; i <= n; i++) {
				if(wt[i-1] > j) {
					tmp[i][j] = tmp[i-1][j];
				} else {
					tmp[i][j] = Math.max(val[i-1] + tmp[i-1][j-wt[i-1]], tmp[i-1][j]);
				}
			}
		}
		
		printArray(tmp, n+1, W+1);
		return tmp[n][W];
	}
	
	public static void printArray(int tmp[][], int rows, int columns) {
		for(int i = 0; i<rows; i++)
		{
		    for(int j = 0; j<columns; j++)
		    {
		        System.out.print(tmp[i][j] + "\t");
		    }
		    System.out.println();
		}

	}

}
