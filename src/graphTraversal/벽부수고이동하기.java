package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    2206 : 벽 부수고 이동하기
//    ref url : https://www.acmicpc.net/problem/2206
public class 벽부수고이동하기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static Node[][] map;

    static int[] dn = new int[]{1,-1,0,0};
    static int[] dm = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = p[0];
        M = p[1];
        map = new Node[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            Node[] nodeRow = new Node[M];
            for (int j = 0; j < M; j++) {
                nodeRow[j] = new Node(i, j, row[j]);
            }
            map[i] = nodeRow;
        }
        map[0][0].fastestWithOneBreak = 1;
        map[0][0].fastestWithNoneBreak = 1;

        Queue<Status> q = new LinkedList<>();
        q.add(new Status(0,0,1, false));
        while(!q.isEmpty()) {
            Status s = q.poll();
            int n = s.n;
            int m = s.m;
            for (int k = 0; k < 4; k++) {
                int _n = n + dn[k];
                int _m = m + dm[k];
                if(!isBoundary(_n,_m)) continue;
                Node node = map[_n][_m];
                int f1 = node.fastestWithNoneBreak;
                int f2 = node.fastestWithOneBreak;
                if(node.isWall()) {
                    // 깼으면 더 못감
                    if(s.brokenWall) continue;
                    if(f2  > s.moved + 1) {
                        node.fastestWithOneBreak = s.moved + 1;
                        q.add(new Status(_n,_m, s.moved + 1, true));
                    }
                } else {
                    // 한번 깼냐 안갰냐에 따라 분기
                    if(s.brokenWall) {
                        if(f2 > s.moved +1) {
                            node.fastestWithOneBreak = s.moved + 1;
                            q.add(new Status(_n,_m, s.moved + 1, s.brokenWall));
                        }

                    } else {
                        if(f1 > s.moved + 1) {
                            node.fastestWithNoneBreak = s.moved + 1;
                            q.add(new Status(_n,_m, s.moved + 1, s.brokenWall));
                        }
                    }
                }
            }
        }
        Node exit = map[N-1][M-1];
        int result = Math.min(exit.fastestWithNoneBreak, exit.fastestWithOneBreak);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static boolean isBoundary(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class Status {
        int n;
        int m;
        int moved;
        boolean brokenWall;
        public Status(int n, int m, int moved, boolean brokenWall) {
            this.n = n;
            this.m = m;
            this.moved = moved;
            this.brokenWall = brokenWall;
        }
    }

    static class Node {
        int n;
        int m;
        int v;
        int fastestWithOneBreak;
        int fastestWithNoneBreak;

        public Node(int n, int m, int v) {
            this.n = n;
            this.m = m;
            this.v = v;
            this.fastestWithNoneBreak = Integer.MAX_VALUE;
            this.fastestWithOneBreak = Integer.MAX_VALUE;
        }

        boolean isWall() {
            return this.v == 1;
        }
    }
}
