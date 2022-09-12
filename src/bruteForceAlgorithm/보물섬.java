package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//    2589 : 보물섬
//    ref url : https://www.acmicpc.net/problem/2589
public class 보물섬 {
    static int X = 0;
    static int Y = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        X = Integer.parseInt(input[1]);
        Y = Integer.parseInt(input[0]);
        Land[][] map = new Land[Y][X];
        for (int i = 0; i < Y; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < row.length; j++)
                if (row[j] == 'L')
                    map[i][j] = new Land(j, i);
        }
        System.out.println(findShortCut(map));
        in.close();
    }

    static class Land {
        int x;
        int y;
        int shortCut;
        boolean checked;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int findShortCut(Land[][] map) {
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    Land start = map[i][j];
                    if (canBeSpot(start, map)) {
                        Queue<Land> queue = new LinkedList<>();
                        start.checked = true;
                        queue.add(start);
                        while (!queue.isEmpty()) {
                            Land next = queue.poll();
                            for (int n = 0; n < 4; n++) {
                                int x = next.x + dx[n];
                                int y = next.y + dy[n];
                                if (isBoundary(x, y) && map[y][x] != null && !map[y][x].checked) {
                                    map[y][x].shortCut = next.shortCut + 1;
                                    map[y][x].checked = true;
                                    result = Math.max(result, map[y][x].shortCut);
                                    queue.add(map[y][x]);
                                }
                            }
                        }
                    }
                    clearFlag(map);
                }
            }
        }
        return result;
    }

    static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < X && y < Y;
    }

    static boolean canBeSpot(Land land, Land[][] map) {
        int neighborLandCnt = 0;
        int outOfBoardCnt = 0;
        int waterCnt = 0;
        for (int i = 0; i < 4; i++) {
            int x = land.x + dx[i];
            int y = land.y + dy[i];
            if (isBoundary(x, y)) {
                if (map[y][x] != null) {
                    neighborLandCnt++;
                } else {
                    waterCnt++;
                }
            } else {
                outOfBoardCnt++;
            }
        }
        return neighborLandCnt <= 1 || (neighborLandCnt > 1 && waterCnt + outOfBoardCnt >= 2);
    }

    static void clearFlag(Land[][] map) {
        for (Land[] lands : map) {
            for (Land land : lands) {
                if(land != null){
                    land.shortCut = 0;
                    land.checked = false;
                }
            }
        }
    }
}
