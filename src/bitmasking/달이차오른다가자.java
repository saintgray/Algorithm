package bitmasking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 1194 : 달이 차오른다, 가자.
// ref : https://www.acmicpc.net/problem/1194
public class 달이차오른다가자 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static V[][] maze;
    static int N, M;
    static int[] dn = new int[]{-1, 1, 0, 0};
    static int[] dm = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        N = Integer.parseInt(param[0]);
        M = Integer.parseInt(param[1]);
        maze = new V[N][M];
        V minsik = null;
        Set<V> exits = new HashSet<>();
        for (int i = 0; i < N; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = new V(i, j, row[j]);
                if (maze[i][j].v == '0') minsik = maze[i][j];
                if (maze[i][j].v == '1') exits.add(maze[i][j]);
            }
        }
        Queue<S> q = new LinkedList<>();
        q.add(new S(minsik, 0, 0));
        while (!q.isEmpty()) {
            S status = q.poll();
            V v = status.v;
            int key_bit = status.key_bit;
            int move_count = status.move_count;
            for (int i = 0; i < 4; i++) {
                int _n = v.n + dn[i];
                int _m = v.m + dm[i];
                if (!isBoundary(_n, _m)) continue;
                V _v = maze[_n][_m];
                if (_v.isWall()) continue;
                // 1. 이동하려는 정점이 빈칸일 때
                if (_v.isWay()) {
                    // 방문하지 않은 곳이면 이동
                    if (_v.move_count[key_bit] == 0) {
                        _v.move_count[key_bit] = move_count + 1;
                        q.add(new S(_v, key_bit, move_count + 1));
                    }
                }
                // 2. 이동하려는 정점이 문일 때
                if (_v.isDoor()) {
                    // 열쇠가 있고 방문하지 않은 곳일 때 이동
                    if ((Integer.bitCount(key_bit & _v.bit())) == 1 && _v.move_count[key_bit] == 0) {
                        _v.move_count[key_bit] = move_count + 1;
                        q.add(new S(_v, key_bit, move_count + 1));
                    }
                }
                // 3. 이동하려는 정점이 열쇠일 때
                if (_v.isKey()) {
                    // 방문하지 않은 곳일 시 key_bit 추가 후 이동
                    if (_v.move_count[key_bit] == 0) {
                        _v.move_count[key_bit] = move_count + 1;
                        q.add(new S(_v, key_bit | _v.bit(), move_count + 1));
                    }
                }
            }
        }

        int minimum_move_count_to_exit = Integer.MAX_VALUE;
        for (V exit : exits) {
            for (int i = 0; i < exit.move_count.length; i++) {
                if (exit.move_count[i] == 0) continue;
                minimum_move_count_to_exit = Math.min(minimum_move_count_to_exit, exit.move_count[i]);
            }
        }
        System.out.println(minimum_move_count_to_exit == Integer.MAX_VALUE ? "-1" : minimum_move_count_to_exit);
    }

    static boolean isBoundary(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class S {
        V v;
        int key_bit;
        int move_count;

        public S(V v, int key_bit, int move_count) {
            this.v = v;
            this.key_bit = key_bit;
            this.move_count = move_count;
        }
    }

    static class V {
        int n;
        int m;
        char v;
        int[] move_count;   // 이 정점에 도달 시 가진 열쇠 현황에 따른 최소 이동횟수

        public V(int n, int m, char v) {
            this.n = n;
            this.m = m;
            this.v = v;
            if (!this.isWall()) this.move_count = new int[64]; // 가진 열쇠 비트 조합은 0 ~ (64-1) 까지 가능
        }

        boolean isWay() {
            return this.v == '.' || this.v == '0' || this.v == '1';
        }

        boolean isWall() {
            return this.v == '#';
        }

        boolean isDoor() {
            int ascii = (int) this.v;
            return 65 <= ascii && ascii <= 70;
        }

        boolean isKey() {
            int ascii = (int) this.v;
            return 97 <= ascii && ascii <= 102;
        }

        int bit() {
            return this.isKey() ? (int) Math.pow(2, (5 - (((int) this.v) - 97))) : (int) Math.pow(2, (5 - (((int) this.v) - 65)));
        }
    }

}