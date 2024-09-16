package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//    1674 : 도시 분할 계획
//    ref url : https://www.acmicpc.net/problem/1674
//    tag : kruskal algorithm
public class 도시분할계획 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        int N = Integer.parseInt(param[0]);
        int M = Integer.parseInt(param[1]);
        V[] vertex = new V[N];
        for (int i = 0; i < N; i++) {
            vertex[i] = new V(i);
        }

        PriorityQueue<E> roads = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            param = in.readLine().split(" ");
            V u = vertex[Integer.parseInt(param[0]) - 1];
            V v = vertex[Integer.parseInt(param[1]) - 1];
            roads.add(new E(u, v, Integer.parseInt(param[2])));
        }
        // 유지비가 적은 순으로 마을을 연결하고 연결된 도로 중
        // 유지비가 가장 큰 도로를 없애고 2개 마을로 분할하는 게 유지비의 총합을 최소로 할 수 있다.
        int total = 0;
        PriorityQueue<Integer> weightQueue = new PriorityQueue<>(Comparator.reverseOrder());
        while (!roads.isEmpty()) {
            E road = roads.poll();
            V pu = find(road.v);
            V pv = find(road.u);
            if (pu == pv) continue;
            total += road.w;
            union(pu, pv);
            weightQueue.add(road.w);
        }
        System.out.println(total - weightQueue.poll());
    }

    static V find(V v) {
        if (v.parent == v) return v;
        return v.parent = find(v.parent);
    }

    static void union(V u, V v) {
        if (u.parent.no < v.parent.no) v.parent = u;
        else u.parent = v;
    }

    static class V {
        int no;
        V parent;

        public V(int no) {
            this.no = no;
            this.parent = this;
        }
    }

    static class E implements Comparable<E> {
        V u;
        V v;
        int w;  // 유지비

        public E(V u, V v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.w, e.w);
        }
    }
}
