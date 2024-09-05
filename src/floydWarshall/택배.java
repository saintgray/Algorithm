package floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    1719 : 택배
//    ref url : https://www.acmicpc.net/problem/1719
public class 택배 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = (1000 * 199) + 1;

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        int n = Integer.parseInt(params[0]);    // max 200
        int m = Integer.parseInt(params[1]);    // max 10000
        Integer[][] parent = new Integer[n + 1][n + 1];
        int[][] time = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) time[i][j] = 0;
                else time[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            params = in.readLine().split(" ");
            int st = Integer.parseInt(params[0]);
            int ed = Integer.parseInt(params[1]);
            int _time = Integer.parseInt(params[2]);
            if (time[st][ed] > _time) {
                time[st][ed] = _time;
                time[ed][st] = _time;
                parent[st][ed] = ed;
                parent[ed][st] = st;
            }
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int st = 1; st <= n; st++) {
                for (int ed = 1; ed <= n; ed++) {
                    int d = time[st][mid] + time[mid][ed];
                    if (time[st][ed] > d) {
                        time[st][ed] = d;
                        parent[st][ed] = mid;
                        int j = mid;
                        // 최초 출발 집하장 역추적
                        while (parent[st][j] != null && parent[st][j] != j) {
                            parent[st][ed] = parent[st][j];
                            j = parent[st][j];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.write(i == j ? "- " : parent[i][j] + " ");
            }
            out.newLine();
        }
        out.flush();
    }
}
