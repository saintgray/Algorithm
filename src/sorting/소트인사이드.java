package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	문제
//	배열을 정렬하는 것은 쉽다. 수가 주어지면, 그 수의 각 자리수를 내림차순으로 정렬해보자.
//	
//	입력
//	첫째 줄에 정렬하려고 하는 수 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수이다.
//	
//	출력
//	첫째 줄에 자리수를 내림차순으로 정렬한 수를 출력한다.

public class 소트인사이드 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		int nod = (int) Math.log10((double) num) + 1;

		for (int i = nod - 1; i > 0; i--) {
			int leadingDigit = (num % (int) Math.pow(10, i+1)) / (int) Math.pow(10, i);
			int lastDigit = (num % (int) Math.pow(10, i)) / (int) Math.pow(10, i - 1);
			if (leadingDigit < lastDigit) {
				num = sort(num, leadingDigit, lastDigit, i);
				i = nod;
			}
		}

		System.out.println(num);
		in.close();
	}

	public static int sort(int num, int leadingDigit, int lastDigit, int i) {
		return num - ((10 * leadingDigit + lastDigit) * (int) Math.pow(10, i - 1))
				+ ((10 * lastDigit + leadingDigit) * (int) Math.pow(10, i - 1));
	}
}
