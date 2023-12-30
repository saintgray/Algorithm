package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 상범빌딩 {
    static int L = 0;
    static int R = 0;
    static int C = 0;

    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {1, -1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};


    static char[][][] b = null;
    static boolean[][][] ck = null;

    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        while (!(input = in.readLine()).equals("0 0 0")) {
            min = Integer.MAX_VALUE;

            int[] params = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            L = params[0];
            R = params[1];
            C = params[2];

            int sL = 0;
            int sR = 0;
            int sC = 0;

            b = new char[L][R][C];
            ck = new boolean[L][R][C];

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    char[] arr = in.readLine().toCharArray();
                    for (int c = 0; c < C; c++) {
                        b[l][r][c] = arr[c];
                        if (arr[c] == 'S') {
                            sL = l;
                            sR = r;
                            sC = c;
                        }
                    }
                }
                in.readLine();
            }
            Queue<String> coord = new LinkedList<>();
            coord.add(String.format("%s %s %s %s", sL, sR, sC, 0));
            ck[sL][sR][sC] = true;
            run(coord);
            out.write(min == Integer.MAX_VALUE ? "Trapped!\n" : String.format("Escaped in %s minute(s).\n", min));
        }
        out.flush();
    }

    static void run(Queue<String> coord) {
        int[] coords = Arrays.stream(coord.poll().split(" ")).mapToInt(Integer::parseInt).toArray();
        int l = coords[0];
        int r = coords[1];
        int c = coords[2];
        int time = coords[3];
        if (b[l][r][c] == 'E') {
            min = Math.min(time, min);
        } else {
            for (int i = 0; i < 6; i++) {
                int nextL = l + dl[i];
                int nextR = r + dr[i];
                int nextC = c + dc[i];
                if (isBoundary(nextL, nextR, nextC) &&
                        b[nextL][nextR][nextC] != '#' &&
                        !ck[nextL][nextR][nextC]) {
                    ck[nextL][nextR][nextC] = true;
                    coord.add(String.format("%s %s %s %s", nextL, nextR, nextC, time +1));
                }
            }
            while (!coord.isEmpty())
                run(coord);
        }
    }


    static boolean isBoundary(int l, int r, int c) {
        return l >= 0 && r >= 0 && c >= 0 && l < L && r < R && c < C;
    }
}
