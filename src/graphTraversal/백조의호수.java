package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    3197 : 백조의 호수
//    ref url : https://www.acmicpc.net/problem/3197
//    ref algorithm : disjoint set
public class 백조의호수 {

    static int R;
    static int C;
    static final int[] dr = new int[]{0, 0, 1, -1};
    static final int[] dc = new int[]{1, -1, 0, 0};

    static Node[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] param = in.readLine().split(" ");
        R = Integer.parseInt(param[0]);
        C = Integer.parseInt(param[1]);
        Node swan1 = null;
        Node swan2 = null;
        // read data
        map = new Node[R][C];
        for (int r = 0; r < R; r++) {
            char[] line = in.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                map[r][c] = new Node(r, c, line[c]);
                if (map[r][c].isSwan()) {
                    if (swan1 == null) swan1 = map[r][c];
                    else swan2 = map[r][c];
                }
            }
        }
        // grouping
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                Node n = map[r][c];
                if (n.visited) continue;
                if (n.isIceBerg()) continue;
                Queue<Node> groupQueue = new LinkedList<>();
                n.visited = true;
                groupQueue.add(n);
                while (!groupQueue.isEmpty()) {
                    Node _n = groupQueue.poll();
                    _n.parent = n;
                    for (int k = 0; k < 4; k++) {
                        int _r = _n.r + dr[k];
                        int _c = _n.c + dc[k];
                        if (!isBoundary(_r, _c)) continue;
                        Node scan = map[_r][_c];
                        if (!scan.visited && !scan.isIceBerg()) {
                            scan.visited = true;
                            // scan.parent = n;
                            groupQueue.add(scan);
                        }
                    }
                }
            }
        }
        Arrays.stream(map).flatMap(Arrays::stream).forEach(n -> n.visited = false);
        // scan iceberg to be melt
        Queue<Node> target = new LinkedList<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                Node n = map[r][c];
                // 빙산이 아니거나, 녹일 대상에 추가된 빙산인 경우 SKIP
                if (!n.isIceBerg() || n.visited) continue;
                boolean isTarget = false;
                // n 이 물과 맞닿아 있는지 확인
                for (int k = 0; k < 4; k++) {
                    int _r = n.r + dr[k];
                    int _c = n.c + dc[k];
                    if (!isBoundary(_r, _c)) continue;
                    if (!map[_r][_c].isIceBerg()) {
                        isTarget = true;
                        break;
                    }
                }
                if (isTarget) {
                    n.visited = true;
                    target.add(n);
                }
            }
        }
        // solve
        int days = 0;
        while (findParentOf(swan1) != findParentOf(swan2)) {
            days++;
            Queue<Node> subTarget = new LinkedList<>();
            while (!target.isEmpty()) {
                Node n = target.poll();
                n.melt();
                for (int k = 0; k < 4; k++) {
                    int _r = n.r + dr[k];
                    int _c = n.c + dc[k];
                    if (!isBoundary(_r, _c)) continue;
                    Node scan = map[_r][_c];
                    // 탐색하지 않은 빙산일 경우 queue 에 추가
                    if (!scan.visited && scan.isIceBerg()) {
                        scan.visited = true;
                        subTarget.add(scan);
                    }
                    // 그룹 병합
                    if (!scan.isIceBerg()) {
                        Node p1 = findParentOf(n);
                        Node p2 = findParentOf(scan);
                        if (p1 != p2) {
                            merge(p1, p2);
                        }
                    }
                }
            }
            target = subTarget;
        }
        System.out.println(days);
    }

    static Node findParentOf(Node n) {
        if(n ==n.parent) return n;
        return (n.parent = findParentOf(n.parent));
    }

    static void merge(Node p1, Node p2) {
        if(p1.r == p2.r) {
            if(p1.c < p2.c) {
                p2.parent = p1;
            } else {
                p1.parent = p2;
            }
        } else if (p1.r < p2.r) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
        }
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Node {
        int r;
        int c;
        char v;
        boolean visited;
        Node parent;

        void melt() {
            this.v = '.';
        }

        boolean isIceBerg() {
            return this.v == 'X';
        }

        boolean isSwan() {
            return this.v == 'L';
        }

        public Node(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
            this.parent = this;
        }
    }
}
