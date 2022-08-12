package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	문제
//	두 정수 A와 B를 입력받은 다음, A×B를 출력하는 프로그램을 작성하시오.
//	
//	입력
//	첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)
//	
//	출력
//	첫째 줄에 A×B를 출력한다.
public class AXB {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] params=in.readLine().split(" ");
		System.out.println(Integer.parseInt(params[0]) * Integer.parseInt(params[1]));
		in.close();
	}
}
