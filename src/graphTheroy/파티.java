package graphTheroy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

//    1238 : 파티
//    ref url : https://www.acmicpc.net/problem/1238
//    tag : dijkstra
public class 파티 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);
        int X = Integer.parseInt(params[2]);
        // 1. X를 시작점으로 모든 정점까지의 최단거리를 구한다 (= 집에 돌아갈 때의 최단거리)
        // 2. 모든 간선정보의 시작점과 끝점 정보를 반전시킨다.
        // 3. X 를 시작점으로 모든 정점의 최단거리를 구한다 (= 각 정점에서 X까지 갈 때의 최단거리)
        V[] arr = new V[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new V();
        }
        for (int m = 0; m < M; m++) {
            params = in.readLine().split(" ");
            int u = Integer.parseInt(params[0]);
            int v = Integer.parseInt(params[1]);
            int t = Integer.parseInt(params[2]);
            arr[u].toX.add(new E(u, v, t));
            arr[v].toHome.add(new E(v, u, t));
        }
        // 1.
        arr[X].timeToX = 0;
        PriorityQueue<V> queue = new PriorityQueue<>(Comparator.comparing(v -> v.timeToHome));
        for (E e : arr[X].toHome) {
            arr[e.v].timeToHome = e.t;
            queue.add(arr[e.v]);
        }
        // 1.
        while (!queue.isEmpty()) {
            V from = queue.poll();
            if (from.visited) continue;
            from.visited = true;
            for (E e : from.toHome) {
                V to = arr[e.v];
                if (from.timeToHome + e.t < to.timeToHome) {
                    to.timeToHome = from.timeToHome + e.t;
                    to.visited = false;
                    queue.add(to);
                }
            }
        }
        // 2.
        // 입력값을 받을 때 반전시켰으므로 SKIP
        // 방문 여부만 초기화
        for (int n = 1; n <= N; n++) {
            arr[n].visited = false;
        }
        // 3.
        queue = new PriorityQueue<>(Comparator.comparing(v -> v.timeToX));
        for (E e : arr[X].toX) {
            arr[e.v].timeToX = e.t;
            queue.add(arr[e.v]);
        }
        while (!queue.isEmpty()) {
            V from = queue.poll();
            if (from.visited) continue;
            from.visited = true;
            for (E e : from.toX) {
                V to = arr[e.v];
                if (from.timeToX + e.t < to.timeToX) {
                    to.timeToX = from.timeToX + e.t;
                    to.visited = false;
                    queue.add(to);
                }
            }
        }
        // RESULT
        int max = Integer.MIN_VALUE;
        for (int n = 1; n <= N; n++) {
            max = Math.max(max, arr[n].timeToX + arr[n].timeToHome);
        }
        System.out.println(max);
    }

    static class V {
        int timeToX;   // X까지 가는데 걸리는 최단 시간
        int timeToHome; // 집까지 가는데 걸리는 최단시간
        PriorityQueue<E> toX;
        PriorityQueue<E> toHome;
        boolean visited;

        public V() {
            this.timeToX = Integer.MAX_VALUE;
            this.timeToHome = Integer.MAX_VALUE;
            this.toX = new PriorityQueue<>();
            this.toHome = new PriorityQueue<>();
        }
    }

    static class E implements Comparable<E> {
        int u;
        int v;
        int t;

        public E(int u, int v, int t) {
            this.u = u;
            this.v = v;
            this.t = t;
        }

        @Override
        public int compareTo(E _e) {
            return Integer.compare(this.t, _e.t);
        }
    }
}



