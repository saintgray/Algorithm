package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    1600 : 말이 되고픈 원숭이
//    ref url : https://www.acmicpc.net/problem/1600
public class 말이되고픈원숭이 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int K;
    static int W;
    static int H;
    static Node[][] map;
    static int[] dh = new int[]{1, -1, 0, 0, 2, 2, -2, -2, -1, 1, -1, 1};
    static int[] dw = new int[]{0, 0, 1, -1, -1, 1, -1, 1, 2, 2, -2, -2};

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(in.readLine());
        String[] param = in.readLine().split(" ");
        H = Integer.parseInt(param[1]);
        W = Integer.parseInt(param[0]);
        map = new Node[H][W];
        for (int h = 0; h < H; h++) {
            String[] row = in.readLine().split(" ");
            for (int w = 0; w < W; w++) {
                map[h][w] = new Node(h, w, Integer.parseInt(row[w]));
            }
        }

        Arrays.fill(map[0][0].memory, 0);
        Status s = new Status();
        s.h = 0;
        s.w = 0;
        s.moved = 0;
        s.horseMoved = 0;
        Queue<Status> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            Status _s = q.poll();
            for (int k = 0; k < 4; k++) {
                addTarget(q, _s, k);
            }
            if (_s.horseMoved == K) continue;
            for (int k = 4; k < 12; k++) {
                addTarget(q, _s, k);
            }
        }
        int shortestMoved = Integer.MAX_VALUE;
        for (int horseMove = 0; horseMove <= K; horseMove++) {
            shortestMoved = Math.min(shortestMoved, map[H - 1][W - 1].memory[horseMove]);
        }
        System.out.println(shortestMoved == Integer.MAX_VALUE ? "-1" : shortestMoved);

    }

    static boolean isBoundary(int h, int w) {
        return h >= 0 && w >= 0 && h < H && w < W;
    }

    static void addTarget(Queue<Status> q, Status s, int mIdx) {
        if (isUpdateTarget(s, mIdx)) {
            Status push = new Status();
            push.moved = s.moved + 1;
            push.h = s.h + dh[mIdx];
            push.w = s.w + dw[mIdx];
            if (mIdx >= 4) push.horseMoved = s.horseMoved + 1;
            else push.horseMoved = s.horseMoved;
            map[push.h][push.w].memory[push.horseMoved] = push.moved;
            q.add(push);
        }
    }

    static boolean isUpdateTarget(Status s, int mIdx) {
        int h = s.h + dh[mIdx];
        int w = s.w + dw[mIdx];
        if (!isBoundary(h, w) || map[h][w].isWall()) return false;
        if (mIdx < 4) return s.moved + 1 < map[h][w].memory[s.horseMoved];
        return s.moved + 1 < map[h][w].memory[s.horseMoved + 1];
    }

    static class Status {
        int h;
        int w;
        int moved;
        int horseMoved;
    }

    static class Node {
        int h;
        int w;
        int v;
        Integer[] memory; // 말의 움직임으로 이동한 횟수 별 원숭이의 동작수의 최소값

        boolean isWall() {
            return this.v == 1;
        }

        public Node(int h, int w, int v) {
            this.h = h;
            this.w = w;
            this.v = v;
            this.memory = new Integer[K + 1];
            Arrays.fill(memory, Integer.MAX_VALUE);
        }
    }
}
