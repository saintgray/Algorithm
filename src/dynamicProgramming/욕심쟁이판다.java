package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1937 : 욕심쟁이 판다
//    ref url : https://www.acmicpc.net/problem/1937
public class 욕심쟁이판다 {
    static int N = 0;
    static int movedResult = Integer.MIN_VALUE;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Integer[][] memoization = null;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(in.readLine());
        int[][] map = new int[N][N];
        memoization = new Integer[N][N];
        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                movedResult = Math.max(movedResult, runEat(map, i, j, map[i][j]));
        System.out.println(movedResult +1);
    }


    static int runEat(int[][] map, int x, int y, int cnt) {

        if (memoization[x][y] != null)
            return memoization[x][y];

        int moveMemo = 0;
        map[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isBoundary(nextX, nextY) && map[nextX][nextY] > cnt) {
                moveMemo = Math.max(moveMemo, 1 + runEat(map, nextX, nextY, map[nextX][nextY]));
            }
        }
        map[x][y] = cnt;
        memoization[x][y] = moveMemo;
        return moveMemo;
    }


    static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
