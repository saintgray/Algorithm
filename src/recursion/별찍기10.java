package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    2447 : 별찍기10
//    ref url : https://www.acmicpc.net/problem/2447
public class 별찍기10 {
    static final String BLANK = " ";
    static final String STAR = "*";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        String[][] paper = new String[N][N];
        run(paper, 0, N, 0, N, false);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                out.write(paper[i][j]);
            }
            out.newLine();
        }
        out.flush();
    }

    public static void run(String[][] paper, int r1, int r2, int c1, int c2, boolean isBlankArea) {
        if (isBlankArea || Math.abs(r2 - r1) == 3) {
            print(paper, r1, r2, c1, c2, isBlankArea);
            return;
        }
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                if (paper[r][c] == null) {
                    int r3 = r1 + ((r2 - r1) / 3);
                    int r4 = r1 + 2 * ((r2 - r1) / 3);
                    int c3 = c1 + ((c2 - c1) / 3);
                    int c4 = c1 + 2 * ((c2 - c1) / 3);
                    int[] dr1 = {r1, r1, r1, r3, r3, r3, r4, r4, r4};
                    int[] dr2 = {r3, r3, r3, r4, r4, r4, r2, r2, r2};
                    int[] dc1 = {c1, c3, c4, c1, c3, c4, c1, c3, c4};
                    int[] dc2 = {c3, c4, c2, c3, c4, c2, c3, c4, c2};
                    for (int k = 0; k < 9; k++) {
                        boolean blankArea = k == 4;
                        // recursion
                        run(paper, dr1[k], dr2[k], dc1[k], dc2[k], blankArea);
                    }
                }
            }
        }
    }

    public static void print(String[][] paper, int r1, int r2, int c1, int c2, boolean isBlankArea) {
        if (isBlankArea) {
            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    paper[r][c] = BLANK;
                }
            }
        } else {
            // size = 3
            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    paper[r][c] = (r2 + r1) / 2 == r && (c2 + c1) / 2 == c ? BLANK : STAR;
                }
            }
        }
    }
}
