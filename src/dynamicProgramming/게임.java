package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1103 : 게임
//    ref url : https://www.acmicpc.net/problem/1103
public class 게임 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static Integer[][] dp;
    static final int[] dn = new int[]{0, 0, -1, 1};
    static final int[] dm = new int[]{-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        N = Integer.parseInt(param[0]);
        M = Integer.parseInt(param[1]);
        map = new char[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        dp = new Integer[N+1][M+1];

        for (int i = 0; i < N; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = row[j];
                map[i + 1][j + 1] = c;
            }
        }
        visited[1][1] = true;
        track(1, 1);
        System.out.println(dp[1][1] + 1);
    }

    /**
     * track
     * @param n 행
     * @param m 열
     * @return n,m 지점에서 움질일 수 있는 최대 횟수
     */
    static int track(int n, int m) {
        int weight = Integer.parseInt(String.valueOf(map[n][m]));
        for (int k = 0; k < 4; k++) {
            int _n = n + weight * dn[k];
            int _m = m + weight * dm[k];
            if (!isBoundary(_n, _m) || map[_n][_m] == 'H') continue;
            // 왔던 길을 다시 오는 경우
            if (visited[_n][_m]) {
                System.out.println("-1");
                System.exit(0);
            }
            visited[_n][_m] = true;
            if (dp[_n][_m] != null) {
                if(dp[n][m] == null) dp[n][m] = dp[_n][_m] + 1;
                else dp[n][m] = Math.max(dp[n][m], dp[_n][_m] + 1);
                visited[_n][_m] = false;
            } else {
                int count = track(_n, _m) + 1;
                if(dp[n][m] == null) dp[n][m] = count;
                else dp[n][m] = Math.max(dp[n][m], count);
                visited[_n][_m] = false;
            }
        }
        if (dp[n][m] == null) return dp[n][m] = 0; // 4 방향 모두 갈 곳이 없는 경우
        return dp[n][m];
    }

    static boolean isBoundary(int n, int m) {
        return n >= 1 && m >= 1 && n <= N && m <= M;
    }
}
