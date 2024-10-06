package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//    1167 : 트리의 지름
//    ref url : https://www.acmicpc.net/problem/1167
//    tag : tree, diameter of tree
public class 트리의지름 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        V[] vertex = new V[N + 1];
        for (int i = 1; i <= vertex.length - 1; i++) {
            vertex[i] = new V(i);
        }
        String input = null;
        V start = null;

        for (int i = 0; i < N; i++) {
            String[] param = in.readLine().split(" ");
            V u = vertex[Integer.parseInt(param[0])];
            for (int j = 1; j < param.length - 1; j+=2) {
                V v = vertex[Integer.parseInt(param[j])];
                u.lines.add(new E(v, Integer.parseInt(param[j + 1])));
                v.lines.add(new E(u, Integer.parseInt(param[j + 1])));
            }
            if (start == null) start = u;
        }

        // 임의 정점(=start)으로부터 가장 거리가 먼 정점(=reef node)을 탐색
        long radius = 0;
        V reef = null;
        start.d = 0L;
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        start.visited = true;
        while (!queue.isEmpty()) {
            V poll = queue.poll();
            PriorityQueue<E> lines = poll.lines;
            for (E line : lines) {
                V to = line.to;
                if (to.visited || to.d > poll.d + line.d) continue;
                to.visited = true;
                to.d = poll.d + line.d;
                if (radius < to.d) {
                    radius = to.d;
                    reef = to;
                }
                queue.add(line.to);
            }
        }

        // 초기화
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].visited = false;
                vertex[i].d = Long.MIN_VALUE;
            }
        }

        // reef node 에서 가장 거리가 먼 정점의 거리가 트리의 지름
        radius = 0;
        reef.d = 0;
        reef.visited = true;
        queue.add(reef);
        while (!queue.isEmpty()) {
            V poll = queue.poll();
            PriorityQueue<E> lines = poll.lines;
            for (E line : lines) {
                V to = line.to;
                if (to.visited || to.d >= poll.d + line.d) continue;
                to.visited = true;
                to.d = poll.d + line.d;
                radius = Math.max(radius, to.d);
                queue.add(line.to);
            }
        }
        System.out.println(radius);
    }

    static class V {
        int no;
        long d;
        boolean visited;
        PriorityQueue<E> lines;

        public V(int no) {
            this.no = no;
            this.lines = new PriorityQueue<>();
            this.d = Long.MIN_VALUE;
        }
    }

    static class E implements Comparable<E> {
        V to;
        long d;  // 거리

        public E(V to, long d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(E e) {
            return -1 * Long.compare(this.d, e.d);
        }
    }
}