package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    14940 : 쉬운 최단거리
//    ref url : https://www.acmicpc.net/problem/14940
public class 쉬운최단거리 {
    static int R;
    static int C;
    static Node[][] map;
    static boolean[][] checked;
    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = params[0];
        C = params[1];
        map = new Node[R][C];
        checked = new boolean[R][C];

        Node start = null;
        for (int i = 0; i < R; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < C; j++) {
                Node node = new Node();
                node.r = i;
                node.c = j;
                node.v = row[j];
                if (node.v == 2) {
                    start = node;
                }
                map[i][j] = node;
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        checked[start.r][start.c] = true;
        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int k = 0; k < 4; k++) {
                int _r = n.r + dr[k];
                int _c = n.c + dc[k];
                if (isBoundary(_r, _c) && !checked[_r][_c] && map[_r][_c].v != 0) {
                    checked[_r][_c] = true;
                    Node next = map[_r][_c];
                    next.d = n.d + 1;
                    q.add(next);
                }
            }
        }

        String delimiter = " ";
        // start 지점을 제외한 node 중 벽이 아니고 거리가 0 인 node는 -1 로 출력한다.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node n = map[i][j];
                int v = n.v;
                out.write(n == start ? "0" :
                        (n.v != 0 && n.d == 0) ? "-1" :
                                String.valueOf(n.d)
                );
                out.write(delimiter);
            }
            out.newLine();
        }
        out.flush();
    }


    static boolean isBoundary(int _r, int _c) {
        return _r < R && _c < C && _r >= 0 && _c >= 0;
    }

    static class Node {
        int r;
        int c;
        int v;
        int d; // distance

    }
}
