package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

//    5427 : 불
//    ref url : https://www.acmicpc.net/problem/5427
public class 불 {

    static Node[][] map;
    static int R;
    static int C;
    static int[] dr = new int[]{0, 0, 1, -1};
    static int[] dc = new int[]{1, -1, 0, 0};
    static final char FIRE = '*';
    static final char WALL = '#';
    static final char ME = '@';
    static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < N; tc++) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            R = params[1];
            C = params[0];
            map = new Node[R][C];

            Node me = null;
            Queue<Node> fires = new LinkedList<>();
            for (int r = 0; r < R; r++) {
                char[] row = in.readLine().toCharArray();
                for (int c = 0; c < C; c++) {
                    Node n = new Node();
                    n.r = r;
                    n.c = c;
                    n.v = row[c];
                    map[r][c] = n;
                    if (n.v == ME) {
                        me = n;
                    }
                    if (n.v == FIRE) {
                        n.fireReachTime = 0;
                        n.checked = true;
                        n.torched = true;
                        fires.add(n);
                    }
                }
            }
            // 불이 각 지점까지 번지기까지의 최단 시간을 계산한다
            while (!fires.isEmpty()) {
                Node fire = fires.poll();
                for (int k = 0; k < 4; k++) {
                    int _r = fire.r + dr[k];
                    int _c = fire.c + dc[k];
                    if (isBoundary(_r, _c) && !map[_r][_c].checked && map[_r][_c].v != WALL) {
                        map[_r][_c].checked = true;
                        map[_r][_c].torched = true;
                        map[_r][_c].fireReachTime = fire.fireReachTime + 1;
                        fires.add(map[_r][_c]);
                    }
                }
            }
            reset();
            // 시작지점으로부터 탈출할 수 있는지 판단한다
            Queue<Node> rq = new LinkedList<>();
            rq.add(me);
            me.checked = true;
            while (!rq.isEmpty()) {
                Node n = rq.poll();
                for (int k = 0; k < 4; k++) {
                    int _r = n.r + dr[k];
                    int _c = n.c + dc[k];
                    if (isBoundary(_r, _c) && !map[_r][_c].checked && map[_r][_c].v != WALL) {
                        // 불이 번질 곳이 아니거나
                        // 상근이가 이동 직후 불이 번지는 곳이 아니어야 함 ( +2초 )
                        if (!map[_r][_c].torched || (n.myReachTime + 2 <= map[_r][_c].fireReachTime)) {
                            map[_r][_c].checked = true;
                            map[_r][_c].myReachTime = n.myReachTime + 1;
                            rq.add(map[_r][_c]);
                        }
                    }
                }
            }
            // 상근이의 시작점이 출구에 있을 경우 탈출시간은 1초
            // 그 외에는 상근이가 탈출하는데 걸리는 최소시간을 반환
            if (me.isExit()) {
                out.write("1");
            } else {
                Object[] exitList = Arrays.stream(map).flatMap(Arrays::stream)
                        .filter(Node::isExit)
                        .filter(node -> node.myReachTime > 0)
                        .sorted(Comparator.comparing(node -> node.myReachTime))
                        .toArray();
                // 건물을 완전히 탈출하기 위한 시간 (=1초) 보정
                out.write(exitList.length == 0 ? IMPOSSIBLE :
                        String.valueOf(((Node) exitList[0]).myReachTime + 1)
                );
            }
            out.newLine();
        }
        out.flush();
    }

    static void reset() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].checked = false;
            }
        }
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Node {
        int r;
        int c;
        int v;
        int fireReachTime;
        int myReachTime;
        boolean checked;
        boolean torched;

        boolean isExit() {
            return (this.r == 0 || this.r == R - 1) || (this.c == 0 || this.c == C - 1);
        }
    }
}
