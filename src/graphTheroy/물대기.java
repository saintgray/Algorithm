package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//    1368 : 물대기
//    ref url : https://www.acmicpc.net/problem/1368
//    tag : minimum spanning tree (MST)
public class 물대기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        V[] vertex = new V[N + 1];
        vertex[0] = new V(0);   // 우물
        PriorityQueue<Pipe> pipes = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            vertex[i + 1] = new V(i + 1);
        }
        for (int i = 0; i < N; i++) {
            int cost = Integer.parseInt(in.readLine());
            V v = vertex[i + 1];
            // 논과 우물의 간선 추가
            pipes.add(new Pipe(vertex[0], vertex[i + 1], cost));
        }

        for (int i = 0; i < N; i++) {
            int[] costs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < costs.length; j++) {
                if (i == j) continue;
                // 두개의 논 사이 간선 정보 추가
                pipes.add(new Pipe(vertex[i + 1], vertex[j + 1], costs[j]));
                pipes.add(new Pipe(vertex[j + 1], vertex[i + 1], costs[j]));
            }
        }

        int totalCost = 0;
        while (!pipes.isEmpty()) {
            Pipe pipe = pipes.poll();
            V from = pipe.from;
            V to = pipe.to;
            if (find(from) == find(to)) continue;
            union(from.parent, to.parent);
            totalCost += pipe.cost;
        }
        System.out.println(totalCost);
    }

    static V find(V v) {
        if (v.parent == v) return v;
        return v.parent = find(v.parent);
    }

    static void union(V p1, V p2) {
        if (p1.no < p2.no) p2.parent = p1;
        else p1.parent = p2;
    }

    static class V {
        int no;
        V parent;

        public V(int no) {
            this.no = no;
            this.parent = this;
        }
    }

    static class Pipe implements Comparable<Pipe> {
        V from;
        V to;
        int cost;

        public Pipe(V from, V to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;

        }

        @Override
        public int compareTo(Pipe pipe) {
            return Integer.compare(this.cost, pipe.cost);
        }
    }
}