package dynamic;

import java.util.Arrays;

public class LIS {
	public static void main(String[] args) {
		int a[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 30 };
		findLis(a);
	}

	public static void findLis(int a[]) {
		int tmp[] = new int[a.length];
		tmp[0] = 1;
		for (int i = 1; i <= a.length - 1; i++) {
			int j = lastMax(a, i, tmp);
			if (j == -1) {
				tmp[i] = 1;
			} else {
				tmp[i] = j + 1;
			}
		}
		System.out.println(Arrays.toString(tmp));

		int max = -1;
		for (int i = 0; i < tmp.length; i++) {
			if (max < tmp[i]) {
				max = tmp[i];
			}
		}
		System.out.println(max);
	}

	public static int lastMax(int a[], int i, int[] tmp) {
		int max = -1;
		for (int j = i; j >= 0; j--) {
			if (a[j] < a[i]) {
				if (tmp[j] > max) {
					max = tmp[j];
				}
			}
		}
		return max;
	}
}
