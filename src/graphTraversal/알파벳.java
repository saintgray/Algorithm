package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//    1987 : 알파벳
//    ref url : https://www.acmicpc.net/problem/1987

public class 알파벳 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int R = 0;
    static int C = 0;
    static int maxRun = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        R = params[0];
        C = params[1];
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
        }
        Set<Character> hist = new HashSet<>();
        run(map, hist, 0, 0);
        System.out.println(maxRun);
        in.close();
    }

    static void run(char[][] map, Set<Character> hist, int i, int j) {

        // 탐색 순서 : 좌, 상, 하, 우
        hist.add(map[i][j]);
        boolean end =true;
        for (int n = 0; n < 4; n++) {
            int ni = i + dy[n];
            int nj = j + dx[n];
            if (ni < R && ni >= 0 && nj < C && nj >= 0) {
                if (!hist.contains(map[ni][nj])) {
                    end=false;
                    run(map, hist, ni, nj);
                }
            }
        }
        if(end)
            maxRun = Math.max(maxRun, hist.size());
        hist.remove(map[i][j]);

    }
}
