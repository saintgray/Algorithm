package Q1_100;

//두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.
//첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)
//첫째 줄에 A-B를 출력한다.

import java.util.Scanner;

public class Q2_A빼기B {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			if ((A > 0 && B < 10)) {
				System.out.println(A - B);
				sc.close();
				break;
			}
		}
		System.exit(0);
		
	}
	

}

