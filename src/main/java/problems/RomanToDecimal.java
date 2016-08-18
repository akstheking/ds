package problems;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimal {

	public static final char[] arr = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
	public final static int[] value = { 1, 5, 10, 50, 100, 500, 1000 };
	public static final Map<Character, Integer> valueMap = new HashMap<>();

	public static void main(String[] args) {
		for (int i = 0, j = 0; i < arr.length && j < value.length; i++, j++) {
			valueMap.put(arr[i], value[j]);
		}
		String num[] = { "I", "II", "IV", "VIII", "IX", "XL", "MCMIV" , "CMIV"};
		for (String n : num) {
			System.out.println(convertToDec(n));
		}

	}

	public static int convertToDec(String rom) {
		int l = rom.length();
		char[] charArr = rom.toCharArray();
		int num = 0;
		int lastValue = 0;
		for (int i = charArr.length - 1; i >= 0; i--) {
			int charValue = valueMap.get(charArr[i]);
			if (charValue >= lastValue) {
				num += charValue;
				lastValue = charValue;
			} else {
				num -= charValue;
			}

			/*
			 * while (i >= 0 && charArr[i] == 'I') { i--; num++; } while (i >= 0
			 * && (charArr[i] == 'V' || charArr[i] == 'I')) { if (charArr[i] ==
			 * 'V') { num += 5; } if (charArr[i] == 'I') { num--; } i--; }
			 */
		}
		return num;
	}

}
