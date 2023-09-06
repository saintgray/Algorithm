package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    17090 : 미로 탈출하기
//    ref url : https://www.acmicpc.net/problem/17090
public class 미로탈출하기 {

    static int R = 0;
    static int C = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final String UP =  "U";
    static final String DOWN =  "D";
    static final String LEFT =  "L";
    static final String RIGHT =  "R";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = params[0];
        C = params[1];
        String[][] map = new String[R][C];
        boolean[][] escapeable = new boolean[R][C];
        boolean[][] checked = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = in.readLine().split("");
            for (int c = 0; c < C; c++) {
                if (canEscape(r, c, map[r][c])) {
                    escapeable[r][c] = true;
                    checked[r][c] = true;
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (escapeable[r][c]) {
                    queue.add(new Node(r, c));
                    while (!queue.isEmpty()) {
                        Node node = queue.poll();
                        for (int k = 0; k < 4; k++) { // 상, 하, 좌, 우
                            int _dr = node.r + dr[k];
                            int _dc = node.c + dc[k];
                            if (isBoundary(_dr, _dc) && !checked[_dr][_dc]) {
                                if ((k == 0 && DOWN.equals(map[_dr][_dc])) ||
                                        (k == 1 && UP.equals(map[_dr][_dc])) ||
                                        (k == 2 && RIGHT.equals(map[_dr][_dc])) ||
                                        (k == 3 && LEFT.equals(map[_dr][_dc]))) {
                                    escapeable[_dr][_dc] = true;
                                    checked[_dr][_dc] = true;
                                    queue.add(new Node(_dr, _dc));
                                }
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (boolean[] row : escapeable) {
            for (boolean canEscape : row) {
                result += canEscape ? 1 : 0;
            }
        }
        System.out.println(result);
    }

    static boolean isBoundary(int _r, int _c) {
        return _r >= 0 && _r < R && _c >= 0 && _c < C;
    }

    static boolean canEscape(int r, int c, String command) {
        return (UP.equals(command) && r - 1 < 0) ||
                (DOWN.equals(command) && r + 1 == R) ||
                (LEFT.equals(command) && c - 1 < 0) ||
                (RIGHT.equals(command) && c + 1 == C);
    }


    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
