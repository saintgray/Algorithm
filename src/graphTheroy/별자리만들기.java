package graphTheroy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//    4386 : 별자리 만들기
//    ref url : https://www.acmicpc.net/problem/4386
//    tag : kruskal algorithm
public class 별자리만들기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(in.readLine());
        V[] stars = new V[N];
        for (int i = 0; i < N; i++) {
            String[] parms = in.readLine().split(" ");
            stars[i] = new V(i, Double.parseDouble(parms[0]), Double.parseDouble(parms[1]));
        }
        PriorityQueue<E> lines = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                lines.add(new E(stars[i], stars[j]));
            }
        }

        double len = 0;
        while (!lines.isEmpty()) {
            E line = lines.poll();
            if (find(line.u) == find(line.v)) continue;
            union(line.u.parent, line.v.parent);
            len += Math.sqrt(line.d);
        }
        System.out.println(len);
    }

    static V find(V v) {
        if (v.parent == v) return v;
        return (v.parent = find(v.parent));
    }

    static void union(V p1, V p2) {
        if (p1.no < p2.no) p2.parent = p1;
        else p1.parent = p2;
    }

    static class V {

        int no;
        double x;
        double y;
        V parent;

        double distanceTo(V v) {
            return Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2);
        }

        public V(int no, double x, double y) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.parent = this;
        }
    }

    static class E implements Comparable<E> {
        V u;
        V v;
        double d;  // u 와 v 거리의 제곱

        public E(V u, V v) {
            this.u = u;
            this.v = v;
            this.d = u.distanceTo(v);
        }

        @Override
        public int compareTo(E e) {
            return Double.compare(this.d, e.d);
        }
    }
}