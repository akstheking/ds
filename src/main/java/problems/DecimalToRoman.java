package problems;

import java.util.HashMap;
import java.util.Map;

public class DecimalToRoman {

	public static final char[] arr = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
	public final static int[] value = { 1, 5, 10, 50, 100, 500, 1000 };
	public static final int[] baseValues = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	public static final String[] baseRoman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	public static final Map<Integer, Character> valueMap = new HashMap<>();

	public static void main(String[] args) {
		for (int i = 0, j = 0; i < arr.length && j < value.length; i++, j++) {
			valueMap.put(value[i], arr[j]);
		}
		String num[] = { "I", "II", "IV", "VIII", "IX", "XL", "MCMIV", "CMIV" };
		int numbers[] = { 1, 2, 4, 8, 9, 40, 1904, 904, 3549 };
		for (int n : numbers) {
			System.out.println(decToRoman(n));
		}

	}

	public static String decToRoman(int dec) {
		String rom = "";
		for (int i = 0; i < baseValues.length; i++) {
			if (dec >= baseValues[i]) {
				int q = dec / baseValues[i];
				dec = dec % baseValues[i];
				while (q-- > 0) {
					rom += baseRoman[i];
				}
				
			}
		}
		return rom;
	}

}
