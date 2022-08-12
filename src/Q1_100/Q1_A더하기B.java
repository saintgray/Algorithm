package Q1_100;

import java.util.Scanner;

// 백준1000 채점 요청시 주의점
// 패키지 까지 복사하지 말것
// import는 복사할것
// Class 이름을 Main으로 할 것
// main 메소드를 선언해서 코드를 작성할것


//두 정수 A와 B를 입력받은 다음 A+B를 출력하는 프로그램을 작성하시오

//입력: 첫째 줄에 A와 B가 주어진다.(0<A,, B<10)
//출력: 첫째 줄에 A+B를 출력한다.

public class Q1_A더하기B {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			if ((A > 0 && B < 10)) {
				System.out.println(A + B);
				sc.close();
				break;
			}
		}
		System.exit(0);
		
	}
}
//문제의 제목이 무엇이었는지 표시하기 위해 정답을 맞추었을 경우 클래스 이름을 변경하였음
