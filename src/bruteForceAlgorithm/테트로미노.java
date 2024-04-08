package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

//    14500 : 감소하는 수
//    ref url : https://www.acmicpc.net/problem/14500
public class 테트로미노 {
    static int N = 0;
    static int M = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = params[0];
        M = params[1];
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        int[] cd1 = new int[]{0, 0};
        int[] cd2 = new int[]{0, 1};
        int[] cd3 = new int[]{1, 0};
        int[] cd4 = new int[]{1, 1};

        int[] cd5 = new int[]{2, 0};
        int[] cd6 = new int[]{2, 1};
        int[] cd7 = new int[]{0, 2};
        int[] cd8 = new int[]{1, 2};

        int[] cd9 = new int[]{0, 3};
        int[] cd10 = new int[]{3, 0};
        List<Tetromino> list = Arrays.asList(
                new Tetromino(cd1, cd2, cd3, cd4), // ㅁ

                new Tetromino(cd1, cd3, cd5, cd10), // |
                new Tetromino(cd1, cd2, cd7, cd9), // ㅡ

                new Tetromino(cd2, cd3, cd4, cd8), // ㅗ
                new Tetromino(cd1, cd3, cd5, cd4), // ㅏ
                new Tetromino(cd2, cd4, cd6, cd3), // ㅓ
                new Tetromino(cd1, cd2, cd7, cd4), // ㅜ

                new Tetromino(cd1, cd3, cd4, cd6), // ┖┓
                new Tetromino(cd3, cd4, cd2, cd7),
                new Tetromino(cd2, cd4, cd3, cd5),// ┏┛
                new Tetromino(cd1, cd2, cd4, cd8),

                new Tetromino(cd1, cd3, cd5, cd6),
                new Tetromino(cd1, cd3, cd2, cd7),
                new Tetromino(cd1, cd2, cd4, cd6),
                new Tetromino(cd3, cd4, cd8, cd7),

                new Tetromino(cd2, cd4, cd6, cd5),
                new Tetromino(cd1, cd3, cd4, cd8),
                new Tetromino(cd1, cd3, cd5, cd2),
                new Tetromino(cd1, cd2, cd7, cd8)
        );

        int result = Integer.MIN_VALUE;
        for (Tetromino t : list) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    // 기준 좌표 변화량
                    int dy = y - t.ay;
                    int dx = x - t.ax;
                    if (isBoundary(t.ax + dx, t.ay + dy,
                            t.bx + dx, t.by + dy,
                            t.cx + dx, t.cy + dy,
                            t.dx + dx, t.dy + dy)) {
                        t.ax = t.ax + dx;
                        t.ay = t.ay + dy;
                        t.bx = t.bx + dx;
                        t.by = t.by + dy;
                        t.cx = t.cx + dx;
                        t.cy = t.cy + dy;
                        t.dx = t.dx + dx;
                        t.dy = t.dy + dy;
                        result = Math.max(result, getSum(t, map));
                    }
                }
            }
        }
        System.out.println(result);
    }


    static class Tetromino {
        int ax;
        int ay;
        int bx;
        int by;
        int cx;
        int cy;
        int dx;
        int dy;

        public Tetromino(int[]... coords) {
            int[] aCoord = coords[0];
            int[] bCoord = coords[1];
            int[] cCoord = coords[2];
            int[] dCoord = coords[3];
            this.ax = aCoord[1];
            this.ay = aCoord[0];
            this.bx = bCoord[1];
            this.by = bCoord[0];
            this.cx = cCoord[1];
            this.cy = cCoord[0];
            this.dx = dCoord[1];
            this.dy = dCoord[0];
        }
    }

    static boolean isBoundary(int ax, int ay, int bx, int by, int cx, int cy, int dx, int dy) {
        return ax >= 0 && ay >= 0 && bx >= 0 && by >= 0 && cx >= 0 & cy >= 0 && dx >= 0 && dy >= 0 &&
                ax < M && ay < N && bx < M && by < N && cx < M && cy < N && dx < M && dy < N;
    }

    static int getSum(Tetromino t, int[][] map) {
        return map[t.ay][t.ax] + map[t.by][t.bx] + map[t.cy][t.cx] + map[t.dy][t.dx];
    }
}
