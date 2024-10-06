import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
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
        Queue<V> queue = new LinkedList<>();
        vtx[1].min = 0;
        queue.add(vtx[1]);
        // 도로를 포장하지 않고 서울에서 포천까지 가는 최단시간 구하기
        while (!queue.isEmpty()) {
            V from = queue.poll();
            if (from.checked) continue;
            from.checked = true;
            PriorityQueue<E> roads = from.roads;
            for (E road : roads) {
                V to = road.to;
                if (from.min + road.time < to.min) {
                    to.checked = false;
                    to.min = from.min + road.time;
                    to.prev = from;
                    queue.add(to);
                }
            }
        }

    }

    /* 정점 정보 */
    static class V {
        long min;    // 정점 도달까지의 최단 시간
        boolean checked;
        V prev;      // 최단경로로 도달하기 위해 거쳐야 하는 직전 정점
        PriorityQueue<E> roads;

        public V(int k) {
            this.roads = new PriorityQueue<>();
            this.min = Long.MAX_VALUE;
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

}