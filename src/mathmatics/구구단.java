package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	문제
//	N을 입력받은 뒤, 구구단 N단을 출력하는 프로그램을 작성하시오. 출력 형식에 맞춰서 출력하면 된다.
//	
//	입력
//	첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 9보다 작거나 같다.
//	
//	출력
//	출력형식과 같게 N*1부터 N*9까지 출력한다.
public class 구구단 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		for(int i=0; i<9; i++) {
			System.out.printf("%d * %d = %d\n",N,i+1,N*(i+1));
		}
		in.close();
		
	}
}
