package myds;

import java.util.ArrayList;

public class MinHeap<E extends Comparable<E>> {

	public static void main(String[] args) {
		MinHeap<Integer> h = new MinHeap<>();
		h.insertKey(3);
		h.insertKey(2);
		h.deleteKey(1);
		h.insertKey(15);
		h.insertKey(5);
		h.insertKey(4);
		h.insertKey(45);
		h.printHeap();
		System.out.println(h.extractMin());
		h.printHeap();
		System.out.println(h.getMin());
		h.decreaseKey(2, new Integer(1));
		h.printHeap();
		System.out.println(h.getMin());
	}

	ArrayList<E> list;

	public MinHeap() {
		list = new ArrayList<>();
	}

	public void printHeap() {
		System.out.println(list);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(E item) {
		return list.contains(item);
	}

	public void insertKey(E item) {
		list.add(item);
		bubbleUp(list.size() - 1);
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private int getLeft(int i) {
		return 2 * i + 1;
	}

	private int getRight(int i) {
		return 2 * i + 2;
	}

	public E getMin() {
		return list.get(0);
	}

	public E extractMin() {
		E root = list.get(0);
		E last = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		if (!isEmpty()) {
			list.set(0, last);
			heapify(0);
		}
		return root;
	}

	public void decreaseKey(int index, E value) {
		if (index >= 0 && index < list.size()) {
			list.set(index, value);
			bubbleUp(index);
		}
	}

	public void decreaseKey(E source, E value) {
		int index = list.indexOf(source);
		if (index >= 0 && index < list.size()) {
			list.set(index, value);
			bubbleUp(index);
		}
	}

	public void deleteKey(int index) {
		list.set(index, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		heapify(index);
	}

	private void heapify(int index) {
		int left = getLeft(index);
		int right = getRight(index);
		int smallest = index;

		if (left < list.size()
				&& list.get(left).compareTo(list.get(smallest)) < 0) {
			smallest = left;
		}
		if (right < list.size()
				&& list.get(right).compareTo(list.get(smallest)) < 0) {
			smallest = right;
		}
		if (smallest != index) {
			swap(smallest, index);
			heapify(smallest);
		}
	}

	private void swap(int a, int b) {
		E temp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, temp);
	}

	private void bubbleUp(int index) {
		int parent = getParent(index);
		while (parent >= 0 && (list.get(parent).compareTo(list.get(index)) > 0)) {
//			System.out.println(parent + " : " + index);
			swap(parent, index);
			index = parent;
			parent = getParent(parent);
		}
	}

}
