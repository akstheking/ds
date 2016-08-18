package org.akhil.gfg.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxForWindow {

	private static int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
	private static final int WINDOW_SIZE = 3;

	public static void main(String[] args) {
		if (a.length <= 0) {
			System.out.println("No elements in array");
			return;
		}
		List<Integer> winMax = new ArrayList<>();
		LinkedList<Integer> queue = new LinkedList<>();

		queue.offer(a[0]);
		for (int i = 1; i < WINDOW_SIZE; i++) {
			while (!queue.isEmpty() && queue.peekLast() < a[i]) {
				queue.pollLast();
			}
			queue.offerLast(a[i]);
			// System.out.println(queue);
		}
		winMax.add(queue.peekFirst());
		for (int i = 1; i + WINDOW_SIZE < a.length; i++) {
			// System.out.println(a[i] + " : " + a[i-1]+" : " +queue);
			winMax.add(queue.peekFirst());
			if (queue.peekFirst() == a[i - 1]) {
				queue.pop();
			}
			int newElement = a[i + WINDOW_SIZE];

			while (!queue.isEmpty() && queue.peekLast() < newElement) {
				queue.pollLast();
			}
			queue.offerLast(newElement);
		}
		winMax.add(queue.peekFirst());
		System.out.println(winMax);
	}

}
