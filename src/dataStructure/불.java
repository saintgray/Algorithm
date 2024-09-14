package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//	4179 : 불!
//	ref url : https://www.acmicpc.net/problem/4179
public class 불 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int R;
    static int C;
    static int[] dr = new int[]{0, 0, 1, -1};
    static int[] dc = new int[]{1, -1, 0, 0};

    static V[][] map = new V[R][C];

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        R = Integer.parseInt(param[0]);
        C = Integer.parseInt(param[1]);
        map = new V[R][C];
        V jihoon = null;
        Queue<V> fires = new LinkedList<>();
        Set<V> exits = new HashSet<>();
        for (int r = 0; r < R; r++) {
            char[] chars = in.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                V v = new V(r, c, chars[c]);
                if (v.v == 'J') {
                    v.dj = 0;
                    jihoon = v;
                }
                if (v.v == 'F') {
                    v.df = 0;
                    fires.add(v);
                }
                if (v.isExit()) exits.add(v);
                map[r][c] = v;
            }
        }
        // 탈출구가 없는 경우
        if (exits.size() == 0) {
            System.out.println("IMPOSSIBLE");
            System.exit(0);
        }

        while (!fires.isEmpty()) {
            V poll = fires.poll();
            for (int i = 0; i < 4; i++) {
                int _r = poll.r + dr[i];
                int _c = poll.c + dc[i];
                if (!isBoundary(_r, _c) || map[_r][_c].isWall() || map[_r][_c].visited) continue;
                if (poll.df + 1 >= map[_r][_c].df) continue;
                map[_r][_c].visited = true;
                map[_r][_c].df = poll.df + 1;
                fires.add(map[_r][_c]);
            }
        }
        Arrays.stream(map).flatMap(Arrays::stream).forEach(v -> v.visited = false);


        Queue<V> queue = new LinkedList<>();
        jihoon.visited = true;
        queue.add(jihoon);
        while (!queue.isEmpty()) {
            V poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int _r = poll.r + dr[i];
                int _c = poll.c + dc[i];
                if (!isBoundary(_r, _c) || map[_r][_c].isWall() || map[_r][_c].visited) continue;
                if (map[_r][_c].df <= poll.dj + 1) continue;
                map[_r][_c].visited = true;
                map[_r][_c].dj = poll.dj + 1;
                queue.add(map[_r][_c]);
            }
        }

        // 지훈이가 불보다 더 빨리 도착한 탈출구를 찾는다
        int time = Integer.MAX_VALUE;
        for (V exit : exits) {
            if (exit.dj < exit.df) {
                time = Math.min(time, exit.dj + 1);
            }
        }
        System.out.println(time == Integer.MAX_VALUE ? "IMPOSSIBLE" : time);
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class V {
        int r;
        int c;
        char v;
        boolean visited;
        int dj; // 지훈이가 이 정점에 도달하기까지 최단 시간
        int df; // 불이 이 정점에 도달하기 까지 최단 시간

        boolean isWall() {
            return this.v == '#';
        }

        boolean isExit() {
            return !this.isWall() && (r == 0 || r == R - 1 || c == 0 || c == C - 1);
        }

        public V(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
            this.df = Integer.MAX_VALUE;
            this.dj = Integer.MAX_VALUE;
        }
    }
}
