package Q1_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Q4_피보나치수열 {
	

//	fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
//
//	fibonacci(3)은 fibonacci(2)와 fibonacci(1) (첫 번째 호출)을 호출한다.
//	fibonacci(2)는 fibonacci(1) (두 번째 호출)과 fibonacci(0)을 호출한다.
//	두 번째 호출한 fibonacci(1)은 1을 출력하고 1을 리턴한다.
//	fibonacci(0)은 0을 출력하고, 0을 리턴한다.
//	fibonacci(2)는 fibonacci(1)과 fibonacci(0)의 결과를 얻고, 1을 리턴한다.
//	첫 번째 호출한 fibonacci(1)은 1을 출력하고, 1을 리턴한다.
//	fibonacci(3)은 fibonacci(2)와 fibonacci(1)의 결과를 얻고, 2를 리턴한다.
//	1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, fibonacci(N)을 호출했을 때, 
//	0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.
	
// 단 N은 40보다 작거나 같은 자연수 또는 0이다.

	// method fibonacci(int n)
	public int fibonacci(int n) {
		if (n == 0) {
			//System.out.println("0");
			return 0;
		} else if (n == 1) {
			//System.out.println("1");
			return 1;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	// fibonacci(int n) 메소드의 반환값이 곧 1이 출력되는 횟수 와 같다( 1이 출력될때마다 1을 반환해서 더하므로 )
	// System.out.println(main.fibonacci(5));

	// 0이 나오는 횟수는 0이 나올때마다 카운팅해주는 변수를 넣어주면 된다.

//	public int countZero(int n) {
//		if (n == 0) {
//			return 1;
//		} else if (n == 1) {
//			return 0;
//		}else {
//			return countZero(n-1) + countZero(n-2);
//		}
//	}
//	
	// 채점 결과 시간 초과 뜸
	
	// 2. N을 시드로 넣을 경우 완전 분열하는 총 수는 N-2 번이고 마지막에 남은 fibonacci(2)가 한번 분열하기 때문에
	// 총 출력되는 0과 1의 갯수는 2의 N-2 제곱에 1을 빼고(마지막 분열하는 2 제외) +2 를 더한다
	// 즉 2의 N-2 제곱에 1을 더한 값이 총 0과 1의 갯수이다.
	// 0이 나오는 갯수는 여기에 1이 나오는 횟수를 빼면 된다.


	public static void main(String[] args) throws IOException{
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		
//		int N = Integer.parseInt(br.readLine());
		

		// test용 코드
//		System.out.println("난수 N = " + N);
//		System.out.println("1이 출력되는 횟수는  " + main.fibonacci(N) + " 입니다");
//		System.out.println("0이 출력되는 횟수는 "+main.countZero(N)+ "입니다");
		
		// N이 주어졌다면 밑에서부터 수열을 더해가는 Bottom - up  방식을 사용할 수 있다.
		// fibonacci(N) 을 구한다고 치면
		// fibonacci(1) =1 , fibonacci(0) =0
		// fibonacci(2) = 1+0
		// fibonacci(3) = fibonacci(2) + fibonacci(1) = 1+1 = 2
		// fibonacci(4) = f(3) + f(2) = 2 + 1 = 3;
		// fibonacci(5) = f(4) + f(3) = 3 + 2 = 5;
		// ... 더해서 N까지구하면 된다!
		
//		int[] fib = new int[N+1];
//		fib[0] = 0; // fibonacci(0) =0
//		fib[1] =1; // fibonacci(1) =1
//		for(int i =2; i<N+1; i++) {
//			fib[i] = fib[i-1] + fib[i-2];
//			
//		}
		// 맨 마지막 인덱스의 저장되는 값이 바로 fibonacci(N) 값이 된다!
		// = 1이 출력되는 갯수.
		
		// 0이 출력되는 갯수는?
		// N = 0 일때 fib[0] = 0 > fibZero[0] = 1로 두고
		// N = 1 일때 fib[1] = 1 > fibZero[1] = 0으로 두자.
//		
//		int[] fibZero = new int[N+1];
//		fibZero[0] = 1;
//		fibZero[1] = 0;
//		for(int i=2; i<N+1; i++) {
//			fibZero[i] = fibZero[i-1] + fibZero[i-2];
//		}
		int T = Integer.parseInt(br.readLine());
		// 최초에 백준에서 테스트의 갯수를 보낸다.
		int[] testNumberArray = new int[T];
		for(int i =0; i<T;i++) {
			testNumberArray[i] = Integer.parseInt(br.readLine());
			// 그 다음 0부터 41 이하의 수를 보낸다.
			// 여기서 그 값을 읽어서 테스트 자연수 N을 저장하는 배열을 만든다.
		}
		
		
		
		
		for(int N : testNumberArray) {
			int[] fibOne = new int[N+1];
			int[] fibZero = new int[N+1];
			if(!(N==0 || N==1)) {
				fibOne[0] =0;
				fibOne[1] =1;
				fibZero[0] =1;
				fibZero[1] =0;
				
				for(int i =2;i<N+1;i++) {
					fibOne[i] = fibOne[i-1]+fibOne[i-2];
					fibZero[i] = fibZero[i-1]+fibZero[i-2];
				}
					
				System.out.printf("%d %d\n",fibZero[N],fibOne[N]);
				
				}else if(N==0) {
					System.out.printf("%d %d\n",1,0);
			}else {
				System.out.printf("%d %d\n",0,1);
			}
			
		}
		
	
		
		
		br.close();
		
	


	}

}
