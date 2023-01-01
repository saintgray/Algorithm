package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//    1005 : ACM Craft
//    ref url : https://www.acmicpc.net/problem/1005
public class ACM_CRAFT {

	// 위상 정렬로 접근

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine()); // 첫번째로 테스트 번호 입력받음
		int[] fastestBuildTime = new int[test];
		int tried = 0;
		while (tried != test) {
			StringTokenizer t1 = new StringTokenizer(in.readLine(), " ");
			Buildings[] buildings = new Buildings[Integer.parseInt(t1.nextToken()) + 1];
			// 인덱스를 건물 번호로 쓸 것이므로 빌딩 배열의 0번 index는 쓰지 않고 사용한다.
			int numOfRules = Integer.parseInt(t1.nextToken());
			t1 = new StringTokenizer(in.readLine(), " ");
			int index = 1;
			while (t1.hasMoreTokens()) {
				Buildings b = new Buildings();
				b.buildTime = Integer.parseInt(t1.nextToken());
				buildings[index] = b;
				index++;
			}
			for (int i = 0; i < numOfRules; i++) {
				t1 = new StringTokenizer(in.readLine(), " ");
				int X = Integer.parseInt(t1.nextToken());
				int Y = Integer.parseInt(t1.nextToken());
				// 규칙 X Y란 X 가 지어져야만 Y를 지을 수 있다는 의미이므로
				// 예를 들어 1 3 이라는 규칙이 있을시
				// buildings[3-1 =2] 빌딩이 지어지기 위해선 X가 필요하다.
				// X를 지을 때마다 나중에 count-- 를 하여
				// count =0 일때 buildings[2] 가 지어질 수 있다.
				buildings[X].chained.add(Y);
				buildings[Y].needs.add(X);
				buildings[Y].indegree++;
			}
			int W = Integer.parseInt(in.readLine());
			// 간선수가 0인 건물을 찾는다.
			int phaseCounting = 0;
			while (!(phaseCounting == buildings.length - 1)) {
				// phaseCounting : 간선이 0인 건물을 찾을 때마다 1씩 증가시킴
				// 위상 정렬을 끝냈다는 말은 phaseCounting의 값이 건물의 수와 같아지는 순간이므로
				// 그때 반복문을 종료한다.
				for (int i = 1; i < buildings.length; i++) {
					if (buildings[i].indegree == 0 && buildings[i].checked) {
						phaseCounting++;
						buildings[i].checked = false;
						// 만약 선행 건물이 없다면 ( = 최초로 위상정렬을 시작하는 건물이면 )
						// 최단 건설 시간은 빌드타임과 같다. (바로 만들 수 있으므로)
						if (buildings[i].needs.size() == 0) {
							buildings[i].fastestBuildTime = buildings[i].buildTime;
							// 그 이외의 경우는 연결된 선행 건물들의 최단시간과 자신의 빌드타임을 더한 값의 최대값이
							// 자신의 건설 최단 시간이다. (왜냐하면 선행 건물들이 다 지어지기 전에는 짓지 못하기 때문이다.)
						} else {
							for (int k = 0; k < buildings[i].needs.size(); k++) {
								// 빌딩의 건설 최단 시간 계산
								if (buildings[i].fastestBuildTime <= buildings[buildings[i].needs.get(k)].fastestBuildTime + buildings[i].buildTime) {

									buildings[i].fastestBuildTime = buildings[buildings[i].needs.get(k)].fastestBuildTime + buildings[i].buildTime;
								}
							}
						}
						// 마지막으로 해당 건물 다음에 지을 수 있는 건물들의 간선 수를 감소시킨다.
						for (int j = 0; j < buildings[i].chained.size(); j++) {
							buildings[buildings[i].chained.get(j)].indegree--;
						}
					}
				}
			}
			fastestBuildTime[tried] = buildings[W].fastestBuildTime;
			tried++;
		}
		// result
		for (int i = 0; i < fastestBuildTime.length; i++) {
			System.out.printf("%d ", fastestBuildTime[i]);
		}
	}

	static class Buildings {
		// 이 건물을 짓고나야 지을 수 있는 건물 번호의 모음
		List<Integer> chained = new LinkedList<Integer>();
		// 이 건물을 짓기 전에 지어야 하는 건물 번호
		List<Integer> needs = new LinkedList<Integer>();
		// 이 건물의 빌드타임
		int buildTime;
		// 자신을 짓기위해 선행하여 지어야만 하는 총 건물의 갯수
		int indegree;
		// 이 건물을 짓기위한 최소시간
		int fastestBuildTime;
		// 위상정렬을 했으면 false, 아직 안했으면 true
		boolean checked = true;

	}
}

