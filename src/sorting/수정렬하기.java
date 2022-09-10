package sorting;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//  10989 : 수 정렬하기 3
//
//	문제
//	N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//
//	입력
//	첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다.
//	둘째 줄부터 N개의 줄에는 수가 주어진다.
//	이 수는 10,000보다 작거나 같은 자연수이다.
//
//	출력
//	첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

public class 수정렬하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<Integer, Integer> cntMap = new HashMap<>();
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(in.readLine());
			Integer cnt = cntMap.get(n);
			cntMap.put(n, cnt == null ? 1 : cnt + 1);
		}

		cntMap.keySet().stream().sorted().forEach(n -> {
			for (int j = 0; j < cntMap.get(n); j++) {
				try {
					out.write(n + "\n");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		out.flush();
		in.close();
		out.close();
	}
}
