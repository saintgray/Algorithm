package mathmatics;

// 자료 url : https://www.acmicpc.net/JudgeOnline/upload/201009/3(2).png

//    문제
//    위의 그림과 같이 육각형으로 이루어진 벌집이 있다.
//    그림에서 보는 바와 같이 중앙의 방 1부터 시작해서
//    이웃하는 방에 돌아가면서 1씩 증가하는 번호를 주소로 매길 수 있다.
//    숫자 N이 주어졌을 때, 벌집의 중앙 1에서 N번 방까지
//    최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지
//    (시작과 끝을 포함하여)를 계산하는 프로그램을 작성하시오.
//    예를 들면, 13까지는 3개, 58까지는 5개를 지난다.
//
//    입력
//    첫째 줄에 N(1 ≤ N ≤ 1,000,000,000)이 주어진다.
//
//    출력
//    입력으로 주어진 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나는지 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double X = Double.parseDouble(in.readLine());
//        0단 > 1단 > 2단 > 3단 > 4단 > 5단 : 단수이자 그 방까지 갈때까지의 최소 방문수
//        1  > 6  >  12 > 18  > 24 > 30
//
//        0~1 > (1+6) + 1 ~ (1+6+12) > (1+6+12) + 1 ~ (1+6+12+18)
//
//        숫자 X가 주어졌을때 몇단(N)의 벌집인지?
//                1 + 6(1+2+3...+N) = X
//        1+ 6( ( N*(1+N)/2 ) ) = X
//        1+ 3*N((1+N)) = X
//        N(1+N) = (X-1)/3
//        N^2 + N - (X-1)/3 = 0
//        N = ( -1 + √(1 + 4*((X-1)/3)) ) / 2
//        N = ( -1 + √(1 + 4*((X-1)/3))) ) / 2
//            >> 나머지가 있으면 N+1 단이 덜 채워진 벌집이며 나머지가 없으면 가득 찬 벌집임
//
//        출발점, 도착점을 포함한 도착지까지의 최소 방문수 = N (1번방을 제외한)
        System.out.println((1 +  4*((X-1)/3)));
        System.out.println(Math.pow((1 +  4*((X-1)/3)),(double)1/2));
        int result = (int)(Math.pow((1 +  4*((X-1)/3)),(double)1/2) / 2);
        System.out.println((Math.pow((1 +  4*((X-1)/3)),(double)1/2) / 2));
        System.out.println(result);
        boolean isFullHoneyComb = ( -1 + Math.pow((1 +  4*((X-1)/3)),(double)1/2) ) % 2 == 0;
        System.out.println(X == 1 ? 1 : isFullHoneyComb ?  result : result + 1);
        in.close();

    }
}

