package graphTraversal;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    4963 : 섬의 개수
//    ref url : https://www.acmicpc.net/problem/4963
public class 섬의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String param;
        while (!(param = in.readLine()).equals("0 0")) {
            int[] mapInfo = Arrays.stream(param.split(" ")).mapToInt(Integer::parseInt).toArray();
            int w = mapInfo[0];
            int h = mapInfo[1];
            int[][] map = new int[h + 1][w + 1];
            boolean[][] check = new boolean[h + 1][w + 1];
            for (int i = 1; i < h + 1; i++) {
                int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(row, 0, map[i], 1, row.length);
            }

            Queue<String> queue = new LinkedList<>();
            int islands = 0;
            for (int i = 1; i < h + 1; i++) {
                for (int j = 1; j < w + 1; j++) {
                    if (map[i][j] == 1 && !check[i][j]) {
                        queue.add(String.format("%d,%d", i, j));
                        check[i][j] = true;
                        run(map, check, queue);
                        islands++;
                    }
                }
            }
            out.write(String.format("%d\n", islands));
        }
        out.flush();
        in.close();
        out.close();
    }


    static void run(int[][] map, boolean[][] check, Queue<String> queue) {
        if (queue.isEmpty())
            return;
        int[] coordinate = Arrays.stream(queue.poll().split(",")).mapToInt(Integer::parseInt).toArray();
        int i = coordinate[0];
        int j = coordinate[1];

        // 상하좌우
        if (i + 1 < map.length && map[i + 1][j] == 1 && !check[i + 1][j]) {
            queue.add(String.format("%d,%d", i + 1, j));
            check[i + 1][j] = true;
        }
        if (i - 1 >= 1 && map[i - 1][j] == 1 && !check[i - 1][j]) {
            queue.add(String.format("%d,%d", i - 1, j));
            check[i - 1][j] = true;
        }
        if (j + 1 < map[i].length && map[i][j + 1] == 1 && !check[i][j + 1]) {
            queue.add(String.format("%d,%d", i, j + 1));
            check[i][j + 1] = true;
        }
        if (j - 1 >= 1 && map[i][j - 1] == 1 && !check[i][j - 1]) {
            queue.add(String.format("%d,%d", i, j - 1));
            check[i][j - 1] = true;
        }
        // 대각선
        if (i + 1 < map.length && j + 1 < map[i + 1].length && map[i + 1][j + 1] == 1 && !check[i + 1][j + 1]) {
            queue.add(String.format("%d,%d", i + 1, j + 1));
            check[i + 1][j + 1] = true;
        }
        if (i - 1 >= 1 && j - 1 >= 1 && map[i - 1][j - 1] == 1 && !check[i - 1][j - 1]) {
            queue.add(String.format("%d,%d", i - 1, j - 1));
            check[i - 1][j - 1] = true;
        }
        if (i + 1 < map.length && j - 1 >= 1 && map[i + 1][j - 1] == 1 && !check[i + 1][j - 1]) {
            queue.add(String.format("%d,%d", i + 1, j - 1));
            check[i + 1][j - 1] = true;
        }
        if (i - 1 >= 1 && j + 1 < map[i - 1].length && map[i - 1][j + 1] == 1 && !check[i - 1][j + 1]) {
            queue.add(String.format("%d,%d", i - 1, j + 1));
            check[i - 1][j + 1] = true;
        }
        run(map, check, queue);
    }
}
