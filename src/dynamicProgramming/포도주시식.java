package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    2156 : 포도주 시식
//    ref url : https://www.acmicpc.net/problem/2156
public class 포도주시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] glass = new int[N+1];
        int[] memo = new int[N + 1];
        for(int i =1; i<=N; i++)
            glass[i] = Integer.parseInt(in.readLine());
        if (N == 1) {
            System.out.println(glass[1]);
        } else if (N == 2) {
            System.out.println(glass[1] + glass[2]);
        } else {
            int max = 0;
            // 첫번째 잔을 먹을때의 최대 마신 포도주 양 = 이전 포도주가 없으므로 memo[1] = glass[1]
            // 두번째 잔을 먹을때의 최대 마신 포도주 양 = 1번을 먹지 않았을 경우 / 먹었을 경우 => 먹었을 경우 가 더 크므로 memo[2] = glass[1] + glass[2];
            memo[1] = glass[1];
            memo[2] = glass[2] + glass[1];
            // 점화식
            // n 번째 포도주를 먹을때 최대로 마실 수 있는 포도주 양
            // 1. n-1 , n-2 번 포도주를 먹었을 경우 -> 먹지 못함 -> memo[n] = 0
            // 2. n-1 번 포도주를 먹지 않았을 경우 -> memo[n] = memo[n-2] + glass[n]
            // 3. n-1 번 포도주를 먹었을 경우 -> memo[n] = memo[n-3] + glass[n-1] + glass[n]
            // 4. n-2, n-2 포도주 모두 먹지 않았을 경우 -> memo[n] = Math.max(memo[n], memo[n-1])
            // 1,2,3 중 최대값이 memo[n] 의 값이다
            for (int i = 3; i <= N; i++) {
                int calc1  =memo[i - 2] + glass[i];
                int calc2 = memo[i - 3] + glass[i - 1] + glass[i];
                memo[i] = Math.max(calc1, calc2);
                memo[i] = Math.max(memo[i], memo[i - 1]);
                max = Math.max(max, memo[i]);
            }
            System.out.println(memo[N]);
        }
    }
}
