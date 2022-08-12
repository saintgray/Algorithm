package Q1_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Q13_컨닝 {

	static char[][] room;
	static List<Character> brokenChairs = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(in.readLine());
		int[] result = new int[testCase];

		for (int tried = 0; tried < result.length; tried++) {
			StringTokenizer tk = new StringTokenizer(in.readLine());

			int height = Integer.parseInt(tk.nextToken());
			int width = Integer.parseInt(tk.nextToken());

			room = new char[height][width];
			
			

			for (int i = 0; i < room.length; i++) {
				String line = in.readLine();
				int lineSize = line.length();
				for (int j = 0; j < lineSize; j++) {
					room[i][j] = line.charAt(j);
				}
			}
			
			int countByOdd = 0;
			int countByEven = 0;
			for (int j = 0; j < width; j += 2) {
				for (int i = 0; i < height; i++) {
					countByOdd=checkNeighborLine(countByOdd, i,j);
					
					if (room[i][j] == '.') {
						// 해당 책상을 컨닝할수 있는 이웃한 6자리를 마스킹한다
						countByOdd++;
					}
//					else {
//						countByOdd=checkNeighborLine(countByOdd, i,j);
//					}
				}
			}
			for (int j = 1; j < width; j += 2) {
				for (int i = 0; i < height; i++) {
					countByEven=checkNeighborLine(countByEven, i,j);
					if (room[i][j] == '.') {
						countByEven++;
					}
//					else {
//						countByEven=checkNeighborLine(countByEven, i,j);
//					}
				}
			}

			result[tried] = Math.max(countByOdd, countByEven);
			

		} // end of calculate

		for (int student : result) {
			System.out.println(student);
		}

	}

//	public static void showRoom() {
//		for (int i = 0; i < room.length; i++) {
//			for (int j = 0; j < room[i].length; j++) {
//				System.out.print(room[i][j]);
//			}
//			System.out.println();
//		}
//	}

	public static int checkNeighborLine(int count, int i, int line) {
		// 해당 줄(line) 에 이웃한 왼쪽 줄을 스캔하면서 해당 책상에 컨닝이 가능한 자리의 책상이 모두 부숴져있다면
		// 앉을 수 있는 자리이므로 추가로 카운팅해준다.
		int neighborLine = line - 1;
		if (neighborLine >= 0) {

			if (room[i][neighborLine] == '.') {
				if (allBroken(i, neighborLine)) {
					count++;
				}
			}
		}
		return count;
	}

	public static boolean allBroken(int i, int j) {
		boolean result = true;
		try {
			brokenChairs.add(room[i - 1][j - 1]);
		} catch (IndexOutOfBoundsException e1) {

		}
		try {
			brokenChairs.add(room[i][j - 1]);
		} catch (IndexOutOfBoundsException e1) {

		}
		try {
			brokenChairs.add(room[i + 1][j - 1]);
		} catch (IndexOutOfBoundsException e1) {

		}

		try {
			brokenChairs.add(room[i - 1][j + 1]);
		} catch (IndexOutOfBoundsException e1) {

		}
		try {
			brokenChairs.add(room[i][j + 1]);
		} catch (IndexOutOfBoundsException e1) {

		}

		try {
			brokenChairs.add(room[i + 1][j + 1]);
		} catch (IndexOutOfBoundsException e1) {

		}
		for (Character c : brokenChairs) {
			if (c == '.') {
				result = false;
				break;
			}
		}
		brokenChairs.clear();
		return result;
	}
	
	

}
