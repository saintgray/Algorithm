package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    17144 : 미세먼지 안녕!
//    ref url : https://www.acmicpc.net/problem/17144
public class 미세먼지안녕 {

    static int R = 0;
    static int C = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = params[0];
        C = params[1];
        Node[][] room = new Node[R][C];
        Node upperCleaner = null;
        Node lowerCleaner = null;
        for (int r = 0; r < R; r++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int c = 0; c < C; c++) {
                int val = row[c];
                if (val == -1) {
                    Node node = new AirCleaner(r, c, val);
                    if (upperCleaner == null) {
                        upperCleaner = node;
                        ((AirCleaner) upperCleaner).flowsClockwise = false;
                    } else {
                        lowerCleaner = node;
                        ((AirCleaner) lowerCleaner).flowsClockwise = true;
                    }
                    room[r][c] = node;
                } else {
                    room[r][c] = new Node(r, c, val);
                }
            }
        }
        // init node flows
        ((AirCleaner) upperCleaner).initNodeFLow();
        ((AirCleaner) lowerCleaner).initNodeFLow();

        int time = params[2];
        while (time-- > 0) {
            // 1. start Spread
            List<Node> dustList = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    Node node = room[r][c];
                    if (!node.isAirCleaner()) {
                        dustList.add(node);
                    }
                }
            }
            for (Node dust : dustList) {
                int val = dust.val;
                int spreads = val / 5;
                for (int i = 0; i < 4; i++) {
                    int dr = dust.r + dy[i];
                    int dc = dust.c + dx[i];
                    if (isBoundary(dr, dc) && !room[dr][dc].isAirCleaner()) {
                        room[dr][dc].spreadSum += spreads;
                        val -= spreads;
                    }
                }
                dust.val = val;
            }
            // 2. calculate and init spreadSum
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (!room[r][c].isAirCleaner()) {
                        room[r][c].val += room[r][c].spreadSum;
                        room[r][c].spreadSum = 0;
                    }
                }
            }
            // 3. operate cleaner
            ((AirCleaner) upperCleaner).doClean(room);
            ((AirCleaner) lowerCleaner).doClean(room);
        }
        System.out.println(
                Arrays.stream(room).flatMap(Arrays::stream)
                        .filter((node) -> !node.isAirCleaner())
                        .map((node) -> node.val)
                        .reduce(Integer::sum)
                        .get()
        );
    }

    public static class Node {
        int r;
        int c;
        int val;
        int spreadSum;

        public Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean isAirCleaner() {
            return this.val == -1;
        }
    }

    public static class AirCleaner extends Node {

        boolean flowsClockwise;
        List<Node> nodeFlows = new ArrayList<>();

        public AirCleaner(int r, int c, int val) {
            super(r, c, val);
        }

        public void initNodeFLow() {
            int[] dy = !this.flowsClockwise ? new int[]{0, -1, 0, 1, 0} : new int[]{0, 1, 0, -1, 0};
            int[] dx = {1, 0, -1, 0, 1};
            int topRow = !this.flowsClockwise ? 0 : this.r;
            int bottomRow = !this.flowsClockwise ? this.r : R - 1;

            boolean cycleRotated = false;
            int r = this.r;
            int c = this.c;
            int k = 0;
            while (!cycleRotated) {
                r += dy[k];
                c += dx[k];
                if (r == this.r && c == this.c) {
                    cycleRotated = true;
                } else if (!isCleanerBoundary(r, c, topRow, bottomRow)) {
                    r -= dy[k];
                    c -= dx[k];
                    k++;
                } else {
                    this.nodeFlows.add(new Node(r, c));
                }
            }
        }

        boolean isCleanerBoundary(int dr, int dc, int topRow, int bottomRow) {
            return dr >= topRow && dr <= bottomRow && dc >= 0 && dc < C;
        }

        void doClean(Node[][] room) {
            List<Node> flows = this.nodeFlows;
            for (int i = flows.size() - 1; i >= 0; i--) {
                try {
                    Node node = room[flows.get(i).r][flows.get(i).c];
                    Node prevNode = room[flows.get(i - 1).r][flows.get(i - 1).c];
                    node.val = prevNode.val;
                } catch (Exception e) {
                    // catch for get i == 0
                    room[flows.get(i).r][flows.get(i).c].val = 0;
                }
            }
        }
    }

    public static boolean isBoundary(int dr, int dc) {
        return dr < R && dc < C && dr >= 0 && dc >= 0;
    }
}
