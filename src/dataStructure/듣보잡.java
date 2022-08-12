package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 문제
// 김진영이 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 듣도 못한 사람의 수 N, 보도 못한 사람의 수 M이 주어진다. 
// 이어서 둘째 줄부터 N개의 줄에 걸쳐 듣도 못한 사람의 이름과, N+2째 줄부터 보도 못한 사람의 이름이 순서대로 주어진다. 
// 이름은 띄어쓰기 없이 알파벳 소문자로만 이루어지며, 그 길이는 20 이하이다. N, M은 500,000 이하의 자연수이다.
// 듣도 못한 사람의 명단에는 중복되는 이름이 없으며, 보도 못한 사람의 명단도 마찬가지이다.
//
// 출력
// 듣보잡의 수와 그 명단을 사전순으로 출력한다.

public class 듣보잡 {
	
	private static int N;
	private static int M;
	private static Map<String, String> personsDontHeard = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] params= in.readLine().split(" ");
		N=Integer.parseInt(params[0]);
		M=Integer.parseInt(params[1]);
		for(int i=0; i<N; i++) {
			String pdh= in.readLine();
			personsDontHeard.put(pdh, pdh);
		}
		List<String> result= new LinkedList<String>();
		for(int i=0; i<M; i++) {
			String pds=in.readLine();
			if(personsDontHeard.get(pds)!=null) {
				result.add(pds);
			}
		}
		Collections.sort(result);
		System.out.println(result.size());
		for(String name : result) {
			System.out.println(name);
		}
		in.close();
	}
}
