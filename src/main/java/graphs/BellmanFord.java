package graphs;

import graphs.Graph.Edge;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import util.Utility;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-
 * algorithm/
 * 
 * @author akhil
 *
 */
public class BellmanFord {

	public static Graph getG1() {
		Graph g = new Graph(5);
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);
		return g;
	}

	public static Graph getG2() {
		Graph g = new Graph(5);
		// g.addEdge(0, 1, -1);
		g.addEdge(0, 2, -4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(2, 3, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, 3);
		return g;
	}

	public static void main(String[] args) {
		int V = 5;
		Graph g = getG2();
		bellmanFord(g, 0);
	}

	public static void bellmanFord(Graph g, int src) {
		int[] dist = new int[g.getV()];
		int[] path = new int[g.getV()];
		for (int i = 1; i < g.getV(); i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		for (int j = 1; j < g.getV(); j++) {
			// Relaxing each edge V-1 times
			for (int i = 0; i < g.getV(); i++) {
				for (Edge edge : g.getAdjList().get(i)) {
					if (dist[i] != Integer.MAX_VALUE
							&& dist[i] + edge.getWeight() < dist[edge.getDest()]) {
						dist[edge.getDest()] = dist[i] + edge.getWeight();
						path[edge.getDest()] = i;
					}
				}
			}
		}

		for (int i = 0; i < g.getV(); i++) {
			for (Edge edge : g.getAdjList().get(i)) {
				if (dist[i] + edge.getWeight() < dist[edge.getDest()]) {
					System.out
							.println("There is a negative weight cyle in the graph.");
					return;
				}
			}
		}

		for (int i = 0; i < path.length; i++) {
			int j = i;
			Deque<Integer> pathTrace = new LinkedList<>();
			generatePath(path, i, pathTrace);
			System.out.println(i + " => " + pathTrace);
		}
		System.out.println();

		Utility.printArrays(dist);
	}

	public static void generatePath(int[] path, int i, Deque<Integer> pathTrace) {
		pathTrace.addFirst(i);
		if(i == 0) {
			return;
		}
		generatePath(path, path[i], pathTrace);
	}

}
