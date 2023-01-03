package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    200002 : 사과나무
//    ref url : https://www.acmicpc.net/problem/200002
public class 사과나무 {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[][] farm = new int[N][N];
        int[][] sum = new int[N][N];

        for (int i = 0; i < N; i++) {
            // 농장
            farm[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 누적합
            for (int j = 0; j < N; j++)
                sum[i][j] = j == 0 ? farm[i][j] : sum[i][j - 1] + farm[i][j];
        }
        // brute force
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N - k; i++) {
                for (int j = 0; j < N - k; j++) {
                    // i행부터 i+k행까지 j+k 열의 누적합 - j+k 전 열까지의 누적합 = 총 순이익
                    int a = 0;
                    int b = 0;
                    for (int p = i; p <= i + k; p++) {
                        a += sum[p][j+k];
                        b += j == 0 ? 0 : sum[p][j - 1];
                    }
                    int result = a - b;
                    max = Math.max(result, max);
                }
            }
        }
        System.out.println(max);
    }
}
