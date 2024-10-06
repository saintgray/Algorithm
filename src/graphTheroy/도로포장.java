package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//    1162 : 도로포장
//    ref url : https://www.acmicpc.net/problem/1162
//    tag : dijkstra, dynamic programming
public class 도로포장 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final Long INF = Long.MAX_VALUE;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        N = Integer.parseInt(params[0]);
        M = Integer.parseInt(params[1]);
        K = Integer.parseInt(params[2]);
        V[] vtx = new V[N + 1];
        for (int n = 1; n <= N; n++) {
            vtx[n] = new V(K);
        }
        for (int m = 0; m < M; m++) {
            params = in.readLine().split(" ");
            V from = vtx[Integer.parseInt(params[0])];
            V to = vtx[Integer.parseInt(params[1])];
            int d = Integer.parseInt(params[2]);
            from.roads.add(new E(to, d));
            to.roads.add(new E(from, d));
        }
        PriorityQueue<S> status = new PriorityQueue<>();
        for (int i = 0; i <= K; i++) {
            vtx[1].min[i] = 0;
        }
        status.add(new S(vtx[1], 0, 0));
        // 다익스트라
        while (!status.isEmpty()) {
            S s = status.poll();
            V from = s.from;
            int k = s.k;
            if (from.checked[k]) continue;
            from.checked[k] = true;
            PriorityQueue<E> roads = s.from.roads;
            for (E road : roads) {
                V to = road.to;
                if (from.min[k] + road.time < to.min[k]) {
                    to.checked[k] = false;
                    to.min[k] = from.min[s.k] + road.time;
                    status.add(new S(to, to.min[k], k));
                }
                // 도로를 포장해서 갈 수 있는 경우
                if (k < K) {
                    if (from.min[k] < to.min[k + 1]) {
                        to.checked[k + 1] = false;
                        to.min[k + 1] = from.min[k];
                        status.add(new S(to, to.min[k + 1], k + 1));
                    }
                }
            }
        }
        // 포천에 도달하기까지 최소 시간 반환
        Long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            min = Math.min(min, vtx[N].min[i]);
        }
        System.out.println(min);
    }

    /* 정점 정보 */
    static class V {
        long[] min;    // 도로를 k 개 포장 시 이 정점에 도달하기까지 최단 시간
        boolean[] checked;  // 도로를 k 개 포장 시 방문했는지 여부
        PriorityQueue<E> roads;

        public V(int k) {
            this.min = new long[k + 1];
            this.checked = new boolean[k + 1];
            Arrays.fill(this.min, INF);
            this.roads = new PriorityQueue<>();
        }
    }

    /* 정점의 간선 정보 */
    static class E implements Comparable<E> {
        V to;
        long time;

        public E(V to, long t) {
            this.to = to;
            this.time = t;
        }

        @Override
        public int compareTo(E e) {
            return Long.compare(this.time, e.time);
        }
    }

    /* Queue 상태정보 */
    static class S implements Comparable<S> {
        V from;     // 도달 정점
        long time;  // 걸린 시간
        int k;      // 포장한 도로의 수

        public S(V from, long time, int k) {
            this.from = from;
            this.time = time;
            this.k = k;
        }

        @Override
        public int compareTo(S s) {
            return Long.compare(this.time, s.time);
        }
    }
}