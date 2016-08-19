package graphs;

import util.Utility;
import graphs.Graph.Edge;
import myds.MinHeap;

public class DjikstraUsingAdjacency {

	public static void main(String[] args) {
		Graph g = new Graph(9);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(2, 3, 7);
		g.addEdge(2, 8, 2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 4, 9);
		g.addEdge(3, 5, 14);
		g.addEdge(4, 5, 10);
		g.addEdge(5, 6, 2);
		g.addEdge(6, 7, 1);
		g.addEdge(6, 8, 6);
		g.addEdge(7, 8, 7);
		shortestPath(g, 0);
	}

	private static class MinHeapNode implements Comparable<MinHeapNode> {
		int v, weight;

		public MinHeapNode(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof MinHeapNode)) {
				return false;
			}
			MinHeapNode node = (MinHeapNode) obj;
			return node.getV() == v;
		}

		@Override
		public int compareTo(MinHeapNode o) {
			return weight - o.getWeight();
		}

		@Override
		public String toString() {
			return "MinHeapNode [v=" + v + ", weight=" + weight + "]";
		}

	}

	public static void shortestPath(Graph g, int source) {
		MinHeap<MinHeapNode> heap = new MinHeap<>();
		int dist[] = new int[g.getV()];
		for (int i = 1; i < g.getV(); i++) {
			dist[i] = Integer.MAX_VALUE;
			heap.insertKey(new MinHeapNode(i, Integer.MAX_VALUE));
		}
		g.printAdjacencyList();
		heap.insertKey(new MinHeapNode(0, 0));
		heap.printHeap();
		while (!heap.isEmpty()) {
			MinHeapNode node = heap.extractMin();
			System.out.println("extracted : " + node);
			for (Edge edge : g.getAdjList().get(node.getV())) {
				MinHeapNode oldValueNode = new MinHeapNode(edge.getDest(),
						dist[edge.getDest()]);
				MinHeapNode newValueNode = new MinHeapNode(edge.getDest(),
						node.getWeight() + edge.getWeight());
//				System.out.println(oldValueNode);
//				System.out.println(newValueNode);
//				System.out.println(heap.contains(oldValueNode));
//				System.out.println(newValueNode.compareTo(oldValueNode));
				heap.printHeap();
				if (heap.contains(oldValueNode)
						&& newValueNode.compareTo(oldValueNode) < 0) {
					heap.decreaseKey(oldValueNode, newValueNode);
					dist[newValueNode.getV()] = newValueNode.getWeight();
				}
			}
		}

		Utility.printArrays(dist);
	}

}
