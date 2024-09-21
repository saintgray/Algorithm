package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    14442 : 벽 부수고 이동하기 2
//    ref url : https://www.acmicpc.net/problem/14442
public class 벽부수고이동하기2 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C, K;
    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};
    static V[][] map;

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        R = Integer.parseInt(params[0]);
        C = Integer.parseInt(params[1]);
        K = Integer.parseInt(params[2]);
        map = new V[R][C];
        for (int i = 0; i < R; i++) {
            int[] row = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = new V(i, j, row[j]);
            }
        }
        map[0][0].d[0] = 0;
        Queue<S> status = new LinkedList<>();
        status.add(new S(0, 0, 0, 0));
        while (!status.isEmpty()) {
            S s = status.poll();
            for (int k = 0; k < 4; k++) {
                int _r = s.r + dr[k];
                int _c = s.c + dc[k];
                if (!isBoundary(_r, _c)) continue;
                if (map[_r][_c].v == 1) {
                    if (s.brokenWall == K) continue;
                    if (s.d + 1 < map[_r][_c].d[s.brokenWall + 1]) {
                        map[_r][_c].d[s.brokenWall + 1] = s.d + 1;
                        status.add(new S(_r, _c, s.d + 1, s.brokenWall + 1));
                    }
                } else {
                    if (s.d + 1 < map[_r][_c].d[s.brokenWall]) {
                        map[_r][_c].d[s.brokenWall] = s.d + 1;
                        status.add(new S(_r, _c, s.d + 1, s.brokenWall));
                    }
                }
            }
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            shortest = Math.min(shortest, map[R - 1][C - 1].d[i]);
        }
        System.out.println(shortest == Integer.MAX_VALUE ? "-1" : shortest + 1);
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class V {
        int r;
        int c;
        int v;
        int[] d;

        public V(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
            this.d = new int[K + 1];
            Arrays.fill(this.d, Integer.MAX_VALUE);
        }
    }

    static class S {
        int r;
        int c;
        int d;
        int brokenWall;

        public S(int r, int c, int d, int brokenWall) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.brokenWall = brokenWall;
        }
    }

}
