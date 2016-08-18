package disjointsets;

public class DetectCycle {

	public static void main(String[] args) {

		int V = 3, E = 3;
		Graph graph = new Graph(V, E);

		// add edge 0-1
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;

		// add edge 1-2
		graph.edge[1].src = 1;
		graph.edge[1].dest = 2;

		// add edge 0-2
		graph.edge[2].src = 0;
		graph.edge[2].dest = 2;

		if (graph.isCyclic())
			System.out.println("graph contains cycle");
		else
			System.out.println("graph doesn't contain cycle");
	}

	static class Graph {

		int V, E;
		Edge[] edge;
		int[] parent;

		class Edge {
			int src, dest;
		}

		public Graph(int V, int E) {
			this.V = V;
			this.E = E;

			edge = new Edge[E];
			parent = new int[V];

			for (int i = 0; i < E; i++) {
				edge[i] = new Edge();
			}

			for (int i = 0; i < V; i++) {
				parent[i] = -1;
			}
		}

		public int findParent(int i) {
			if (parent[i] == -1) {
				return i;
			}
			return findParent(parent[i]);
		}

		public void union(int a, int b) {
			int aRep = findParent(a);
			int bRep = findParent(b);
			parent[aRep] = bRep;
		}

		public boolean isCyclic() {
			for (Edge edge : this.edge) {
				int x = findParent(edge.src);
				int y = findParent(edge.dest);

				if (x == y) {
					return true;
				}
				System.out.println(x + " : " + y);
				union(x, y);
			}
			return true;
		}

	}

}