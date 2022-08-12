package mathmatics;

//	문제
//	두 자연수 A와 B가 주어진다. 이때, A+B, A-B, A*B, A/B(몫), A%B(나머지)를 출력하는 프로그램을 작성하시오. 
//	
//	입력
//	두 자연수 A와 B가 주어진다. (1 ≤ A, B ≤ 10,000)
//	
//	출력
//	첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A*B, 넷째 줄에 A/B, 다섯째 줄에 A%B를 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사칙연산 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] params = in.readLine().split(" ");
		int A = Integer.parseInt(params[0]);
		int B = Integer.parseInt(params[1]);
		System.out.printf("%d\n%d\n%d\n%d\n%d",A+B,A-B,A*B,A/B,A%B);
		in.close();
	}
}
