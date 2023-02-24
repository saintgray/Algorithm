package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    1743 : 음식물 피하기
//    ref url : https://www.acmicpc.net/problem/1743
public class 음식물피하기 {

    static int R;
    static int C;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = params[0];
        C = params[1];
        int cnt = params[2];
        Garbage[][] map = new Garbage[R][C];
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < cnt; i++) {
            int[] coord = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[coord[0] - 1][coord[1] - 1] = new Garbage(coord[0] - 1, coord[1] - 1);
        }
        Queue<Garbage> queue = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    int count = 1;
                    queue.add(map[i][j]);
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Garbage garb = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = dr[k] + garb.r;
                            int c = dc[k] + garb.c;
                            if (isBoudary(r, c) && map[r][c] != null && !visited[r][c]) {
                                count++;
                                queue.add(map[r][c]);
                                visited[r][c] = true;
                            }
                        }
                    }
                    result = Math.max(result, count);
                }
            }
        }
        System.out.println(result);
    }

    static boolean isBoudary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Garbage {
        int r;
        int c;

        public Garbage(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
