package floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    11404 : 플로이드
//    ref url : https://www.acmicpc.net/problem/11404
public class 플로이드 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp;  // 정점 i 에서 j 까지 가는 최단거리
    static int INF = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        dp = new int[n][n];
        INF = ((n-1)*100000) + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = i == j ? 0 : INF;
            }
        }
        for (int i = 0; i < M; i++) {
            int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp[p[0] - 1][p[1] - 1] = Math.min(dp[p[0] - 1][p[1] - 1], p[2]);
        }
        for (int m = 0; m < n; m++)
            for (int s = 0; s < n; s++)
                for (int e = 0; e < n; e++)
                    if (dp[s][e] > dp[s][m] + dp[m][e])
                        dp[s][e] = dp[s][m] + dp[m][e];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.write((dp[i][j] == INF ? 0 : dp[i][j]) + " ");
            }
            out.newLine();
        }
        out.flush();
    }
}