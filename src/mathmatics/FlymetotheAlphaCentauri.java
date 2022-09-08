package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlymetotheAlphaCentauri {

	public static void main(String[] args) throws IOException {
		// 최소작동횟수이므로 우주비행선의 이동한 경로를 그래프로 그린다 치면 최대한 많은 광년을 이동하다가
		// 어느 지점부터 목표지점 바로 전 위치에서 1광년이 되게 하기 위해 이동하는 광년이 점점 줄어드는
		// 언덕모양의 그래프 개형이 될 것임을 알 수 있다.

		// 그 그래프에서 극대값을 가지는 지점이 바로 이동하는 광년을 줄여가는 순간이므로,

		// 시작지점을 x라 하고 도착지점을 y라 할 때 이동거리를 줄이는 좌표는
		// 수열{bn}의 일반항이 bn = n (b1 =1) 인 계차수열의 교차점이다.
		// 즉 수열 {x} : x1 =x , {bn}=n 을 원수열로 갖는 계차수열
		// {y} : y1=y, {bn}=-n을 원수열로 갖는 계차수열

		// x는 증가해가고 y는 감소해가면서 특정 순간에 조작횟수 카운팅을 중지해야 하는데
		// 그 순간은 한번 더 증가/감소할 시에 x의 값이 y의 값보다 커지는 순간이다.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		

		int testCase = Integer.parseInt(in.readLine());
		int[] result = new int[testCase];

		for (int tried = 0; tried < result.length; tried++) {
			
			StringTokenizer tk = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(tk.nextToken());
			int y = Integer.parseInt(tk.nextToken());

			int n = 0; // 조작횟수
			
	
			while (true) {
				x += n;
				y -= n;

			    // 다음 번째 증가/감소에서 x의 값이 y의 값보다 같거나 커진다면 즉, (y-(n+1) -{(x-(n+1))} <= 0 라면
				// 조작횟수는 기본적으로 2n번일 것이고 (x에서 시작하면서 n번 조작 + 분기점을 지나 y점까지 가면서 n번)
				// 분기점에서 추가로 몇번 조작을 해야하는지만 판단하면 된다.
				
				
				if (y-x <= 2*(n+1)) {
					// 만약 x와 y가 같다면 딱 맞아떨어지므로 추가 조작횟수가 필요없다 = 총 조작횟수는 2n번
					if(x==y) {
						result[tried] = 2*n;
						
					// 남은 거리 D (y-x) 가 한번 더 이동할때의 거리 (n+1) 보다 작거나 같다면 추가로 한번만 더 조작하면 되므로 총 조작횟수는 2n+1 번
					}else if(y-x <= n+1) {
						result[tried] = 2*n+1;
					// 남은 거리 D 가 n+1 보다 크다면 2번 더 조작해야하므로 총 조작횟수는 2n + 2 번이다.
					}else { // (y-x >= n+1)
						result[tried] = 2*(n+1);
					}
					break;
				} else {
					n++;
				}
			}


		}
		
		
		for(int times : result) {
			System.out.println(times);
		}
		
	}

}
