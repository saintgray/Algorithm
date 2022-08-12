package Q1_100;
//조규현과 백승환은 터렛에 근무하는 직원이다. 하지만 워낙 존재감이 없어서 인구수는 차지하지 않는다.

//
//이석원은 조규현과 백승환에게 상대편 마린(류재명)의 위치를 계산하라는 명령을 내렸다. 
//조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.
//
//조규현의 좌표 (x1, y1)와 백승환의 좌표 (x2, y2)가 주어지고, 
//조규현이 계산한 류재명과의 거리 r1과 백승환이 계산한 류재명과의 거리 r2가 주어졌을 때, 
//류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.
//만약 류재명이 있을 수 있는 위치의 개수가 무한대일 경우에는 -1을 출력한다.

import java.util.Random;

//x1, y1, x2, y2는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이고, r1, r2는 10,000보다 작거나 같은 자연수이다.

public class Q3_Turret {

	public static void main(String[] args) {

		Random r = new Random();

		int x1 = (r.nextInt(20001) * -1) + 10000;
		int y1 = (r.nextInt(20001) * -1) + 10000;
		int x2 = (r.nextInt(20001) * -1) + 10000;
		int y2 = (r.nextInt(20001) * -1) + 10000;

		int r1 = r.nextInt(10000) + 1;
		int r2 = r.nextInt(10000) + 1;

		// d 는 조규환과 백승환 사이의 거리이다.
		int d = (int)(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

		// 생성된 난수 x1 ~ d는 다음의 관계를 만족해야한다.
		// 1. 두 터렛에서 마린의 위치가 관측되어야 하기 때문에 두 터렛사이의 거리는 r1 + r2 보다는 같거나 작아야한다.

	
		int T = 0;

		if (x1 == x2 && y1 == y2 && r1==r2) {
			T = -1;
		} else if (d-Math.pow(r1, 2) == Math.pow(r2, 2)) {
			T = 1;
		} else if (d-Math.pow(r1, 2) > Math.pow(r2, 2)) {
			T =0;
		}else {
			T =2;
		}

		System.out.println(T);

	}

}