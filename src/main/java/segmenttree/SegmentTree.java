package segmenttree;

import java.util.ArrayList;
import java.util.function.Function;

import util.Pair;

public class SegmentTree<T> {

	Pair<T> pair;
	Function<Pair<T>, T> f;
	ArrayList<T> input;
	ArrayList<T> tree;

	private int getLeft(int i) {
		return 2 * i + 1;
	}

	private int getRight(int i) {
		return 2 * i + 2;
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	public SegmentTree(ArrayList<T> input, Function<Pair<T>, T> f) {
		this.f = f;
		this.input = new ArrayList<>(input);
		buildTree();
	}

	private void buildTree() {
		int height = (int) Math.ceil((Math.log(input.size()) / Math.log(2)));
		int treeSize = (int) (Math.pow(2, height + 1)) - 1;
		tree = new ArrayList<T>(treeSize);
		for (int i = 0; i < treeSize; i++) {
			tree.add(null);
		}
//		System.out.println(tree.size());
		buildTreeUtil(0, input.size(), 0);
	}

	private T buildTreeUtil(int ss, int se, int si) {
		int mid = (ss + se) / 2;
		if (ss == se) {
			T value = (ss < input.size()) ? input.get(ss) : null;
//			System.out.println(ss + " : " + si + " : " + value);
			tree.set(si, value);
			return value;
		}
		T result = f.apply(new Pair<T>(buildTreeUtil(ss, mid, getLeft(si)),
				buildTreeUtil(mid + 1, se, getRight(si))));
		tree.set(si, result);
		return result;
	}

	public T rangeQuery(int a, int b) {
		if (a > b || a < 0 || b >= input.size()) {
			return null;
		}
		return rangeUtil(0, input.size() - 1, a, b, 0);
	}

	private T rangeUtil(int l, int r, int a, int b, int si) {
//		System.out.println(l + " : " + r);
		if(b < l || a > r) {
			return null;
		}
		if(l >= a && r <= b) {
//			System.out.println(si + " : " + tree.get(si));
			return tree.get(si);
		}
		int mid = (l + r) / 2;
		
		return f.apply(new Pair<T>(rangeUtil(l, mid, a, b, getLeft(si)), rangeUtil(mid+1, r, a, b, getRight(si))));
	}

	public void printSegmentTree() {
		System.out.println(tree);
	}
}
