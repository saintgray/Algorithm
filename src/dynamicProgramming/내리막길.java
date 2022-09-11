package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1520 : 내리막길
//    ref url : https://www.acmicpc.net/problem/1520
//    hint : Dynamic Programming and DFS

public class 내리막길 {
    static int cases = 0;
    static int M = 0;
    static int N = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        M = param[0];
        N = param[1];
        Node[][] map = new Node[M][N];
        for (int i = 0; i < M; i++) {
            int[] heights = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < N; j++)
                map[i][j] = new Node(i, j, heights[j], -1);
        }
        dfs(map, map[0][0]);
        System.out.println(map[0][0].noc);
        in.close();
    }

    static int dfs(Node[][] map, Node anchor) {
        if(anchor.i == M-1 && anchor.j == N-1)
            return 1;
        if(anchor.noc != -1)
            return anchor.noc;

        int anchorNoc = 0;
        for (int n = 0; n < 4; n++) {
            int moveX = anchor.j + dx[n];
            int moveY = anchor.i + dy[n];
            if (isBoundary(moveX, moveY) && map[moveY][moveX].height < anchor.height) {
                anchorNoc += dfs(map, map[moveY][moveX]);
            }
        }
        anchor.noc = anchorNoc;
        return anchor.noc;
    }


    static class Node {
        int i;
        int j;
        int height;
        int noc;

        public Node(int i, int j, int height, int noc) {
            this.i = i;
            this.j = j;
            this.height = height;
            this.noc = noc;
        }
    }

    static boolean isBoundary(int dx, int dy) {
        return dx >= 0 && dy >= 0 && dx < N && dy < M;
    }
}

// 힌트 : dfs 와 dp 의 조합
