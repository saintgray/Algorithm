package bitmasking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 4991 : 로봇청소기
// ref url : https://www.acmicpc.net/problem/4991
public class 로봇청소기 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static V[][] room;
    static int N, M;
    static int[] dn = new int[]{-1, 1, 0, 0};
    static int[] dm = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        String input = null;
        while ((input = in.readLine()) != null && !"0 0".equals(input)) {
            solve(input);
        }
        out.flush();
    }

    static void solve(String input) throws IOException {
        String[] param = input.split(" ");
        N = Integer.parseInt(param[1]);
        M = Integer.parseInt(param[0]);
        room = new V[N][M];
        V robot = null;
        Set<V> dirtySpots = new HashSet<>();
        for (int i = 0; i < N; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                room[i][j] = new V(i, j, row[j]);
                if (room[i][j].v == 'o') robot = room[i][j];
                if (room[i][j].v == '*') dirtySpots.add(room[i][j]);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(room[i][j].isWall()) continue;
                room[i][j].move_count = new int[(int) Math.pow(2, dirtySpots.size())];
                Arrays.fill(room[i][j].move_count, Integer.MAX_VALUE);
            }
        }

        // 더러운 칸을 모두 청소한 비트
        int full_clean_bit = ((int) Math.pow(2, dirtySpots.size())) - 1;
        // 더러운 칸의 고유 비트 번호 부여
        int bit = 0;
        for (V spot : dirtySpots) {
            spot.spot_bit = (int) Math.pow(2, bit++);
        }

        Queue<S> q = new LinkedList<>();
        q.add(new S(robot, 0, 0));
        while (!q.isEmpty()) {
            /// System.out.println(q.size());
            S status = q.poll();
            V v = status.v;
            int clean_bit = status.clean_bit;
            int move_count = status.move_count;
            for (int i = 0; i < 4; i++) {
                int _n = v.n + dn[i];
                int _m = v.m + dm[i];
                if (!isBoundary(_n, _m)) continue;
                V _v = room[_n][_m];
                if (_v.isWall()) continue;
                if(_v.isSpot() && _v.move_count[clean_bit] > move_count + 1) {
                    _v.move_count[clean_bit] = move_count + 1;
                    q.add(new S(_v, _v.spot_bit | clean_bit, move_count + 1));
                    _v.move_count[_v.spot_bit | clean_bit] = Math.min(_v.move_count[_v.spot_bit | clean_bit], move_count +1);
                    continue;
                }
                // 일반 길일 때
                if(_v.move_count[clean_bit] > move_count + 1) {
                    _v.move_count[clean_bit] = move_count + 1;
                    q.add(new S(_v, clean_bit, move_count + 1));
                }
            }
        }
        int minimun_move_count = Integer.MAX_VALUE;
        for (V spot : dirtySpots) {
            int move_count = spot.move_count[full_clean_bit];
            if (move_count == Integer.MAX_VALUE) {
                out.write("-1");
                out.newLine();
                return;
            }
            minimun_move_count = Math.min(move_count, minimun_move_count);
        }
        out.write(String.valueOf(minimun_move_count));
        out.newLine();
    }

    static boolean isBoundary(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class S {
        V v;
        int clean_bit;
        int move_count;

        public S(V v, int clean_bit, int move_count) {
            this.v = v;
            this.clean_bit = clean_bit;
            this.move_count = move_count;
        }
    }

    static class V {
        int n;
        int m;
        char v;
        int spot_bit;
        int[] move_count;   // 이 정점에 도달 시 가진 방 청소 현황 bit 별 최소 이동횟수

        public V(int n, int m, char v) {
            this.n = n;
            this.m = m;
            this.v = v;
        }

        boolean isWay() {
            return !this.isWall();
        }

        boolean isSpot() {
            return this.v == '*';
        }

        boolean isWall() {
            return this.v == 'x';
        }
    }
}