package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//    1916 : 최소비용 구하기
//    ref url : https://www.acmicpc.net/problem/1916\
//    tag : dijkstra
public class 최소비용구하기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        V[] vertex = new V[N];
        for (int i = 0; i < N; i++) {
            vertex[i] = new V(i);
        }
        for (int i = 0; i < M; i++) {
            String[] params = in.readLine().split(" ");
            V from = vertex[Integer.parseInt(params[0]) - 1];
            V to = vertex[Integer.parseInt(params[1]) - 1];
            int cost = Integer.parseInt(params[2]);
            from.roads.add(new E(to, cost));
        }
        String[] params = in.readLine().split(" ");
        V st = vertex[Integer.parseInt(params[0]) - 1];
        V ed = vertex[Integer.parseInt(params[1]) - 1];
        st.totalCost = 0;
        Queue<V> queue = new LinkedList<>();
        queue.add(st);
        while (!queue.isEmpty()) {
            V v = queue.poll();
            if (v.visited) continue;
            v.visited = true;
            PriorityQueue<E> roads = v.roads;
            for (E road : roads) {
                if (v.totalCost + road.cost < road.to.totalCost) {
                    road.to.visited = false;
                    road.to.totalCost = v.totalCost + road.cost;
                    queue.add(road.to);

                }
            }
        }
        System.out.println(ed.totalCost);
    }

    static class V {
        int no;
        int totalCost;
        boolean visited;
        PriorityQueue<E> roads;

        public V(int no) {
            this.no = no;
            this.roads = new PriorityQueue<>();
            this.totalCost = Integer.MAX_VALUE;
        }
    }

    static class E implements Comparable<E> {
        V to;
        int cost;

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}