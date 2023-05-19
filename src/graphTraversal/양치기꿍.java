package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    3187 : 양치기 꿍
//    ref url : https://www.acmicpc.net/problem/3187
public class 양치기꿍 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = params[0];
        int C = params[1];
        String[][] map = new String[R][C];
        boolean[][] checked = new boolean[R][C];
        for (int i = 0; i < map.length; i++) {
            map[i] = in.readLine().split("");
        }
        List<Region> regions = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(!"#".equals(map[i][j]) && !checked[i][j]) {
                    Queue<String> queue = new LinkedList<>();
                    Region region = new Region();
                    queue.add(String.format("%s %s", i, j));
                    checked[i][j] = true;
                    region.sheep += "k".equals(map[i][j]) ? 1 : 0;
                    region.wolf += "v".equals(map[i][j]) ? 1 : 0;
                    while(!queue.isEmpty()) {
                        String[] coord = queue.poll().split(" ");
                        int coordX = Integer.parseInt(coord[0]);
                        int coordY = Integer.parseInt(coord[1]);
                        for (int k = 0; k < 4; k++) {
                            int x = coordX + dx[k];
                            int y = coordY + dy[k];
                            if (isBoundary(x, y, R, C) && !checked[x][y] && !"#".equals(map[x][y])) {
                                checked[x][y] = true;
                                queue.add(String.format("%s %s", x, y));
                                region.sheep += "k".equals(map[x][y]) ? 1 : 0;
                                region.wolf += "v".equals(map[x][y]) ? 1 : 0;
                            }
                        }
                    }
                    if (region.sheep > region.wolf) {
                        region.wolf = 0;
                    } else {
                        region.sheep = 0;
                    }
                    regions.add(region);
                }
            }
        }
        System.out.printf("%s %s",
                regions.stream().map(region -> region.sheep).reduce(Integer::sum).get(),
                regions.stream().map(region -> region.wolf).reduce(Integer::sum).get());
    }

    static class Region {
        int sheep;
        int wolf;

    }

    static boolean isBoundary(int x, int y, int X, int Y) {
        return x >=0 && y >= 0 && x < X && y < Y;
    }
}
