package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//서기 2012년! 드디어 2년간 수많은 국민들을 기다리게 한 게임 ACM Craft (Association of Construction Manager Craft)가 발매되었다.
//
//이 게임은 지금까지 나온 게임들과는 다르게 ACM크래프트는 다이나믹한 게임 진행을 위해 건물을 짓는 순서가 정해져 있지 않다. 
//즉, 첫 번째 게임과 두 번째 게임이 건물을 짓는 순서가 다를 수도 있다. 
//매 게임시작 시 건물을 짓는 순서가 주어진다. 또한 모든 건물은 각각 건설을 시작하여 완성이 될 때까지 Delay가 존재한다.

//위의 예시를 보자.
//
//이번 게임에서는 다음과 같이 건설 순서 규칙이 주어졌다. 1번 건물의 건설이 완료된다면 2번과 3번의 건설을 시작할수 있다. 
//""""""""""""""""""(동시에 진행이 가능하다) """"""""""""""""""""""
//그리고 4번 건물을 짓기 위해서는 2번과 3번 건물이 모두 건설 완료되어야지만 4번건물의 건설을 시작할수 있다.
//
//따라서 4번건물의 건설을 완료하기 위해서는 우선 처음 1번 건물을 건설하는데 10초가 소요된다. 
//그리고 2번 건물과 3번 건물을 동시에 건설하기 시작하면 2번은 1초뒤에 건설이 완료되지만 아직 3번 건물이 완료되지 않았으므로 4번 건물을 건설할 수 없다.
//3번 건물이 완성되고 나면 그때 4번 건물을 지을수 있으므로 4번 건물이 완성되기까지는 총 120초가 소요된다.
//
//프로게이머 최백준은 애인과의 데이트 비용을 마련하기 위해 서강대학교배 ACM크래프트 대회에 참가했다! 
//최백준은 화려한 컨트롤 실력을 가지고 있기 때문에 모든 경기에서 특정 건물만 짓는다면 무조건 게임에서 이길 수 있다. 
//그러나 매 게임마다 특정건물을 짓기 위한 순서가 달라지므로 최백준은 좌절하고 있었다.
//백준이를 위해 특정건물을 가장 빨리 지을 때까지 걸리는 최소시간을 알아내는 프로그램을 작성해주자.

//입력

//첫째 줄에는 테스트케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 주어진다.
//첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K이 주어진다. (건물의 번호는 1번부터 N번까지 존재한다) 
//
//둘째 줄에는 각 건물당 건설에 걸리는 시간 D1, D2, ..., DN이 공백을 사이로 주어진다. 
//셋째 줄부터 K+2줄까지 건설순서 X Y가 주어진다. (이는 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능하다는 의미이다) 
//
//마지막 줄에는 백준이가 승리하기 위해 건설해야 할 건물의 번호 W가 주어진다.
//
//
//출력

//건물 W를 건설완료 하는데 드는 최소 시간을 출력한다. 편의상 건물을 짓는 명령을 내리는 데는 시간이 소요되지 않는다고 가정한다.
//
//건설순서는 모든 건물이 건설 가능하도록 주어진다.

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
		for (

				int i = 0; i < fastestBuildTime.length; i++) {
			System.out.printf("%d ", fastestBuildTime[i]);
		}
	}
}
class Buildings {
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
