package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    21609 : 상어 중학교
//    ref url : https://www.acmicpc.net/problem/21609
public class 상어중학교 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static Node[][] map;
    static int score = 0;

    static int[] dn = new int[]{1,-1,0,0};
    static int[] dm = new int[]{0,0,1,-1};

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = p[0];
        M = p[0];
        map = new Node[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Node[] nodeRow = new Node[M];
            for (int j = 0; j < M; j++) {
                nodeRow[j] = new Node(i, j, row[j]);
            }
            map[i] = nodeRow;
        }
        BlockGroup group = null;
        while((group = findGroup()) != null && group.nodes.size() >= 2) {
            // System.out.println(String.format("next STD block : [%d 행] [%d 열], size: %d", group.standardBlock.n+1, group.standardBlock.m+1, group.nodes.size()));
            pop(group);
            drop();
            rotate();
            drop();
        }
        System.out.println(score);
    }

    static void pop(BlockGroup group) {
        int size = group.nodes.size();
        score += Math.pow(size,2);
        List<Node> nodes = group.nodes;
        if(nodes.size() == 0) return;
        for (Node node : nodes) map[node.n][node.m] = null;
    }

    static void drop() {
        for (int m = 0; m < M; m++) {
            for (int n = N - 1; n >= 0; n--) {
                Node node = map[n][m];

                if(node == null || node.isBrick()) continue;
                int _n = n;
                while(isBoundary(_n + 1,m) && map[_n + 1][m] == null) {
                    _n++;
                }
                if(_n != n) {
                    map[_n][m] = new Node(_n,m,node.v);
                    map[n][m] = null;
                }
            }
        }
    }

    static void debug(String type) {
        System.out.println(String.format("================= %s : %s", type, "score".equals(type) ? score : ""));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%s", map[i][j] == null ? "[ ]"  : map[i][j].isBrick() ? "[*]" : "["+map[i][j].v+"]");
            }
            System.out.println();
        }
    }

    static void rotate() {
        // 반시계 회전, clear flag 초기화
        Node[][] result = new Node[N][M];
        for (int n = 0; n < N; n++) {
            Node[] row = map[n];
            for (int m = 0; m < M; m++) {
                result[M-1-m][n] = row[m];
                if(row[m] != null) {
                    result[M-1-m][n].n = M-1-m;
                    result[M-1-m][n].m = n;
                    result[M-1-m][n].v = row[m].v;
                    result[M-1-m][n].checked = false;
                }
            }
        }
        map = result;
    }

    static BlockGroup findGroup() {
        List<BlockGroup> groups = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node standardNode = map[i][j];
                if(Node.isNull(i,j) || standardNode.isBrick() || standardNode.isRainbow() || standardNode.checked) continue;
                BlockGroup group = new BlockGroup();
                group.standardBlock = standardNode;
                Queue<Node> q = new LinkedList<>();
                standardNode.checked = true;
                group.nodes.add(standardNode);
                q.add(standardNode);
                while(!q.isEmpty()) {
                    Node next = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int _i = next.n + dn[k];
                        int _j = next.m + dm[k];
                        if(!Node.isNull(_i,_j) && !map[_i][_j].checked && (map[_i][_j].isRainbow() || map[_i][_j].v == standardNode.v)) {
                            map[_i][_j].checked = true;
                            q.add(map[_i][_j]);
                            group.nodes.add(map[_i][_j]);
                            if(map[_i][_j].isRainbow()) group.rainbows++;
                        }
                    }
                }
                groups.add(group);
                group.nodes.stream().filter(Node::isRainbow).forEach(rainbow -> rainbow.checked=false);
            }
        }
        return groups.size() == 0 ? null : groups.stream().sorted(Comparator.comparing(group -> ((BlockGroup) group).nodes.size(), Comparator.reverseOrder())
                .thenComparing(group -> ((BlockGroup) group).rainbows, Comparator.reverseOrder())
                .thenComparing(group -> ((BlockGroup) group).standardBlock.n, Comparator.reverseOrder())
                .thenComparing(group -> ((BlockGroup) group).standardBlock.m, Comparator.reverseOrder()))
                .findFirst()
                .get();
    }

    static boolean isBoundary(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class Node {
        int n;
        int m;
        int v;
        boolean checked;

        public Node(int n, int m, int v) {
            this.n = n;
            this.m = m;
            this.v = v;
        }

        boolean isBrick() { return this.v == -1; }
        boolean isRainbow() { return this.v  == 0; }
        static boolean isNull(int n, int m) { return !isBoundary(n, m) || map[n][m] == null; }


    }

    static class BlockGroup {
        Node standardBlock;
        int rainbows;
        List<Node> nodes = new ArrayList<>();
    }
}