package Q1_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q6_VectorMatching_FAILED {
	static StringTokenizer tk;
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static Vector[][] vectorsArr;
	static Coord[] coords;
	static ArrayList<Vector[]> cases;

	// methods
	public static Vector vector(Coord c1, Coord c2) {
		Vector vector = new Vector(c1, c2);
		return vector;
	}

	public static Vector Rvector(Vector vector) {
		Vector Rvector = new Vector();
		Rvector.coordA = vector.coordB;
		Rvector.coordB = vector.coordA;
		Rvector.vector = new Coord();
		Rvector.vector.X = -vector.vector.X;
		Rvector.vector.Y = -vector.vector.Y;
		return Rvector;
	}

	// makeVectorSet
	public static Vector[][] makeVectorSet(int N, Coord[] coords) {
		// N(짝수) 개의 점으로부터 만들수 있는 총 벡터의 갯수는
		// 1-2 1-3.... 1-N, (+ rVectors ) , 2-3 2-4 .... 2-N ( + rVectors)
		// 3-4... 3-N ( +rVectors )......
		// N-1 - N (+rVectors)

		// 개수를 세어보면 (N-1개 + N-2개 + .... 1개) *2(역벡터 포함) 개 가 있다.

		vectorsArr = new Vector[N - 1][];

		for (int i = 0; i < N - 1; i++) {
			int vectorsIndex = 0;
			Vector[] vectors = new Vector[2 * (N - (i + 1))];

			for (int j = i; j < N - 1; j++) {

				Vector vector = vector(coords[i], coords[j + 1]);
				Vector reverse = Rvector(vector);
				vectors[vectorsIndex++] = vector;
				vectors[vectorsIndex++] = reverse;

			}
			vectorsArr[i] = vectors;
		}
		return vectorsArr;
	}

	// 벡터 경우의 수 계산
	public static boolean existVector(ArrayList<Vector> cases, Vector vector) {
		boolean result = false;
		for (Vector v : cases) {
			if (v.equals(vector)) {
				result = true;
			}
		}
		return result;
	}

	// 벡터의 크기
	public static double summation(ArrayList<Vector> cases, double minimum) {
		Coord sum = new Coord();
		for (Vector v : cases) {
			sum.X += v.vector.X;
			sum.Y += v.vector.Y;
		}
//		System.out.println("벡터의 X좌표: "+sum.X +","+" 벡터의 Y좌표: "+sum.Y);
		return Math.min(Math.sqrt(Math.pow(sum.X, 2) + Math.pow(sum.Y, 2)), minimum);

	}

	public static void main(String[] args) throws IOException {

		int testCase = Integer.parseInt(in.readLine());
		double[] result = new double[testCase];

		for (int testCount = 0; testCount < testCase; testCount++) {

			int N = Integer.parseInt(in.readLine());
			coords = new Coord[N];
			for (int i = 0; i < coords.length; i++) {
				tk = new StringTokenizer(in.readLine());
				coords[i] = new Coord(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()),
						String.valueOf(i));
			}

//          좌표들이 제대로 저장되었는지 디버깅합니다.
			System.out.println(Arrays.toString(coords));

			vectorsArr = makeVectorSet(N, coords);

			// 2 차원 벡터가 잘 만들어 졌는지 디버깅 합니다.
			for (int i = 0; i < vectorsArr.length; i++) {
				System.out.println(Arrays.toString(vectorsArr[i]));
			}

			// N개의 점에 대해 모든 벡터의 경우의 수를 vectors에 저장했다.

			double minimum = Double.POSITIVE_INFINITY;

			ArrayList<Vector> cases = new ArrayList<Vector>();
			int[] arrIndex = new int[vectorsArr.length];
			boolean exitWhile = false;

			// arrIndex[] 의 값들은 모두 0이다.
			// 각각의 벡터모음 라인들

			while (!exitWhile) {

				for (int i = 0; i < vectorsArr.length; i++) {

					// 2번째 방법
					// vectorsArr[i] 의 arrIndex[i] 인덱스에 있는 벡터가 리스트에 포함된 벡터라면 arrIndex[i]++
					if (!cases.isEmpty()) {
						while (existVector(cases, vectorsArr[i][arrIndex[i]])) {
							if (arrIndex[i] == vectorsArr[i].length - 1) {
								break;
							} else {
								arrIndex[i]++;
							}

						}
					}

					if (!existVector(cases, vectorsArr[i][arrIndex[i]])) {
						cases.add(vectorsArr[i][arrIndex[i]]);
					}

				}

				if (cases.size() == N / 2) {
					minimum = summation(cases, minimum);
					System.out.println("크기 갱신! :" + minimum);
				}

//              arrIndex[i] 에 있는 벡터가 리스트에 있는 벡터일때 arrIndex[i]++ 가 정상적으로 반영되었는지 확인합니다.
//				for (int i = 0; i < arrIndex.length; i++) {
//					System.out.print(arrIndex[i] + " ");
//				}
//				System.out.println();
//
//				// i 번째 인덱스에 arrIndex[i] (i인덱스의 몇 번 인덱스에 있는 벡터인지 나타내는 지표) 를 순서대로 스캔하기 위한 코드
//				int lengthSum = 0;
//				int arrIndexSum = 0;
//				int aboveFromThisIndexIsFull = 0;
//
//				for (int i = 0; i < vectorsArr.length; i++) {
//					for (int j = i; j < vectorsArr.length; j++) {
//						arrIndexSum += arrIndex[j];
//						lengthSum += vectorsArr[j].length - 1;
//					}
//
//					if (arrIndexSum == lengthSum) {
//						aboveFromThisIndexIsFull = i;
//						break;
//					} else {
//						lengthSum = 0;
//						arrIndexSum = 0;
//					}
//				}
//
//				if (aboveFromThisIndexIsFull != 0) {
//					for (int i = aboveFromThisIndexIsFull; i < vectorsArr.length; i++) {
//						arrIndex[i] = 0;
//					}
//					arrIndex[aboveFromThisIndexIsFull - 1] += 1;
//				} else {
//					if (arrIndex[0] == vectorsArr[0].length - 1) {
//						exitWhile = true;
//					} else {
//						// vectorsArr의 마지막인덱스에 있는 벡터모음의 arrIndex의 값을 1 증가시킨다.
//						arrIndex[vectorsArr.length - 1] += 1;
//					}
//				}
//
//				cases.clear();
//
//			}

				result[testCount] = minimum;
				// 시행 결과 : 되긴 하는데 좌표의 갯수가 8개가 넘어가면 계산 시간이 기하급수적으로
				// 증가해서 프로그램의 성능이 너무 낮음 >> 시간초과 + 이미 쓴 좌표를 포함하는 벡터까지도 경우에 수에 들어가서 효율이 너무 안좋음

				// 포함된 벡터를 제외하는 알고리즘을 추가했지만 또 다시 시간 초과

				// 하나의 cases 안의 벡터의 방향이 모두 반대일때 총 벡터합의 크기는 같으므로 중복제거를 또 할 수 있다.
				// 어 떻 게?

			} // end of for loop by testCount

//		for (double d : result) {
//			System.out.println(d);
//		}

		}

	}
}

class Coord {
	String no;
	int X;
	int Y;

	public Coord(int X, int Y, String no) {
		this.X = X;
		this.Y = Y;
		this.no = no;
	}

	public Coord() {

	}

	@Override
	public String toString() {
		return "(".concat(String.valueOf(this.X).concat(",".concat(String.valueOf(this.Y))).concat(")"));

	}
}

class Vector {
	String coordA;
	String coordB;
	Coord vector;

	public Vector(Coord A, Coord B) {
		this.coordA = A.no;
		this.coordB = B.no;
		this.vector = new Coord();
		vector.X = A.X - B.X;
		vector.Y = A.Y - B.Y;
	}

	public Vector() {

	}

	@Override
	public String toString() {
		return this.coordA.concat("-").concat(this.coordB);
	}

	@Override
	public boolean equals(Object obj) {
		Vector v = (Vector) obj;
		return this.coordA.equals(v.coordA) || this.coordB.equals(v.coordB) || this.coordA.equals(v.coordB)
				|| this.coordB.equals(v.coordA);
	}
}
