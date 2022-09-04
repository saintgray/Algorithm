package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//    10026 : 적록색약
//    ref url : https://www.acmicpc.net/problem/10026

public class 적록색약 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(in.readLine());
        char[][] map = new char[size][size];
        for (int i = 0; i < size; i++)
            map[i] = in.readLine().toCharArray();
        System.out.printf("%d %d",bfs(map, false),bfs(map, true));
        in.close();
    }

    static int bfs(char[][] map, boolean colorBlind) {
        Queue<Integer[]> queue = new LinkedList<>();
        boolean[][] check = new boolean[size][size];
        int regionCnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!check[i][j]) {
                    regionCnt++;
                    queue.add(new Integer[]{i, j});
                    check[i][j] = true;
                    while (!queue.isEmpty()) {
                        Integer[] coordinate = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            char pollC = map[coordinate[0]][coordinate[1]];
                            int y = coordinate[0] + dy[k];
                            int x = coordinate[1] + dx[k];
                            if (boundary(y, x) && !check[y][x]) {
                                char c = map[y][x];
                                if (colorBlind) {
                                    if (((c == 'R' || c == 'G') && (pollC == 'R' || pollC == 'G')) || c == pollC) {
                                        check[y][x] = true;
                                        queue.add(new Integer[]{y, x});
                                    }
                                } else {
                                    if (c == pollC) {
                                        check[y][x] = true;
                                        queue.add(new Integer[]{y, x});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return regionCnt;
    }

    public static boolean boundary(int i, int j) {
        return i >= 0 && j >= 0 && i < size && j < size;
    }

}