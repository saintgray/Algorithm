package sorting;

//	문제
//	N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//	
//	입력
//	첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 
//	이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
//	
//	출력
//	첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(in.readLine());
		List<Integer> list = new LinkedList<Integer>();
		for(int i=0; i<size; i++) {
			list.add(Integer.parseInt(in.readLine()));
		}
		Collections.sort(list, (o1, o2) -> o1>o2?1:o1==o2?0:-1);
		list.stream().forEach(num ->{System.out.println(num);});
	}
}
