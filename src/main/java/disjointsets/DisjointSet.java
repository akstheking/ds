package disjointsets;

import java.util.Arrays;

public class DisjointSet {

	int[] parent;
	int rank[];
	// String[] nodes;

	public static void main(String[] args) {
		// String[] nodes = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		// String[] relations = {"a b", "b d", "c f", "c i", "j e", "g j"};

		DisjointSet dus = new DisjointSet(10);
		dus.unionByRank(0, 2);

		// 4 is a friend of 2
		dus.unionByRank(4, 2);

		// 3 is a friend of 1
		dus.unionByRank(3, 1);

		// Check if 4 is a friend of 0
		if (dus.find(4) == dus.find(0))
			System.out.println("Yes");
		else
			System.out.println("No");

		// Check if 1 is a friend of 0
		if (dus.find(1) == dus.find(0))
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public void union(int a, int b) {
		int aRep = find(a);
		int bRep = find(b);

		if (aRep == bRep) {
			return;
		}

		parent[aRep] = bRep;
		rank[bRep]++;
	}

	public void unionByRank(int a, int b) {
		int aRep = find(a);
		int bRep = find(b);

		int aRank = rank[a];
		int bRank = rank[b];

		if (aRep == bRep) {
			return;
		}

		if (aRank > bRank) {
			parent[bRep] = aRep;
		} else if (aRank < bRank) {
			parent[aRep] = bRep;
		} else {
			parent[aRep] = bRep;
			rank[aRep]++;
		}

	}

	public int find(int i) {
		if (parent[i] == i) {
			return i;
		} else {
			return find(parent[i]);
		}
	}

	public int findWithPathCompression(int i) {
		if (parent[i] == i) {
			return i;
		} else {
			int result = findWithPathCompression(parent[i]);
			parent[i] = result;
			return result;
		}
	}

	public void printDs() {
		System.out.println(Arrays.toString(parent));
	}

}
