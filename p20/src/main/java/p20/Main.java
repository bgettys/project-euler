package p20;

import java.util.ArrayList;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * 
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800, and the sum of the
 * digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * 
 * Find the sum of the digits in the number 100!
 * 
 * @author rgettys
 * 
 */
public class Main {

	private static class LongHolder {

		public long value;

		public LongHolder(long value) {
			this.value = value;
		}

	}

	public static void main(String[] args) {
		ArrayList<LongHolder> product = new ArrayList<>();
		product.add(new LongHolder(1L));
		for (int i = 1; i <= 100; ++i) {
			long remainder = 0;
			for (LongHolder lh : product) {
				lh.value *= i;
				lh.value += remainder;
				if (lh.value > 999999999L) {
					remainder = lh.value / 1000000000L;
					lh.value -= remainder * 1000000000L;
				} else {
					remainder = 0;
				}
			}
			if (remainder > 0) {
				product.add(new LongHolder(remainder));
			}
		}
		long digitSum = 0;
		for (LongHolder lh : product) {
			String strProduct = String.valueOf(lh.value);
			int digits = strProduct.length();
			for (int i = 0; i < digits; ++i) {
				digitSum += Integer.parseInt(String.valueOf(strProduct.charAt(i)));
			}
		}
		System.out.println(digitSum);
	}

}
