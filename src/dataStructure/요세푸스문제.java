package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	문제
//	요세푸스 문제는 다음과 같다.
//	1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 
//	이제 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 
//	이 과정은 N명의 사람이 모두 제거될 때까지 계속된다. 
//	원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 
//	예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
//	N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.
//	
//	입력
//	첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 5,000)
//	
//	출력
//	예제와 같이 요세푸스 순열을 출력한다.

public class 요세푸스문제 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] params=in.readLine().split(" ");
		
		final int N=Integer.parseInt(params[0]);
		final int K=Integer.parseInt(params[1]);
		
		Queue<Integer> queue= new LinkedList<Integer>();
		List<Integer> result=new LinkedList<Integer>();
		
		요세푸스문제.mkRoundtable(N, queue);
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			// key point
			if(size<K) {
				int rotate = K%size==0? size-1: (K%size)-1;
				요세푸스문제.addTargetToList(rotate, queue, result);
			}else {
				int rotate=K-1;
				요세푸스문제.addTargetToList(rotate, queue, result);
			}
		}
		System.out.println(result.toString().replaceAll("\\[", "<").replaceAll("\\]", ">"));
	}
	
	public static void addTargetToList(int rotate, Queue<Integer> queue, List<Integer> result) {
		for(int i=0; i<rotate; i++) {
			queue.add(queue.poll());
		}
		result.add(queue.poll());
	}
	
	public static void mkRoundtable(int N, Queue<Integer> queue) {
		for(int i=0; i<N; i++) {
			queue.add(i+1);
		}
	}

}
