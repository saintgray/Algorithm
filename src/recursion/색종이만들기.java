package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    2630 : 색종이 만들기
//    ref url : https://www.acmicpc.net/problem/2630
public class 색종이만들기 {

    // always even
    static int N = 0;
    static int group = 1;
    static int white = 0;
    static int blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.flush();

        N = Integer.parseInt(in.readLine());
        int[][] paper = new int[N][N];
        int[][] groups = new int[N][N];
        for (int i = 0; i < N; i++) {
            paper[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        run(paper, groups, 0, N, 0, N);
        System.out.println(white);
        System.out.println(blue);
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
                        // 4등분
                        int[] dr1 = {r1, r1, (r1 + r2) / 2, (r1 + r2) / 2};
                        int[] dr2 = {(r1 + r2) / 2, (r1 + r2) / 2, r2, r2};
                        int[] dc1 = {c1, (c1 + c2) / 2, c1, (c1 + c2) / 2};
                        int[] dc2 = {(c1 + c2) / 2, c2, (c1 + c2) / 2, c2};
                        for (int k = 0; k < 4; k++) {
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
            white += num == 0 ? 1 : 0;
            blue += num == 0 ? 0 : 1;
            group++;
        }
    }
}
