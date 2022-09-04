package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    9095 : 1, 2, 3 더하기
//
//    문제
//    정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다.
//    합을 나타낼 때는 수를 1개 이상 사용해야 한다.
//    1+1+1+1
//    1+1+2
//    1+2+1
//    2+1+1
//    2+2
//    1+3
//    3+1
//    정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 테스트 케이스의 개수 T가 주어진다.
//    각 테스트 케이스는 한 줄로 이루어져 있고,
//    정수 n이 주어진다. n은 양수이며 11보다 작다.
//
//    출력
//    각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.

public class 일이삼더하기 {
    static int cases = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(in.readLine());
        for (int i = 0; i < testCases; i++) {
            int n = Integer.parseInt(in.readLine());
            Stack<Integer> hist = new Stack<>();
            run(n, hist);
            System.out.println(cases);
            cases = 0;
        }
        in.close();
    }

    static void run(int n, Stack<Integer> hist) {
        int sum = sum(hist);
        if (sum == n) {
            cases++;
            return;
        } else {
            for (int i = 1; i <= 3; i++) {
                if (sum + i <= n) {
                    hist.push(i);
                    run(n, hist);
                    hist.pop();
                }
            }
        }
    }

    static int sum(Stack<Integer> hist) {
        int sum = 0;
        for (int n : hist)
            sum += n;
        return sum;
    }
}
