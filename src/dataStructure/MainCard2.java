package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//	문제
//	숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 
//	정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.
//	
//	입력
//	첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 
//	둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 
//	숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
//	
//	셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다. 
//	넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수가 주어지며, 
//	이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
//	
//	출력
//	첫째 줄에 입력으로 주어진 M개의 수에 대해서, 
//	각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.

public class MainCard2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(in.readLine());
		List<String> cnl = Arrays.asList(in.readLine().split(" "));
		final int M = Integer.parseInt(in.readLine());
		List<String> wcnl = Arrays.asList(in.readLine().split(" "));

		Collections.sort(cnl, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.parseInt(o1) > Integer.parseInt(o2) ? 1
						: Integer.parseInt(o1) == Integer.parseInt(o2) ? 0 : -1;
			}
		});
		
		int nextScan = 0;

		String result = "";
		
//		System.out.println(cnl);
		// cnl 에 대해 이분 탐색
		for (String num : wcnl) {
			
			nextScan = Integer.parseInt(num);

			int count = 0;
			boolean countCompleted = false;
			int curIndex = N / 2;
			int searchIndex = curIndex;
			int max = N;
			int min = 1;
//			System.out.println("curIndex is initiated : "+ curIndex);
			while (!countCompleted) {
				int indexNum = Integer.parseInt(cnl.get(curIndex));
				if (indexNum == nextScan) {
//					System.out.println(nextScan + " is equals to indexNum : " + indexNum);
					count++;

					while (searchIndex -1 >= 0 && nextScan == Integer.parseInt(cnl.get(searchIndex - 1))) {
						count++;
						searchIndex--;
						if (searchIndex == 0)
							break;
					}
					searchIndex = curIndex;
					while (searchIndex +1 < cnl.size() && nextScan == Integer.parseInt(cnl.get(searchIndex + 1))) {
						count++;
						searchIndex++;
						if (searchIndex == cnl.size() - 1)
							break;
					}
					countCompleted = true;
				} else {
					
					if (indexNum < nextScan) {
						min = curIndex;
						// 새로운 midpoint ( = targetIndex ) 가 이전 midpoint 와 같다면
						// 찾는 수는 없다는 뜻이므로 while loop exit
						int targetIndex=(max+curIndex)/2;
						if(targetIndex==curIndex) {
							break;
						}else {
//							curIndex = (max + curIndex) / 2;
							curIndex=targetIndex;
						}
						
					} else {
						max = curIndex;
						curIndex = (min + curIndex) / 2;
					}
					searchIndex = curIndex;		
					}
				}
			result = result.concat(String.valueOf(count).concat(" "));
			curIndex = N / 2;
		}
		System.out.println(result);
		
		// 시간 초과가 어디서 뜨는 것일까?
		// sort 에서? 아니면 무한 루프?
	}
}
