package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

//    2146 : 다리 만들기
//    ref url : https://www.acmicpc.net/problem/2146
public class 다리만들기 {
    static int groupNum = 1;
    static int N = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int shortestCut = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        Land[][] map = new Land[N][N];
        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = arr[j] == 0 ? null : new Land(i, j);
            }

        }
        Queue<Land> queue = new LinkedList<>();
        for (Land[] row : map) {
            for (Land land : row) {
                if (land != null && !land.visited) {
                    land.visited = true;
                    land.group = groupNum;
                    queue.add(land);
                    scanLand(map, queue);
                    groupNum++;
                }
            }
        }
        clearMap(map);
        // 각 지역별로 다리를 놓을 수 있는 land 선별 (3 면 또는 4면중 하나라도 바다인 경우)
        List<Land> lands = Arrays.stream(map).flatMap(Arrays::stream).filter(Objects::nonNull)
                .filter(land -> {
                    for (int i = 0; i < 4; i++) {
                        if (isBoundary(land.x + dx[i], land.y + dy[i]) && Objects.isNull(map[land.x + dx[i]][land.y + dy[i]]))
                            return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        List<Integer> groups = lands.stream().map(e -> e.group).distinct().collect(Collectors.toList());
        for (int group : groups) {
            Queue<Land> resultQueue = new LinkedList<>();
            lands.stream().filter(e -> e.group == group).forEach(e -> {
                e.visited = true;
                resultQueue.add(e);
            });
            constructBridge(map, resultQueue, group);
            clearMap(map);
        }
        System.out.println(shortestCut);
    }


    static void scanLand(Land[][] map, Queue<Land> queue) {
        while (!queue.isEmpty()) {
            Land land = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = land.x + dx[i];
                int nextY = land.y + dy[i];
                if (isBoundary(nextX, nextY) && map[nextX][nextY] != null && !map[nextX][nextY].visited) {
                    queue.add(map[nextX][nextY]);
                    map[nextX][nextY].visited = true;
                    map[nextX][nextY].group = groupNum;
                }
            }
        }
    }

    static void constructBridge(Land[][] map, Queue<Land> resultQueue, int group) {
        int shortCut = 1;
        while (!resultQueue.isEmpty()) {
            Queue<Land> tempQueue = new LinkedList<>();
            while (!resultQueue.isEmpty()) {
                Land next = resultQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextX = next.x + dx[i];
                    int nextY = next.y + dy[i];
                    if (isBoundary(nextX, nextY)) {
                        if (Objects.nonNull(map[nextX][nextY]) && map[nextX][nextY].group != -1 && map[nextX][nextY].group != group) {
                            shortestCut = Math.min(shortCut - 1 , shortestCut);
                            return;
                        }
                        if (Objects.isNull(map[nextX][nextY])) {
                            // 다리건설
                            map[nextX][nextY] = new Land(nextX, nextY);
                            map[nextX][nextY].group = -1;
                        }
                        if (!map[nextX][nextY].visited) {
                            map[nextX][nextY].visited = true;
                            tempQueue.add(map[nextX][nextY]);
                        }
                    }
                }
            }
            resultQueue.addAll(tempQueue);
            shortCut++;
        }
    }

    static void printMap(Land[][] map) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Land[] a : map) {
            for (Land b : a)
                out.write(b == null ? "0" : b.group == -1 ? "*" : String.valueOf(b.group));
            out.newLine();
        }
        out.flush();
    }

    static void clearMap(Land[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    if (map[i][j].group == -1) {
                        map[i][j] = null;
                    } else {
                        map[i][j].visited = false;
                    }
                }
            }
        }
    }

    static class Land {
        int x;
        int y;
        int group;
        boolean visited;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
