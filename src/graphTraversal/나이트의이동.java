package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    7562 : 나이트의 이동
//    ref url : https://www.acmicpc.net/problem/7562
public class 나이트의이동 {
    static int[] dx = {-2, -2, 2, 2, 1, -1, 1, -1};
    static int[] dy = {1, -1, 1, -1, -2, -2, 2, 2};
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(in.readLine());
        for (int i = 0; i < testCases; i++) {
            size = Integer.parseInt(in.readLine());
            int[] knightCoordinate = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int[] goalCoordinate = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int[][] map = new int[size][size];
            scan(map, knightCoordinate[0], knightCoordinate[1], goalCoordinate[0], goalCoordinate[1]);
            System.out.println(map[goalCoordinate[0]][goalCoordinate[1]] - 1);
        }
        in.close();
    }

    static void scan(int[][] map, int i, int j, int goalI, int goalJ) {
        Queue<Point> queue = new LinkedList<>();
        // start point
        map[i][j] = 1;
        queue.add(new Point(i, j));
        // scan
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int pi = point.i;
            int pj = point.j;
            for (int n = 0; n < 8; n++) {
                int ni = pi + dy[n];
                int nj = pj + dx[n];
                if (ni >= 0 && ni < size && nj >= 0 && nj < size && map[ni][nj] == 0) {
                    // fastest move cnt
                    map[ni][nj] = map[pi][pj] + 1;
                    if (ni == goalI && nj == goalJ)
                        return;
                    // add queue
                    queue.add(new Point(ni, nj));
                }
            }
        }
    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}


