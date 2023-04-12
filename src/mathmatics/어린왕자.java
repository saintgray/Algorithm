package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//어린 왕자는 소혹성 B-664에서 자신이 사랑하는 한 송이 장미를 위해 살아간다. 
//어느 날 장미가 위험에 빠지게 된 것을 알게 된 어린 왕자는, 장미를 구하기 위해 은하수를 따라 긴 여행을 하기 시작했다. 
//하지만 어린 왕자의 우주선은 그렇게 좋지 않아서 행성계 간의 이동을 최대한 피해서 여행해야 한다. 
//아래의 그림은 어린 왕자가 펼쳐본 은하수 지도의 일부이다.
//
//빨간 실선은 어린 왕자가 출발점에서 도착점까지 도달하는데 있어서 필요한 행성계 진입/이탈 횟수를 최소화하는 경로이며, 
//원은 행성계의 경계를 의미한다. 
//이러한 경로는 여러 개 존재할 수 있지만 적어도 3번의 행성계 진입/이탈이 필요하다는 것을 알 수 있다.
//
//위와 같은 은하수 지도, 출발점, 도착점이 주어졌을 때 
//어린 왕자에게 필요한 최소의 행성계 진입/이탈 횟수를 구하는 프로그램을 작성해 보자. 
//행성계의 경계가 맞닿거나 서로 교차하는 경우는 없다. 
//또한, 출발점이나 도착점이 행성계 경계에 걸쳐진 경우 역시 입력으로 주어지지 않는다.

// 코딩테스트 입력값

//입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
//그 다음 줄부터 각각의 테스트케이스에 대해 첫째 줄에 출발점 (x1, y1)과 도착점 (x2, y2)이 주어진다. 
//두 번째 줄에는 행성계의 개수 n이 주어지며, 
//세 번째 줄부터 n줄에 걸쳐 행성계의 중점과 반지름 (cx, cy, r)이 주어진다.

// 코딩테스트 출력값
//각 테스트 케이스에 대해 어린 왕자가 거쳐야 할 최소의 행성계 진입/이탈 횟수를 출력한다.
public class 어린왕자 {

	// Variables
	InputStreamReader sr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(sr);
	StringTokenizer tk;
	Planet[][] testPlanet;
	Coordinate[][] princeAndPrincess;
	Coordinate prince;
	Coordinate princess;

	// 나의 접근 방법.
	// 테스트의 갯수를 테스트 배열에 저장한다.
	public static void main(String[] args) throws IOException {
		어린왕자 main = new 어린왕자();
		int test = Integer.parseInt(main.br.readLine());
//		System.out.println("테스트 횟수 = 행성 갱신수 는 " + test + "회입니다");
		main.testPlanet = new Planet[test][]; // Planet 배열의 모음(테스트를 여러번 하므로)
		main.princeAndPrincess = new Coordinate[test][2]; // 왕자와 공주의 좌표 배열의 묶음(테스트를 여러번 하므로)
		
		
		
		int[] result = new int[test];

		// test 번의 테스트를 위해 데이터를 입력받는다.
		for (int i = 0; i < test; i++) {
			// 출발점과 도착점을 입력받는다.(왕자와 공주)
			// 좌표객체 (Coordinate) 에 들어온 출발점과 도착점을 입력받는다.
			main.tk = new StringTokenizer(main.br.readLine());
			main.princeAndPrincess[i][0] = new Coordinate(Integer.parseInt(main.tk.nextToken()), Integer.parseInt(main.tk.nextToken()));
//			System.out.println("왕자의 좌표는: " + main.princeAndPrincess[i][0].x + "," + main.princeAndPrincess[i][0].y);
			main.princeAndPrincess[i][1] = new Coordinate(Integer.parseInt(main.tk.nextToken()), Integer.parseInt(main.tk.nextToken()));
//			System.out.println("공주의 좌표는: " + main.princeAndPrincess[i][1].x + "," + main.princeAndPrincess[i][1].y);

			// n개의 행성으로 test 번 테스트한다.

			// 그 다음부터 n줄에 걸쳐 행성계의 중점과 반지름 cx,cy,r이 주어진다.
			// 이 값들을 받아 배열에 저장하자.

			// 그 다음에는 행성계의 갯수 n이 주어진다.
			// 이 값을 받아 행성계 배열의 크기로 정한다.
			int n = Integer.parseInt(main.br.readLine());
			main.testPlanet[i] = new Planet[n];
			for (int j = 0; j < n; j++) {

//				System.out.println(i + 1 + " 번째 테스트");
//				System.out.println(n + " 개의 행성계");
//				System.out.println(j + 1 + " 번째 행성입니다.");
				main.tk = new StringTokenizer(main.br.readLine());
				main.testPlanet[i][j] = new Planet(new Coordinate(Integer.parseInt(main.tk.nextToken()), Integer.parseInt(main.tk.nextToken()))
						, Integer.parseInt(main.tk.nextToken()));
//				System.out.println(
//						"행성의 좌표는: " + main.testPlanet[i][j].location.x + "," + main.testPlanet[i][j].location.y);
			}

		}

		// 진입/이탈한 횟수를 초기화한다.
		int outAndIn = 0;
		

		// 최소 진입/이탈 수를 판단하는 과정
		// 1. 공주와 어린왕자가 같은 행성에 있는지 판단한다.
		// 행성 배열 전체를 스캔해가면서 i index의 행성 의 반지름을 r이라 한다면
		// 같은 행성에 있다는 말은 어린왕자의 좌표 ~ 행성중심까지 거리와
		// 공주의 좌표 ~ 행성중심까지의 거리가 모두 행성의 반지름보다 작다는 말이다.

		// 1-1 (같은 행성에 있다면)
		// 공주를 감싸고 있는 행성을 스캔한다.
		// 어린왕자가 공주에게 가기위해 거쳐야 하는 행성은 스캔한 행성의 반지름을 r이라고 하면
		// 원의 중심과 공주의 거리보다는 r이 커야하며, 원의 중심과 왕자의 거리보다는 r이 작아야한다.
		// 또는
		// 원의 중심과 왕자의 거리보다는 r이 커야하며, 원의 중심과 공주의 거리보다는 r이 작아야 한다.
		// 이 조건에 해당하는 원을 찾을때마다 out++
		// 조건에 일치하는 행성이 없을시 out을 출력한다.

		// 1-2. (같은 행성에 없다면)
		// 왕자가 공주를 만나기 위해 반드시 빠져나가야 하는 행성
		// = 행성 반지름의 제곱이 왕자와 행성 중심 사이 거리의 제곱보다 큰 행성
		// 왕자가 공주를 만나기 위해 반드시 들어가야 하는 행성
		// = 행성 반지름의 제곱이 공주와 행성 중심 사이 거리의 제곱보다 큰 행성

		// 메인 코드들
		for (int i = 0; i < test; i++) {
//			System.out.println("왕자와 공주를 같이 포함하고 있는 원이 있는지 확인합니다.");
			if (main.locatedSamePlanet(main.testPlanet[i], main.princeAndPrincess[i])) {
				// 이 출력값이 곧 진입횟수
				outAndIn += main.outIn(main.testPlanet[i], main.princeAndPrincess[i]);
				
			} else {
//				System.out.println("두명을 포함하는 원이 없으므로 다음 조건을 검색합니다.");
				for (Planet p : main.testPlanet[i]) {
					if ((int)Math.pow(p.radius, 2) > main.distance(main.princeAndPrincess[i][0], p) &&
							(int)Math.pow(p.radius, 2)<main.distance(main.princeAndPrincess[i][1], p)) {
//						System.out.println("왕자가 공주를 만나러 행성 하나를 빠져나갔습니다!!");
						outAndIn++;
					}
					if ((int)Math.pow(p.radius, 2) > main.distance(main.princeAndPrincess[i][1], p) &&
							(int)Math.pow(p.radius, 2)<main.distance(main.princeAndPrincess[i][0], p)) {
//						System.out.println("왕자가 공주를 만나러 행성 하나로 들어갔습니다!!");
						outAndIn++;
					}
				}

			}
			
//			System.out.println(outAndIn);
			result[i] = outAndIn;
			
			outAndIn =0;
			// 다음 테스트를 출력하기 위해 카운팅 초기화
		} // end of for{i} cycle
		
		// 결과 출력
		for(int n : result) {
			System.out.printf(n+"\n");
		}
		
		main.sr.close();
		main.br.close();
		

	}

	// methods

	// 행성 중심과 공주 또는 왕자의 거리의 제곱을 반환
	int distance(Coordinate obj, Planet planet) {
		return (int) Math.pow(obj.x - planet.location.x, 2) +(int) Math.pow(obj.y - planet.location.y, 2);
	}

	// 공주와 어린왕자가 같은 행성 내에 있는 지 여부를 리턴
	boolean locatedSamePlanet(Planet[] planetArray, Coordinate[] princeAndPrincess) {
//		System.out.println("왕자의 좌표: "+princeAndPrincess[0].toString());
//		System.out.println("공주의 좌표: "+princeAndPrincess[1].toString());
		boolean result = false;
		int d1 = 0;
		int d2 = 0;
		for (Planet p : planetArray) {
			d1 = (int) (Math.pow(p.location.x - princeAndPrincess[1].x, 2)
					+ Math.pow(p.location.y - princeAndPrincess[1].y, 2));
			d2 = (int) (Math.pow(p.location.x - princeAndPrincess[0].x, 2)
					+ Math.pow(p.location.y - princeAndPrincess[0].y, 2));
			if (d1 < (int) Math.pow(p.radius, 2) && d2 < (int) Math.pow(p.radius, 2)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// 같은 행성에 있다면 1-1의 조건에 맞는 행성의 갯수가 곧 최소 진입/ 이탈 횟수이다
	// 그 횟수를 반환한다.
	int outIn(Planet[] planetArray, Coordinate[] princeAndPrincess) {

		// 진입/이탈 횟수
		int outAndIn = 0;
		for (int i = 0; i < planetArray.length; i++) {
			// 원의 중심과 왕자와의 거리의 제곱
			int planetToprince = (int) Math.pow(planetArray[i].location.x - princeAndPrincess[0].x, 2)
					+ (int) Math.pow(planetArray[i].location.y - princeAndPrincess[0].y, 2);
			// 원의 중심과 공주와의 거리의 제곱
			int planetToprincess = (int) Math.pow(planetArray[i].location.x - princeAndPrincess[1].x, 2)
					+ (int) Math.pow(planetArray[i].location.y - princeAndPrincess[1].y, 2);
			// 원의 반지름의 제곱
			int radiusSquare = (int) Math.pow(planetArray[i].radius, 2);
			if ((radiusSquare > planetToprincess && radiusSquare < planetToprince ) ||(radiusSquare < planetToprincess && radiusSquare > planetToprince)) {
				outAndIn++;
			}
		}
		return outAndIn;

	}
	static  class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "현재 좌표는: " + this.x + "," + this.y + " 입니다.";
		}
	}

	static class Planet {
		Coordinate location;
		int radius;

		public Planet(Coordinate location, int r) {
			this.location = location;
			this.radius = r;
		}

		@Override
		public String toString() {
			return "현재 좌표는: " + this.location.x + "," + this.location.y + " 입니다.";
		}
	}

}


