package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1799 : 비숍
// https://www.acmicpc.net/problem/1799
public class 비숍 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int whiteBishopMax;
    static int blackBishopMax;
    static Node[][] map;
    static boolean[] occupied;
    static boolean[] revOccupied;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        map = new Node[N][N];
        occupied = new boolean[2*N+1];
        revOccupied = new boolean[2*N+1];
        for (int i = 0; i < N; i++) {
            int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = new Node(i, j, p[j]);
                map[i][j].color =
                        (i == 0 && j == 0) ?
                                'W' :
                                i == 0 ?
                                        (map[i][j - 1].getConverseColor()) : map[i - 1][j].getConverseColor();
            }
        }
        // 체스 말 중 하나인 비숍의 특징은 놓인 칸의 색깔의 반대 색은 절대 공격할 수 없다는 특징이 있으므로
        // 전체 체스판에 서로 공격할 수 없도록 비숍을 놓는 경우의 수는
        // 흰색 칸에 대해 서로 공격할 수 없도록 놓는 최대 비숍 수 + 검은색 칸에 대해 서로 공격할 수 없도록 놓는 최대 비숍 수의 합입을 알 수 있다.
        // 즉, 체스판을 분할하는 것이 백트래킹 탐색의 첫번째 가지치기이며
        // 대각선 라인으로 비숍이 공격할 수 있는 점유 여부를 보고
        // 탐색하는 칸이 공격당하는 위치일 시 제외하는 것이 두번째 가지치기이다 (hasOccupied)
        track(0, 0, 0, 'W');
        if(N > 1) track(0, 1, 0, 'B');
        System.out.println(whiteBishopMax + blackBishopMax);
    }

    static void track(int r, int c, int bishops, char target) {
        for (int i = r; i < N; i++) {
            for (int j = (i == r ? c : 0); j < N; j++) {
                if (map[i][j].isWall() || map[i][j].color != target || map[i][j].hasOccupied()) continue;
                occupied[i+j] = true;
                revOccupied[i + (N-j)] = true;
                if(target == 'W') whiteBishopMax = Math.max(whiteBishopMax, bishops +1);
                if(target == 'B') blackBishopMax = Math.max(blackBishopMax, bishops+1);
                track(i, j, bishops + 1, target);
                occupied[i+j] = false;
                revOccupied[i + (N-j)] = false;
            }
        }
    }

    static class Node {
        int r;
        int c;
        char color;
        int v;

        public Node(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }

        boolean isWall() {
            return this.v == 0;
        }

        boolean hasOccupied() {
            // return reverseOccupied.get(this.reverseDiagnoalHash) || occupied.get(this.diagnoalHash);
            return occupied[this.r + this.c] || revOccupied[this.r + (N-this.c)];
        }

        char getConverseColor() {
            return this.color == 'W' ? 'B' : 'W';
        }
    }

}
