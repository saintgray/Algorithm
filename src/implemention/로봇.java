package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// 1726 : 로봇
// ref url : https://www.acmicpc.net/problem/1726
public class 로봇 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Node[][] map;
    static int[][] walls;
    static int M;
    static int N;

    // {동, 서 , 남, 북}
    static int[] dm = new int[]{0, 0, 1, -1};
    static int[] dn = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        M = Integer.parseInt(param[0]);
        N = Integer.parseInt(param[1]);
        map = new Node[M][N];
        walls = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            String[] row = in.readLine().split(" ");
            int[] _walls = new int[N + 1];
            for (int j = 0; j < N; j++) {
                map[i][j] = new Node(i, j, Integer.parseInt(row[j]));
                _walls[j + 1] = _walls[j] + (map[i][j].isWall() ? 1 : 0);
                walls[i + 1][j + 1] = walls[i][j + 1] + _walls[j + 1];
            }

        }
        String[] robot = in.readLine().split(" ");
        String[] destination = in.readLine().split(" ");

        Node dest = map[Integer.parseInt(destination[0]) - 1][Integer.parseInt(destination[1]) - 1];
        RobotStatus status = new RobotStatus();
        status.m = Integer.parseInt(robot[0]) - 1;
        status.n = Integer.parseInt(robot[1]) - 1;
        status.d = Integer.parseInt(robot[2]);
        map[status.m][status.n].controls = (dest == map[status.m][status.n]) ? getMinimumTurnCount(status.d, Integer.parseInt(destination[2])) : 0;
        Queue<RobotStatus> q = new LinkedList<>();
        q.add(status);
        while (!q.isEmpty()) {
            RobotStatus s = q.poll();
            for (int i = 3; i >= 1; i--) {
                for (int k = 0; k < 4; k++) {
                    int _m = s.m + (i * dm[k]);
                    int _n = s.n + (i * dn[k]);
                    // 중간에 벽이 있는 경우 skip
                    if (!canMoveAble(s.m, s.n, _m, _n)) continue;
                    Node move = map[_m][_n];
                    // 지금까지 control 한 횟수 + 이동을 위해 방향전환해야 하는 제어 회수 + 이동을 위한 제어 횟수
                    int _controls = s.controls + getMinimumTurnCount(s.d, k + 1) + 1;
                    // 목적지가 도착지점일 경우 방향전환 횟수 추가
                    if (move == dest) {
                        _controls += getMinimumTurnCount(k + 1, Integer.parseInt(destination[2]));
                    }
                    if (move.controls <= _controls) continue;
                    move.controls = _controls;
                    RobotStatus push = new RobotStatus();
                    push.m = _m;
                    push.n = _n;
                    push.d = k + 1;
                    push.controls = _controls;
                    q.add(push);
                }
            }
        }
        System.out.println(dest.controls);
    }


    static boolean canMoveAble(int m1, int n1, int m2, int n2) {
        if (!isBoundary(m2, n2)) return false;
        if(map[m2][n2].isWall()) return false;
        return walls[m2 + 1][n2 + 1] - (walls[m1][n2 + 1] + walls[m2 + 1][n1]) + walls[m1][n1] == 0;
    }

    static boolean isBoundary(int m, int n) {
        return m >= 0 && n >= 0 && m < M && n < N;
    }

    static int getMinimumTurnCount(int current, int target) {
        // 동(1), 서(2) 남(3) 북(4)
        if (target == current) return 0;
        if (((target - 1) / 2 == (current - 1) / 2)) return 2;  // 반대 방향을 바라보아야 할 때
        return 1;
    }

    static class RobotStatus {
        int m;
        int n;
        int d;  // 바라보는 방향
        int controls;
        // int moved;
    }

    static class Node {
        int m;
        int n;
        int v;
        int controls;

        boolean isWall() {
            return this.v == 1;
        }

        public Node(int m, int n, int v) {
            this.m = m;
            this.n = n;
            this.v = v;
            this.controls = Integer.MAX_VALUE;
        }
    }
}
