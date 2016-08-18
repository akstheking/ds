package graphs;

import java.util.Arrays;

public class DjikstraShortestPath {

	int[][] graph;

	public DjikstraShortestPath(int[][] graph) {
		this.graph = graph;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 0, 10, 0, 2, 0, 0 }, { 0, 0, 0, 14, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		DjikstraShortestPath shortestPath = new DjikstraShortestPath(graph);
		shortestPath.findShortestPath(graph, 0);
	}

	public void findShortestPath(int[][] graph, int source) {
		int V = graph.length;
		boolean[] visited = new boolean[V];
		int[] sptSet = new int[V];
		for (int i = 0; i < V; i++) {
			sptSet[i] = Integer.MAX_VALUE;
		}
		sptSet[source] = 0;
		// visited[source] = true;
		findShortestUtil(graph, sptSet, visited);
	}

	public void findShortestUtil(int[][] graph, int sptSet[], boolean visited[]) {
		int s = findMinimumNonVisited(sptSet, visited);

		System.out.println(s);
		System.out.println(Arrays.toString(sptSet));
		System.out.println(Arrays.toString(visited));
		if (s == -1) {
			printArrays(sptSet);
			return;
		}
		visited[s] = true;
		for (int i = 0; i < graph[s].length; i++) {
			if (graph[s][i] != 0 && !visited[i] && (sptSet[i] > sptSet[s] + graph[s][i])) {
				sptSet[i] = sptSet[s] + graph[s][i];
			}
		}

		findShortestUtil(graph, sptSet, visited);
	}

	public int findMinimumNonVisited(int sptSet[], boolean[] visited) {
		int min = -1;
		int minIndex = -1;
		for (int i = 0; i < sptSet.length; i++) {
			if (!visited[i]) {
				if (min == -1) {
					min = sptSet[i];
					minIndex = i;
				} else if (sptSet[i] < min) {
					min = sptSet[i];
					minIndex = i;
				}
			}
		}
		return minIndex;
	}

	public void printArrays(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(i + " \t " + a[i]);
		}
	}

	public void printArrays(boolean a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(i + " \t " + a[i]);
		}
	}

}
