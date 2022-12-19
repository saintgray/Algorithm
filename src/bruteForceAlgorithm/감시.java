package bruteForceAlgorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15683 : 감시
// ref url : https://www.acmicpc.net/problem/15683
public class 감시 {
    static int X = 0;
    static int Y = 0;
    static int min = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        X = params[1];
        Y = params[0];
        int[][] map = new int[Y][X];
        List<Camera> cameras = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] >= 1 && map[i][j] <= 5)
                    cameras.add(new Camera(map[i][j], j, i));
            }
        }

        cameras.forEach(camera -> {
            if (camera.type == 5)
                camera.runWatch(map);
        });
        cameras.removeIf(camera -> camera.type == 5);
        for (int[] arr : map)
            min += Arrays.stream(arr).filter(e -> e == 0).count();
        if(cameras.size() > 0)
            dfs(0, map, cameras);
        System.out.println(min);
    }

    private static void dfs(int depth, int[][] map, List<Camera> cameras) {
        if (depth == cameras.size() - 1) {
            for (int i = 0; i < 4; i++) {
                int blindSpots = 0;
                Camera camera = cameras.get(depth);
                camera.rotate();
                for (Camera cam : cameras)
                    cam.runWatch(map);
                for (int[] arr : map)
                    blindSpots += Arrays.stream(arr).filter(e -> e == 0).count();
                min = Math.min(min, blindSpots);
                clean(map);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                cameras.get(depth).rotate();
                dfs(depth + 1, map, cameras);
            }
        }
    }

    private static void clean(int[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                map[i][j] = map[i][j] == -1 ? 0 : map[i][j];
    }

    public static class Camera {
        int type;
        int x;
        int y;
        // 0 동, 1 남, 2 서, 3 북
        boolean[] watching;

        public Camera(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
            switch (this.type) {
                case 1: {
                    this.watching = new boolean[]{true, false, false, false};
                    break;
                }
                case 2: {
                    this.watching = new boolean[]{true, false, true, false};
                    break;
                }
                case 3: {
                    this.watching = new boolean[]{true, false, false, true};
                    break;
                }
                case 4: {
                    this.watching = new boolean[]{true, false, true, true};
                    break;
                }
                case 5: {
                    this.watching = new boolean[]{true, true, true, true};
                    break;
                }
            }
        }

        public void rotate() {
            boolean lastBoolean = this.watching[3];
            for (int i = 2; i >= 0; i--)
                this.watching[i + 1] = this.watching[i];
            this.watching[0] = lastBoolean;
        }

        public void runWatch(int[][] map) {
            for (int direction = 0; direction < this.watching.length; direction++) {
                if (this.watching[direction]) {
                    if (direction == 0 || direction == 2) {
                        // 동 / 서 방향 감시
                        int dx = 0;
                        while (isBoundary(this.x + (direction == 0 ? ++dx : --dx), this.y)) {
                            if (map[this.y][this.x + dx] == 6)
                                break;
                            if (map[this.y][this.x + dx] == 0)
                                map[this.y][this.x + dx] = this.type == 5 ? -2 : -1;
                        }
                    } else if (direction == 1 || direction == 3) {
                        // 남 / 북 방향 감시
                        int dy = 0;
                        while (isBoundary(this.x, this.y + (direction == 1 ? --dy : ++dy))) {
                            if (map[this.y + dy][this.x] == 6)
                                break;
                            if (map[this.y + dy][this.x] == 0)
                                map[this.y + dy][this.x] = this.type == 5 ? -2 : -1;
                        }
                    }
                }
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}
