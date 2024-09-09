package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//    13334 : 철로
//    ref url : https://www.acmicpc.net/problem/13334
//    tag : sweeping
public class 철로 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int MAX = 0;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<E> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            E e = new E(in.readLine());
            queue.add(e);
        }
        int d = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> current = new PriorityQueue<>();
        while(!queue.isEmpty()) {
            E e = queue.poll();
            if(e.ed - e.st > d) continue;
            current.add(e.st);
            while(!current.isEmpty()) {
                if(e.ed - current.peek() <= d) break;
                current.poll();
            }
            MAX = Math.max(MAX, current.size());
        }
        System.out.println(MAX);
    }


    static class E implements Comparable<E> {
        int st;
        int ed;

        public E(String param) throws IOException {
            String[] params = param.split(" ");
            int u = Integer.parseInt(params[0]);
            int v = Integer.parseInt(params[1]);
            this.st = Math.min(u, v);
            this.ed = Math.max(u, v);
        }

        @Override
        public int compareTo(E e) {
            // 도착점이 작은 순, 도착점이 같으면 출발점이 작은 순
            int compare = Integer.compare(this.ed, e.ed);
            if (compare == 0) return Integer.compare(this.st, e.st);
            return compare;
        }
    }
}

