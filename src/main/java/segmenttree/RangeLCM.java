package segmenttree;

import java.util.ArrayList;
import java.util.function.Function;

import util.Pair;

public class RangeLCM {
	public static void main(String[] args) {
		int[] input = { 5, 7, 5, 2, 10, 12, 11, 17, 14, 1, 44 };
		ArrayList<Integer> in = new ArrayList<>();
		for (int i : input) {
			in.add(i);
		}
		SegmentTree<Integer> st = new SegmentTree<>(in, new LCMFunction());
		System.out.println(st.rangeQuery(2, 5));
		System.out.println(st.rangeQuery(5, 10));
		System.out.println(st.rangeQuery(0, 10));
	}

	private static class LCMFunction implements
			Function<Pair<Integer>, Integer> {

		@Override
		public Integer apply(Pair<Integer> t) {
			int a = t.getX() == null ? 1 : t.getX();
			int b = t.getY() == null ? 1 : t.getY();
			return lcm(a, b);
		}

		private int lcm(int a, int b) {
			return (a * b) / gcd(a, b);
		}

		private int gcd(int a, int b) {
			if (a == 0) {
				return b;
			}
			return gcd(b % a, a);
		}

	}

}
