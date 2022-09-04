package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    7569 : 토마토
//    ref url : https://www.acmicpc.net/problem/7569

public class 토마토 {
    static int N;
    static int M;
    static int H;
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int days = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        N = params[1];
        M = params[0];
        H = params[2];
        Tomato[][][] tomatoBoxes = new Tomato[H][N][M];
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
                for (int m = 0; m < M; m++) {
                    // 1 : 익은토마토, 0 : 익지않은 토마토 , -1 : 비어있는 칸
                    int stat = row[m];
                    if (row[m] != -1)
                        tomatoBoxes[h][n][m] = new Tomato(h, n, m, stat == 1 ? true : false);
                }
            }
        }
        run(tomatoBoxes);
        System.out.println(allRipes(tomatoBoxes) ? days : -1);
        in.close();
    }


    static void run(Tomato[][][] tomatoes) {
        Queue<Tomato> queue = new LinkedList<>();
        int prevAdded = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    Tomato tomato = tomatoes[h][n][m];
                    if (tomato != null && tomato.ripe) {
                        queue.add(tomato);
                        prevAdded ++;
                    }
                }
            }
        }
        while (prevAdded > 0) {
            int added = 0;
            for (int add = 0; add < prevAdded; add++) {
                Tomato tomato = queue.poll();
                for (int i = 0; i < 6; i++) {
                    int scanX = tomato.m + dx[i];
                    int scanY = tomato.n + dy[i];
                    int scanZ = tomato.h + dz[i];
                    if (boundary(scanZ, scanY, scanX)) {
                        Tomato neighborTomato = tomatoes[scanZ][scanY][scanX];
                        if (neighborTomato != null && !neighborTomato.ripe) {
                            queue.add(neighborTomato);
                            neighborTomato.ripe = true;
                            added ++;
                        }
                    }
                }
            }
            prevAdded = added;
            if(prevAdded >0)
                days++;
        }

    }

    static boolean boundary(int h, int n, int m) {
        return m >= 0 && n >= 0 && h >= 0 && m < M && n < N && h < H;
    }

    static boolean allRipes(Tomato[][][] tomatoes) {
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatoes[h][n][m] != null && !tomatoes[h][n][m].ripe) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

//    static void debug(Tomato[][][] tomatoes) {
//        for (int h = 0; h < H; h++) {
//            for (int n = 0; n < N; n++) {
//                for (int m = 0; m < M; m++) {
//                    System.out.printf("%s ", tomatoes[h][n][m] == null ? "." : tomatoes[h][n][m].ripe ? "O" : "X");
//                }
//                System.out.println();
//            }
//        }
//        System.out.println();
//    }

}

class Tomato {
    int h;
    int n;
    int m;
    boolean ripe;

    public Tomato(int h, int n, int m, boolean ripe) {
        this.h = h;
        this.n = n;
        this.m = m;
        this.ripe = ripe;
    }
}
