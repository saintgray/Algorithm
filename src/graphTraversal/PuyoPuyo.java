package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//    11559 : Puyo Puyo
//    ref url : https://www.acmicpc.net/problem/11559
public class PuyoPuyo {
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static Node[][] field = new Node[12][6];
    static int R = 12;
    static int C = 6;
    static final char EMPTY = '.';
    static final char POP = 'x';

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int r = 0; r < R; r++) {
            char[] row = in.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                Node n = new Node(r,c,row[c]);
                field[r][c] = n;
            }
        }

        Map<Integer, List<Node>> popGroup = null;
        int popCount = 0;
        while ((popGroup = getPopGroup()).size() > 0) {
            pop(popGroup);
            dropBlocks();
            resetCheckFlag();
            popCount++;
        }
        System.out.println(popCount);
    }

    static Map<Integer, List<Node>> getPopGroup() {
        Map<Integer, List<Node>> result = new HashMap<>();
        int groupNum = 1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!field[r][c].checked && field[r][c].v != EMPTY) {
                    Queue<Node> q = new LinkedList<>();
                    q.add(field[r][c]);
                    result.put(groupNum, new ArrayList<>());
                    while (!q.isEmpty()) {
                        Node next = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int _r = next.r + dr[k];
                            int _c = next.c + dc[k];
                            if (isBoundary(_r, _c) && !field[_r][_c].checked && field[_r][_c].v == next.v) {
                                field[_r][_c].checked = true;
                                q.add(field[_r][_c]);
                                result.get(groupNum).add(field[_r][_c]);
                            }
                        }
                    }
                    groupNum++;
                }
            }
        }
        Set<Integer> keySet = new HashSet<>(result.keySet());
        for (Integer group : keySet) {
            if (result.get(group).size() < 4) {
                result.remove(group);
            }
        }
        return result;
    }

    static void pop(Map<Integer, List<Node>> popGroup) {
        for (Integer group : popGroup.keySet()) {
            List<Node> list = popGroup.get(group);
            for (Node node : list) {
                field[node.r][node.c].v = POP;
            }
        }
    }

    static void dropBlocks() {
        for (int c = 0; c < C; c++) {
            for (int r = R - 1; r >= 0; r--) {
                if (field[r][c].v == POP) {
                    for (int _r = r; _r > 0; _r--) {
                        Node upper =field[_r-1][c];
                        Node lower = field[_r][c];
                        lower.v = upper.v;
                    }
                    r++;
                    field[0][c] = new Node(0, c, EMPTY);
                }
            }
        }
    }

    static void resetCheckFlag() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                field[r][c].checked = false;
            }
        }
    }

    static boolean isBoundary(int _r, int _c) {
        return _r < R && _c < C && _r >= 0 && _c >= 0;
    }

    static class Node {
        int r;
        int c;
        char v;
        boolean checked;

        public Node(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}