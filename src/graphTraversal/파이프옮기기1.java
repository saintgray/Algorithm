package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    17070 : 파이프 옮기기 1
//    ref url : https://www.acmicpc.net/problem/17070
public class 파이프옮기기1 {
    static int N = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        int[][] room = new int[N][N];
        Integer[][] cases = new Integer[N][N];
        cases[0][1] = 1;
        for (int i = 0; i < N; i++) {
            room[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        track(cases, room, 1, 0, 1);
        System.out.println(cases[N - 1][N - 1] == null ? "0" : cases[N - 1][N - 1]);
    }

    public static void track(Integer[][] cases, int[][] room, int layout, int r, int c) {
        // layout
        // 1: 가로 , 2: 세로,  3.대각선
        if (r == N - 1 && c == N - 1)
            return;
        int[] dr = layout == 1 ? new int[]{0, 1} : layout == 2 ? new int[]{1, 1} : new int[]{0, 1, 1};
        int[] dc = layout == 1 ? new int[]{1, 1} : layout == 2 ? new int[]{0, 1} : new int[]{1, 0, 1};
        // layout type
        int[] dt = layout == 1 ? new int[]{1, 3} : layout == 2 ? new int[]{2, 3} : new int[]{1, 2, 3};
        for (int i = 0; i < dr.length; i++) {
            int _r = r + dr[i];
            int _c = c + dc[i];
            int _layout = dt[i];
            if (isBoundary(_r, _c) && canPlace(room, _r, _c, _layout)) {
                Integer _case = cases[_r][_c];
                cases[_r][_c] = _case == null ? 1 : cases[_r][_c] + 1;
                track(cases, room, _layout, _r, _c);
            }
        }
    }
    public static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    public static boolean canPlace(int[][] room, int r, int c, int type) {
        if (type == 3) {
            return room[r][c] != 1 && room[r - 1][c] != 1 && room[r][c - 1] != 1;
        } else {
            return room[r][c] != 1;
        }
    }
}
