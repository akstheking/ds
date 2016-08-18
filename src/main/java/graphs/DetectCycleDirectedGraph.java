package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import graphs.Graph.Edge;

public class DetectCycleDirectedGraph {

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
//	    g.addEdge(2, 0);
//	    g.addEdge(2, 3);
	    g.addEdge(3, 3);
	    g.printAdjacencyList();
	    System.out.println(isGraphCyclic(g));
	}
	
	public static boolean isGraphCyclic(Graph g) {
		boolean visited[] = new boolean[g.getV()];
		boolean recStack[] = new boolean[g.getV()];
		
		for(Map.Entry<Integer, List<Edge>> entry : g.getAdjList().entrySet()) {
			if(isGraphCyclicUtil(g, entry.getKey(), visited, recStack)) {
//				System.out.println(entry.getKey());
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGraphCyclicUtil(Graph g, int v, boolean[] visited, boolean[] recStack) {
		System.out.println(v);
		visited[v] = true;
		recStack[v] = true;
		for(Edge u : g.getAdjList().get(v)) {
			if(!visited[u.getDest()] && !isGraphCyclicUtil(g, u.getDest(), visited, recStack)) {
				return false;
			} else if(recStack[u.getDest()]){
				System.out.println("cyclic for : " + u);
				return true;
			}
		}
		recStack[v] = false;
		System.out.println(Arrays.toString(recStack));
		return false;
	}

}
