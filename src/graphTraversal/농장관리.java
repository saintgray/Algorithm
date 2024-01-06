package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    1245 : 농장 관리
//    ref url : https://www.acmicpc.net/problem/1245
public class 농장관리 {
    static int N = 0;
    static int M = 0;
    static int[] dn = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dm = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = params[0];
        M = params[1];
        Node[][] map = new Node[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < row.length; j++) {
                map[i][j] = new Node(i, j, row[j]);
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!map[i][j].checked) {
                    int n = map[i][j].n;
                    int m = map[i][j].m;

                    Queue<Node> queue = new LinkedList<>();
                    List<Node> group = new ArrayList<>();
                    // 같은 높이를 가진 집합 탐색
                    boolean isPeek = true;
                    int height = map[n][m].h;
                    map[n][m].checked = true;
                    queue.add(map[n][m]);
                    group.add(map[n][m]);
                    while (isPeek && !queue.isEmpty()) {
                        Node node = queue.poll();
                        for (int k = 0; k < 8; k++) {
                            int _n = node.n + dn[k];
                            int _m = node.m + dm[k];
                            if (isBoundary(_n, _m) && !map[_n][_m].checked) {
                                isPeek = map[_n][_m].h <= height;
                                if (!isPeek) {
                                    // 방문 여부 rollback
                                    group.forEach(el -> el.checked = false);
                                    break;
                                }
                                if (map[_n][_m].h == height) {
                                    map[_n][_m].checked = true;
                                    queue.add(map[_n][_m]);
                                    group.add(map[_n][_m]);
                                }
                            }
                        }
                    }
                    if (isPeek) {
                        result++;
                        group.forEach(el -> el.checked = true);
                        Queue<Node> q = new LinkedList<>(group);
                        while (!q.isEmpty()) {
                            Node node = q.poll();
                            n = node.n;
                            m = node.m;
                            for (int k = 0; k < 8; k++) {
                                int _n = n + dn[k];
                                int _m = m + dm[k];
                                if (isBoundary(_n, _m) && !map[_n][_m].checked && map[_n][_m].h <= map[n][m].h) {
                                    map[_n][_m].checked = true;
                                    q.add(map[_n][_m]);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    static boolean isBoundary(int _n, int _m) {
        return _n >= 0 && _m >= 0 && _n < N && _m < M;
    }

    static class Node {
        int n;
        int m;
        int h;
        boolean checked;

        public Node(int n, int m, int h) {
            this.n = n;
            this.m = m;
            this.h = h;
        }
    }
}
