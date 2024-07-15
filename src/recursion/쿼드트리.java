package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1992 : 쿼드트리
//    ref url : https://www.acmicpc.net/problem/1992
public class 쿼드트리 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] screen;
    static int[][] zeroSum;
    static int[][] diagnoalZeroSum;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        screen = new int[N][N];
        diagnoalZeroSum = new int[N][N];
        zeroSum = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                screen[i][j] = row[j];
                zeroSum[i][j] = j == 0 ? (row[j] == 0 ? 1 : 0) : zeroSum[i][j - 1] + (row[j] == 0 ? 1 : 0);
                diagnoalZeroSum[i][j] = i == 0 ? zeroSum[i][j] : diagnoalZeroSum[i - 1][j] + zeroSum[i][j];
            }
        }
        compact(0, N - 1, 0, N - 1);
        out.flush();
    }

    static void compact(int r1, int r2, int c1, int c2) throws IOException {
        if (r1 == r2 && c1 == c2) {
            out.write(String.valueOf(screen[r1][c1]));
            return;
        }
        int a = (c1 == 0) ? 0 : diagnoalZeroSum[r2][c1 - 1];
        int b = (r1 == 0) ? 0 : diagnoalZeroSum[r1 - 1][c2];
        int c = (r1 == 0 || c1 == 0) ? 0 : diagnoalZeroSum[r1 - 1][c1 - 1];
        int zeroSum = diagnoalZeroSum[r2][c2] - (a + b) + c;
        if (zeroSum == 0) {
            out.write("1");
        } else if (zeroSum != (c2 - c1 + 1) * (r2 - r1 + 1)) {
            out.write("(");
            compact(r1, (r1 + r2) / 2, c1, (c1 + c2) / 2);  // 좌상
            compact(r1, (r1 + r2) / 2, ((c1 + c2) / 2) + 1, c2);  // 우상
            compact(((r1 + r2) / 2) + 1, r2, c1, (c1 + c2) / 2);  // 좌하
            compact(((r1 + r2) / 2) + 1, r2, ((c1 + c2) / 2) + 1, c2);  // 우하
            out.write(")");
        } else {
            out.write("0");
        }
    }
}