package graphTheroy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//    11779 : 최소비용 구하기 2
//    ref url : https://www.acmicpc.net/problem/11779
//    tag : dijkstra
public class 최소비용구하기2 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        V[] vertex = new V[n + 1];
        for (int i = 1; i <= n; i++) {
            vertex[i] = new V(i);
        }
        for (int i = 0; i < m; i++) {
            String[] param = in.readLine().split(" ");
            V u = vertex[Integer.parseInt(param[0])];
            V v = vertex[Integer.parseInt(param[1])];
            int cost = Integer.parseInt(param[2]);
            u.roads.add(new E(v, cost));
        }
        String[] param = in.readLine().split(" ");
        V st = vertex[Integer.parseInt(param[0])];
        V ed = vertex[Integer.parseInt(param[1])];

        Queue<V> queue = new LinkedList<>();
        st.cost = 0;
        queue.add(st);
        while (!queue.isEmpty()) {
            V poll = queue.poll();
            if (poll.visited) continue;
            poll.visited = true;
            PriorityQueue<E> roads = poll.roads;
            for (E road : roads) {
                if (road.cost + poll.cost < road.to.cost) {
                    road.to.cost = road.cost + poll.cost;
                    road.to.visited = false;
                    road.to.prev = poll;
                    queue.add(road.to);
                }
            }
        }
        // 최단경로 역추적
        Stack<V> route = new Stack<>();
        route.add(ed);
        reverseTrack(ed, route);
        out.write(String.format("%d\n%d\n", ed.cost, route.size()));
        while (!route.isEmpty()) {
            out.write(route.pop().no + " ");
        }
        out.flush();
    }

    static void reverseTrack(V v, Stack<V> stack) {
        if (v.prev == v) return;
        stack.add(v.prev);
        reverseTrack(v.prev, stack);
    }

    static class V {
        int no;
        int cost;  // 출발 정점으로부터 이 정점에 도달하기까지의 최소 비용
        boolean visited;
        PriorityQueue<E> roads; // 이 정점에서 갈수 있는 정점의 간선정보
        V prev; // 최소 비용으로 이 정점에 도착하기 위해 거쳐야 하는 직전 정점

        public V(int no) {
            this.no = no;
            this.roads = new PriorityQueue<>();
            this.cost = Integer.MAX_VALUE;
            this.prev = this;
        }
    }

    static class E implements Comparable<E> {
        V to;
        int cost;

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.cost, e.cost);
        }

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}