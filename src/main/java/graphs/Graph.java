package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

	int V;// number of vertices
	Map<Integer, List<Edge>> adjList;// adjacency list

	public static class Edge implements Comparable<Edge>{
		int dest;
		int weight;

		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		public int getDest() {
			return dest;
		}

		public void setDest(int dest) {
			this.dest = dest;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [dest=" + dest + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return weight-o.getWeight();
		}
		
	}

	public Graph(int v) {
		V = v;
		adjList = new HashMap<>();
		for (int i = 0; i < v; i++) {
			adjList.put(i, new LinkedList<Edge>());
		}
	}

	public void printAdjacencyList() {
		for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
	}

	public void addEdge(int u, int v) {
		Edge n = new Edge(v, 1);
		adjList.get(u).add(n);
	}
	
	public void addEdge(int u, int v, int w) {
		Edge n = new Edge(v, w);
		adjList.get(u).add(n);
	}

	public int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}

	public Map<Integer, List<Edge>> getAdjList() {
		return adjList;
	}

	public void setAdjList(Map<Integer, List<Edge>> adjList) {
		this.adjList = adjList;
	}

}
