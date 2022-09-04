package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//    1463 : 1로 만들기
//
//    문제
//    정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
//
//    X가 3으로 나누어 떨어지면, 3으로 나눈다.
//    X가 2로 나누어 떨어지면, 2로 나눈다.
//    1을 뺀다.
//    정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
//    연산을 사용하는 횟수의 최솟값을 출력하시오.
//
//    입력
//    첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
//
//    출력
//    첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

public class 일로만들기 {
    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(in.readLine());
        int[] calc = new int[X];
        findShortestCalc(calc);
        System.out.println(calc[0]-1);
        in.close();
    }

    static void findShortestCalc(int[] calc) {

        Queue<Integer> queue = new LinkedList<>();
        calc[X - 1] = 1;
        queue.add(X);
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (n % 3 == 0 && (n / 3) - 1 >= 0 && calc[(n / 3) - 1] == 0) {
                calc[(n / 3) - 1] = calc[n - 1] + 1;
                queue.add(n / 3);
            }
            if (n % 2 == 0 && (n / 2) - 1 >= 0 && calc[(n / 2) - 1] == 0) {
                calc[(n / 2) - 1] = calc[n - 1] + 1;
                queue.add(n / 2);
            }
            if (n - 2 >= 0 && calc[n - 2] == 0) {
                calc[(n - 2)] = calc[n - 1] + 1;
                queue.add(n - 1);
            }
            if (calc[0] != 0)
                return;
        }
    }
}
