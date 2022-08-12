package Q1_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q8_분산처리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] result = new int[Integer.parseInt(in.readLine())];

		int tried = 0;
		while (tried < result.length) {

			StringTokenizer tk = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());

			// a의 b제곱을 출력합니다.
			// System.out.println(Math.pow(a, b));
			// b의 범위가 최대 100만, a의 값이 최대 100이기 때문에 long type을 쓰더라도 연산이 불가능하다 (Infinity)

			// 1번 컴퓨터가 첫번째 데이터 처리~ 10번 컴퓨터가 10번째 데이터를 처리하므로
			// a의b 제곱 개의 데이터를 처리할 때에 마지막에 데이터를 처리하는 컴퓨터의 번호는 a의b제곱의 1의 자리 숫자와 같다.

			// 그 수는 a의 일의자리의 숫자와 관련이 있다.
			// 예를들어 a가 28라고 하자. a의 b제곱을 하기 위해서 a를 계속 곱해주게 되는데, 계산 과정에서 1의 자리숫자는 순서대로 28의 8에
			// 28의 8를 곱한 64의 4
			// >> 64의 4에서 28의 8을 곱한 32의 2 >> 32의 2에서 28의 8을 곱한 값 = 16의 일의 자리수 6
			// 즉 a의 일의자리수가 8일 경우 8 4 2 6 의 로테이션으로 반복된다.

			int numOfDigitsBya = a % 10;
			// 이 첫번째 숫자가 몇번 제곱이 되어야 다시 그 자신이 되는지 확인한다.
			boolean rotationChk = false;
			int temp = numOfDigitsBya;
			int countAllElementForOneCycle = 1;
			while (!rotationChk) {

				temp *= numOfDigitsBya;

				if (temp % 10 == numOfDigitsBya) {
					rotationChk = true;
				} else {
					countAllElementForOneCycle++;
				}
			}
			
			

			 //System.out.println("rotation for num: "+numOfDigitsBya+" = "+countAllElementForOneCycle);

			// 제곱수 b를 countAllElementForOneCycle 로 나눈 나머지를 numOfDigitsBya 의 제곱수에 넣으면 된다.
			// 그 결과값의 일의 자리 숫자가 바로 처리해야하는 컴퓨터의 번호이다.
			
			// 인데 b를 countAllElementForOneCycle 로 나눌때 0이 될때 numOfDigitsBya 에서 제곱해야 하는 수는 0 이 아니라
			// 전체 사이클이어야 한다(countAllElementForOneCycle
			 
			 int comp = 0;
			 if(b % countAllElementForOneCycle ==0) {
				 comp = (int)Math.pow(numOfDigitsBya, countAllElementForOneCycle) % 10;
			 }else {
				 comp = (int)Math.pow(numOfDigitsBya, b % countAllElementForOneCycle) % 10;
			 }
			 
			 // comp 0 일 때는 10번 컴퓨터가 데이터 처리를 해야하므로 별도 출력처리를 해준다.( 0번 컴퓨터가 아니라 10번 컴퓨터이기 때문)
			 if(comp ==0) {
				 result[tried] = 10;
			 }else {
				 result[tried] = comp;
			 }

			
			tried++;
			
		}

		for (int num : result) {
			System.out.println(num);
		}
		// 출력되는 결과와 일치함을 확인했다.
	}

}
