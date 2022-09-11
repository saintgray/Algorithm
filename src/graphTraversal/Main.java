package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1520 : 내리막길
//    ref url : https://www.acmicpc.net/problem/1520
//    TLE

public class Main {
    static int cases = 0;
    static int M = 0;
    static int N = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        M = param[0];
        N = param[1];
        int[][] map = new int[N][M];
        for (int i = 0; i < M; i++)
            map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        run(map, 0, 0);
        System.out.println(cases);
        in.close();
    }

    static void run(int[][] map, int i, int j) {
        if (i == M - 1 && j == N - 1) {
            cases++;
            return;
        }
        for (int n = 0; n < 4; n++) {
            int moveX = j + dx[n];
            int moveY = i + dy[n];
            if (isBoundary(moveX, moveY) && map[moveY][moveX] < map[i][j]) {
                run(map, moveY, moveX);
            }
        }
    }

    static boolean isBoundary(int dx, int dy) {
        return dx >= 0 && dy >= 0 && dx < N && dy < M;
    }
}
