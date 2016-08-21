package hackerearth.walmart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestClass {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		Tree tree = new Tree(N);
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			tree.addEdge(s, e, n);
		}
		tree.build();
	}

	private static class Tree {

		int next[];
		int previous[];
		int level[];
		int n;
		int ticketCounter;
		int matrix[][];
		int weight[][];
		boolean entry[];

		public Tree(int n) {
			this.n = n;
			next = new int[n];
			previous = new int[n];
			level = new int[n];
			matrix = new int[n][n];
			weight = new int[n][n];
			entry = new boolean[n];
			for (int i = 0; i < n; i++) {
				entry[i] = true;
			}
		}

		public void addEdge(int s, int e, int w) {
			next[s] = e;
			weight[s][e] = w;
			level[s]++;
			previous[e] = s;
			entry[e] = false;
		}

		public void build() {

			int maxLevel = 0;
			for (int i = 0; i < n; i++) {
				if (level[i] > maxLevel) {
					maxLevel = level[i];
				}
				if (next[i] == 0) {
					ticketCounter = i;
				}
			}

			matrix = new int[maxLevel][n];

			for (int i = 0; i < n; i++) {
				matrix[level[i]][i] = weight[i][next[i]];
			}

			int minLength = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (entry[i]) {
					int length = findMinLength(i, matrix);
					if (length < minLength) {
						minLength = length;
					}
				}
			}

			System.out.println(minLength);

		}

		public int findMinLength(int i, int[][] matrix) {
			findMinLengthUtil(next[i], matrix);
			return matrix[0][ticketCounter];
		}

		public void findMinLengthUtil(int i, int[][] matrix) {
			int value = matrix[level[i]][i];
			for (int j : matrix[i]) {
				if (j != 0) {
					matrix[level[next[j]]][next[j]] += (matrix[level[j]][j] < value) ? matrix[level[j]][j]
							: value;
				}
			}
			findMinLengthUtil(next[i], matrix);
		}

	}

}
