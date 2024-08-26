package dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

//    1753 : 최단경로
//    ref url : https://www.acmicpc.net/problem/1753
public class 최단경로 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] arr;
    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        int V = Integer.parseInt(params[0]);
        int E = Integer.parseInt(params[1]);
        arr = new V[V + 1];
        for (int v = 1; v <= V; v++) {
            arr[v] = new V();
        }
        int K = Integer.parseInt(in.readLine());
        for (int e = 0; e < E; e++) {
            params = in.readLine().split(" ");
            int u = Integer.parseInt(params[0]);
            int v = Integer.parseInt(params[1]);
            int w = Integer.parseInt(params[2]);
            V from = arr[u];
            from.to.add(new E(v, w));
        }
        // 시작점의 최단거리 = 0
        arr[K].shortcut = 0;
        // 탐색 집합 (시작점에서 정점 V 까지의 최단 거리순)
        PriorityQueue<V> S = new PriorityQueue<>();
        // 시작 정점으로부터 갈 수 있는 정점을 탐색 집합에 삽입
        for (E e : arr[K].to) {
            arr[e.v].shortcut = Math.min(arr[e.v].shortcut, e.w);
            S.add(arr[e.v]);
        }
        while (!S.isEmpty()) {
            V from = S.poll();
            if (from.visited) continue;
            from.visited = true;
            for (E e : from.to) {
                V to = arr[e.v];
                int distance = from.shortcut + e.w;
                if (distance < to.shortcut) {
                    // 새로 갱신된 정점 v 로부터 갈 수 있는 모든 점들의
                    // 최단경로의 재갱신이 필요하므로 아직 방문처리 않았다고 표기하는 것이 중요
                    to.visited = false;
                    to.shortcut = distance;
                    S.add(to);
                }
            }
        }
        // print result
        for (int v = 1; v <= V; v++) {
            out.write(arr[v].shortcut == Integer.MAX_VALUE ? "INF" : String.valueOf(arr[v].shortcut));
            out.newLine();
        }
        out.flush();
    }
    static class V implements Comparable<V>{
        int shortcut;           // 시작정점 V' 부터 정점 V 까지의 최단거리
        PriorityQueue<E> to;    // 정정 V 의 간선 정보
        boolean visited;

        public V() {
            this.to = new PriorityQueue<>();
            this.shortcut = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(V _v) {
            return Integer.compare(this.shortcut, _v.shortcut);
        }
    }

    static class E implements Comparable<E> {
        int v;
        int w;

        public E(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(E _e) {
            return Integer.compare(this.w, _e.w);
        }
    }
}


