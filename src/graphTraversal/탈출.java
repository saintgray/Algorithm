package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    3055 : 탈출
//    ref url : https://www.acmicpc.net/problem/3055
public class 탈출 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static Node[][] map;
    static int R;
    static int C;
    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        R = Integer.parseInt(param[0]);
        C = Integer.parseInt(param[1]);
        map = new Node[R][C];

        Node start = null;
        Node cave = null;
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = new Node(i, j, row[j]);
                if (map[i][j].v == 'S') start = map[i][j];
                if (map[i][j].v == 'D') cave = map[i][j];
                if (map[i][j].isWater()) {
                    q.add(map[i][j]);
                } else {
                    map[i][j].time_water = Integer.MAX_VALUE;
                }
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int k = 0; k < 4; k++) {
                int _r = node.r + dr[k];
                int _c = node.c + dc[k];
                if (!isBoundary(_r, _c)) continue;
                Node next = map[_r][_c];
                if (!next.checked && !next.isCave() && !next.isRock()) {
                    next.checked = true;
                    next.time_water = node.time_water + 1;
                    q.add(next);
                }
            }
        }
        Arrays.stream(map).flatMap(Arrays::stream).forEach(node -> node.checked = false);
        q.add(start);
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int k = 0; k < 4; k++) {
                int _r = node.r + dr[k];
                int _c = node.c + dc[k];
                if (!isBoundary(_r, _c)) continue;
                Node next = map[_r][_c];
                if (!next.checked && !next.isRock() && (next.isCave() || next.time_water > node.time_run + 1)) {
                    next.checked = true;
                    next.time_run = node.time_run + 1;
                    q.add(next);
                }
            }
        }
        System.out.println(cave.time_run == 0 ? "KAKTUS" : cave.time_run);
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Node {
        int r;
        int c;
        char v;
        boolean checked;
        int time_water; // 물이 r,c 지점까지 도달하기까지의 시간
        int time_run;   // 비버가 r,c 지점까지 도달하기까지의 시간

        boolean isCave() {
            return this.v == 'D';
        }

        boolean isRock() {
            return this.v == 'X';
        }

        boolean isWater() {
            return this.v == '*';
        }

        public Node(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}


