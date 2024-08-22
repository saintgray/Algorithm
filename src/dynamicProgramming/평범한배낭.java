package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//    12865 : 평범한 배낭
//    ref url : https://www.acmicpc.net/problem/12865
//    ref alg : knapsack problem
public class 평범한배낭 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String[] params = in.readLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int K = Integer.parseInt(params[1]);
        Stuff[] stuffs = new Stuff[N];
        for (int i = 0; i < N; i++) {
            stuffs[i] = new Stuff(in.readLine().split(" "));
        }
        Arrays.sort(stuffs, Comparator.comparing(stuff -> stuff.w));
        int[][] weight = new int[K + 1][N + 1];
        int[][] value = new int[K + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            // n 번째 물건을 대상으로
            Stuff s = stuffs[n - 1];
            // 최대 수용 가능 무게를 1kg 부터 K kg 까지 탐색하면서
            for (int k = 1; k <= K; k++) {
                if (k >= s.w) {
                    // 1. n-1 개 물건까지 넣었을 때 (k - 현재 물건 무게) 까지 채웠을 때 가치 최적값 + 현재 물건 가치
                    // 2. n-1 개 물건까지 넣었을때 가치 최적값
                    // 1,2 중 큰 값을 n번째 물건까지 넣었을 때 최적값으로 할당한다.
                    value[k][n] = Math.max(s.v + value[k - s.w][n - 1], value[k][n - 1]);
                } else {
                    value[k][n] = value[k][n - 1];
                }
            }
        }
        System.out.println(value[K][N]);
    }

    static class Stuff {
        int w;
        int v;
        public Stuff(String[] input) {
            this.w = Integer.parseInt(input[0]);
            this.v = Integer.parseInt(input[1]);
        }
    }

}