package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 유기농배추 {
	
	static List<Loc> foundCab = new LinkedList<Loc>();
	static boolean[][] farm;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(in.readLine());

		// 벌레의 갯수 저장용 배열
		int[] result = new int[testCase];

		for (int tried = 0; tried < testCase; tried++) {
			// 농장 가로길이, 농장세로길이, 배추갯수
			StringTokenizer tk = new StringTokenizer(in.readLine(), " ");
			int width = Integer.parseInt(tk.nextToken());
			int height = Integer.parseInt(tk.nextToken());
			farm = new boolean[height][width];
			int numOfCab = Integer.parseInt(tk.nextToken());

			for (int i = 0; i < numOfCab; i++) {
				StringTokenizer loc = new StringTokenizer(in.readLine(), " ");
				int x = Integer.parseInt(loc.nextToken());
				int y = Integer.parseInt(loc.nextToken());

				farm[y][x] = true;
			}

			
			

			for (int i = 0; i < farm.length; i++) {
				for (int j = 0; j < farm[i].length; j++) {

					// farm[i][j] 의 값이 true 일때 = 배추가 심어져 있는 곳일 때
					if (farm[i][j]) {
						
						// 찾은 배추를 저장하는 리스트에 추가
						// 좌표는 x,y = j,i (i는 행이고 j는 열이기 때문)
						foundCab.add(new Loc(j, i));
						
						// 배추와 상하좌우로 연결된 모든 배추를 찾아 리스트에 저장
						findChained(i, j);
						
						// 리스트에 저장된 모든 배추들과 상하좌우로 연결된 배추를 찾아 리스트에 저장
						// 찾을때마다 리스트의 크기는 자동으로 증가하므로 for문이 끝나면
						// 최초 추가한 farm[i][j] 와 연결된 모든 배추 좌표가 저장된다.
						for (int k=0; k<foundCab.size(); k++) {
							
							findChained(foundCab.get(k).y,foundCab.get(k).x);
						}
						// debug
						// System.out.println(foundCab);
						
						// 배추흰지렁이가 연결된 모든 배추를 먹는다.
						for (Loc loc : foundCab) {
							farm[loc.y][loc.x] = false;
						}
						
						// 다음 탐색을 위해 리스트 비우기
						foundCab.clear();
						
						// 배추흰지렁이 ++
						result[tried]++;
					}

				} // end of for-j loop
			} // end of for-i loop

		}

		for (int insect : result) {
			System.out.println(insect);
		}
	}

	// methods

	// 매개변수로 들어온 배추의 좌표에서 상하좌우로 연결된 모든 배추를 찾아 리스트에 추가한다.
	private static void findChained(int i, int j) {

		

		try {
			if (farm[i + 1][j] && !exist(j, i + 1)) {
				foundCab.add(new Loc(j, i + 1));
			}
		} catch (IndexOutOfBoundsException e1) {
			//System.out.println("배열 밖입니다.");
		}
		try {
			if (farm[i - 1][j] && !exist(j, i - 1)) {
				foundCab.add(new Loc(j, i - 1));
			}
		} catch (IndexOutOfBoundsException e1) {
			//System.out.println("배열 밖입니다.");
		}
		try {
			if (farm[i][j + 1] && !exist(j + 1, i)) {
				foundCab.add(new Loc(j + 1, i));
			}
		} catch (IndexOutOfBoundsException e1) {
			//System.out.println("배열 밖입니다.");
		}
		try {
			if (farm[i][j - 1] && !exist(j - 1, i)) {
				foundCab.add(new Loc(j - 1, i));
			}
		} catch (IndexOutOfBoundsException e1) {
			//System.out.println("배열 밖입니다.");
		}

	}

	// 매개변수로 들어온 배추의 좌표가 foundCab 리스트에 이미 있으면 true, 아니면 false를 반환한다.
	private static boolean exist(int x, int y) {
		boolean result = false;
		for (Loc obj : foundCab) {
			if (obj.x == x && obj.y == y) {
				result = true;
			}
		}
		return result;
	}

//	private static void showFarm() {
//		for (int i = 0; i < farm.length; i++) {
//			for (int j = 0; j < farm[i].length; j++) {
//				if (farm[i][j] == true) {
//					System.out.print("[1]");
//				} else {
//					System.out.print("[0]");
//				}
//			}
//			System.out.println();
//		}
//	}

}

// 배추위 위치
class Loc {
	int x;
	int y;

	public Loc(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Loc) {
			Loc loc = (Loc) obj;
			if (loc.x == this.x && loc.y == this.y) {
				result = true;
			}
		}
		return result;
	}

	public String toString() {
		return String.valueOf(this.x).concat(",").concat(String.valueOf(this.y));
	}

}
