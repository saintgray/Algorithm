package sorting;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//	문제
//	N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//	
//	입력
//	첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수 주어진다. 
//	이 수는 절댓값이 1,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
//	
//	출력
//	첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
public class 수정렬하기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> array = new LinkedList<Integer>();
		int size = Integer.parseInt(in.readLine());
		for(int i=0; i<size; i++) {
			array.add(Integer.parseInt(in.readLine()));
		}
		
		Collections.sort(array, (o1, o2) -> o1>o2?1:o1==o2?0:-1);

		// test case
		// N = 5; numbers : 1, 1, 5, 4, 3


		// filter 사용법
		// collect 사용법
//		 array = array.stream().filter(num->num==1).collect(Collectors.toList());
		// expected result = 1,1


		// addAll(Collections ..) 과 collect 의 혼합사용
//		 array.addAll(array.stream().collect(Collectors.toList()));
		// expected result = 1, 1, 3, 4, 5, 1, 1, 3, 4, 5


		// map 객체 사용법
//		 array = array.stream().map(obj->obj*3).collect(Collectors.toList());
		// expected result = 3, 3, 9, 12, 15

		
		for(Integer num : array) {
			System.out.printf("%d\n",num);
		}
		in.close();
	}

}
