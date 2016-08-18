package segmenttree;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/gcds-of-a-given-index-ranges-in-an-array/
 * 
 * @author akhil
 *
 */

public class GCDForGivenRange {

	int a[];
	int st[];

	public GCDForGivenRange(int[] array) {
		this.a = array;
	}

	public static void main(String[] args) {
		int a[] = { 2, 3, 6, 9, 5 };

		GCDForGivenRange gcdObj = new GCDForGivenRange(a);
		gcdObj.constructSegmentTree();
		gcdObj.printST();
		System.out.println(gcdObj.findGCDRange(1, 3));
	}

	public void printST() {
		System.out.println(Arrays.toString(st));
	}

	public void constructSegmentTree() {
		int height = (int) Math.ceil(Math.log(a.length) / Math.log(2));
		System.out.println(height);
		int size = (int) (2 * Math.pow(2, height) - 1);
		System.out.println(size);
		st = new int[size];
		constructStUtil(0, a.length - 1, 0);
	}

	public int constructStUtil(int ss, int se, int si) {
		// System.out.println(ss + " : " + se + " : " +si);
		int mid = (se + ss) / 2;
		if (ss == se) {
			// System.out.println("\n\nss : " + ss + "  " + a[ss] + "\n\n");
			st[si] = a[ss];
			return a[ss];
		}
		st[si] = gcd(constructStUtil(ss, mid, si * 2 + 1),
				constructStUtil(mid + 1, se, si * 2 + 2));
		// System.out.println("si: " + si + "  gcd:" + st[si]);
		return st[si];
	}

	public int gcd(int a, int b) {
		// System.out.println("a : " + a + " b : " + b);
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public int findGCDUtil(int ss, int se, int l, int r, int si) {
		if (se < l || ss > r) {
			return 0;
		}

		if (ss >= l && se <= r) {
			return st[si];
		}
		int mid = (ss + se) / 2;

		return gcd(findGCDUtil(ss, mid, l, r, si * 2 + 1),
				findGCDUtil(mid + 1, se, l, r, si * 2 + 2));
	}

	public int findGCDRange(int l, int r) {
		if (l > r || l < 0 || r > a.length) {
			return -1;
		}
		return findGCDUtil(0, a.length - 1, l, r, 0);
	}

}
