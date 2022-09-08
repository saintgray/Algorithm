package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class 다리놓기 {
	static long N;
	static long M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(in.readLine());
		BigInteger[] result = new BigInteger[testCase];

		for(int i=0;i<result.length; i++) {
			

			StringTokenizer tk = new StringTokenizer(in.readLine());
			N = Long.parseLong(tk.nextToken());
			M = Long.parseLong(tk.nextToken());
			

			// N개의 사이트를 M개의 사이트에 연결하는 경우의 수는 M개에서 N개를 뽑는 조합의 공식과 같다.
			//  mCn = m! / (m-n)! * n!
			// 팩토리얼의 결과는 10이상으로 넘어가면 기하급수적으로 증가하기 때문에
			// int, long으로 불가능하며 (테스트 의 범위는 최대 30)
			// BigInteger를 통하여 경우의 수를 도출해야 한다.
				
			result[i] = (factorial(M)).divide(factorial(N).multiply((factorial(M - N))));
			
		}
		
		
		for(BigInteger bi: result) {
			System.out.println(bi);
		}
		
		

	}

	public static BigInteger factorial(long num) {
		BigInteger result = new BigInteger(String.valueOf(1));

		for (int i = 1; i <= num; i++) {
			BigInteger multi = new BigInteger(String.valueOf(i));
			result = result.multiply(multi);
		}
		return result;
	}

}
