package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    2468 : 안전영역
//    ref url : https://www.acmicpc.net/problem/2468
public class 안전영역 {

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int size;
    static int rainRange = 0;
    static int safeZones = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(in.readLine());
        int[][] region = new int[size + 1][size + 1];
        for (int i = 0; i < region.length - 1; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < row.length; j++) {
                region[i + 1][j + 1] = row[j];
                rainRange = Math.max(row[j], rainRange);
            }
        }


        for (int rain = 0; rain <= rainRange; rain++) {

            boolean[][] check = new boolean[size + 1][size + 1];
            int safeZone = 0;
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    if (region[i][j] > rain && !check[i][j]) {
                        bfs(region, check, rain, new Coord(i, j));
                        safeZone++;
                    }
                }
            }
            safeZones = Math.max(safeZone, safeZones);
        }
        System.out.println(safeZones);
        in.close();
    }

    static void bfs(int[][] region, boolean[][] check, int rain, Coord anchor) {
        Queue<Coord> queue = new LinkedList<>();
        queue.add(anchor);
        check[anchor.i][anchor.j] = true;

        while (!queue.isEmpty()) {
            Coord coord = queue.poll();
            for (int n = 0; n < 4; n++) {
                int y = coord.i + dy[n];
                int x = coord.j + dx[n];
                if (x <= size && x >= 1 && y <= size && y >= 1) {
                    if (region[y][x] > rain && !check[y][x]) {
                        check[y][x] = true;
                        queue.add(new Coord(y, x));
                    }
                }
            }
        }
    }

    static class Coord {
        int i;
        int j;

        public Coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}



