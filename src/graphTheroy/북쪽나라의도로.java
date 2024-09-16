package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//    1595 : 북쪽나라의 도로
//    ref url : https://www.acmicpc.net/problem/1595
//    tag : tree (radius of tree)
public class 북쪽나라의도로 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        V[] vertex = new V[10001];
        for (int i = 1; i <= 10000; i++) {
            vertex[i] = new V(i);
        }
        String input = null;
        V start = null;
        while ((input = in.readLine()) != null && input.length() > 0) {
            String[] param = input.split(" ");
            V u = vertex[Integer.parseInt(param[0])];
            V v = vertex[Integer.parseInt(param[1])];
            int d = Integer.parseInt(param[2]);
            u.lines.add(new E(v, d));
            v.lines.add(new E(u, d));
            if (start == null) start = u;
        }

        // 입력이 없을 경우
        if (start == null) {
            System.out.println(0);
            System.exit(0);
        }

        // 임의 정점으로부터 가장 거리가 먼 정점(=reef node)을 탐색
        int radius = 0;
        V reef = null;
        start.d = 0;
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
                vertex[i].d = Integer.MIN_VALUE;
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
        int d;
        boolean visited;
        PriorityQueue<E> lines;

        public V(int no) {
            this.no = no;
            this.lines = new PriorityQueue<>();
            this.d = Integer.MIN_VALUE;
        }
    }

    static class E implements Comparable<E> {
        V to;
        int d;  // 거리

        public E(V to, int d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(E e) {
            return -1 * Integer.compare(this.d, e.d);
        }
    }
}