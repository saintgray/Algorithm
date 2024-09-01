package floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    1613 : 역사
//    ref url : https://www.acmicpc.net/problem/1613
public class 역사 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = 401;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        int n = Integer.parseInt(param[0]);
        int k = Integer.parseInt(param[1]);
        init(n);
        readDegrees(k);
        // topologySort();
        findShortestRoute(n);
        int s = Integer.parseInt(in.readLine());
        for (int i = 0; i < s; i++) {
            param = in.readLine().split(" ");
            int u = Integer.parseInt(param[0]);
            int v = Integer.parseInt(param[1]);
            if(dp[u][v] == INF && dp[v][u] == INF) {
                out.write("0");
            } else {
                out.write(dp[u][v] == INF ? "1" : "-1");
            }
            out.newLine();
        }
        out.flush();

    }

    static void init(int n) {
        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = INF;
            }
        }
    }

    static void readDegrees(int k) throws IOException {
        for (int i = 0; i < k; i++) {
            String[] param = in.readLine().split(" ");
            int u = Integer.parseInt(param[0]);
            int v = Integer.parseInt(param[1]);
            dp[u][v] = Math.min(dp[u][v], 1);
        }
    }

    static void findShortestRoute(int n) {
        for (int m = 1; m <= n; m++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dp[s][e] > dp[s][m] + dp[m][e]) {
                        dp[s][e] = dp[s][m] + dp[m][e];
                    }
                }
            }
        }
    }
}
