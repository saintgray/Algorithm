package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1780 : 종이의 개수
//    ref url : https://www.acmicpc.net/problem/1780
public class 종이의개수 {
    static int N = 0;
    static int group = 1;
    static int papers1 = 0;
    static int papers2 = 0;
    static int papers3 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(in.readLine());
        int[][] paper = new int[N][N];
        int[][] groups = new int[N][N];
        for (int i = 0; i < N; i++) {
            paper[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        run(paper, groups, 0, N, 0, N);
        System.out.printf("%s\n%s\n%s\n%n",
                papers1,
                papers2,
                papers3
        );
    }

    public static void run(int[][] paper, int[][] groups, int r1, int r2, int c1, int c2) {
        boolean isAreaFilled = true;
        Integer areaNum = null;
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                if (groups[r][c] != 0)
                    continue;
                if (areaNum == null) {
                    areaNum = paper[r][c];
                } else {
                    if (paper[r][c] != areaNum) {
                        isAreaFilled = false;
                        // 9등분
                        int r3 = r1 + ((r2 - r1) / 3);
                        int r4 = r1 + 2 * ((r2 - r1) / 3);
                        int c3 = c1 + ((c2 - c1) / 3);
                        int c4 = c1 + 2 * ((c2 - c1) / 3);
                        int[] dr1 = {r1, r1, r1, r3, r3, r3, r4, r4, r4};
                        int[] dr2 = {r3, r3, r3, r4, r4, r4, r2, r2, r2};
                        int[] dc1 = {c1, c3, c4, c1, c3, c4, c1, c3, c4};
                        int[] dc2 = {c3, c4, c2, c3, c4, c2, c3, c4, c2};
                        for (int k = 0; k < 9; k++) {
                            // recursion
                            run(paper, groups, dr1[k], dr2[k], dc1[k], dc2[k]);
                        }
                    }
                }
            }
        }

        if (isAreaFilled) {
            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    groups[r][c] = group;
                }
            }
            int num = paper[r1][c1];
            papers1 += num == -1 ? 1 : 0;
            papers2 += num == 0 ? 1 : 0;
            papers3 += num == 1 ? 1 : 0;
            group++;
        }
    }
}
