package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//    2252 : 줄 세우기
//    ref url : https://www.acmicpc.net/problem/2252
//    ref alg : topology sort
public class 줄세우기 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] students;

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        int N = Integer.parseInt(param[0]);
        int M = Integer.parseInt(param[1]);
        students = new V[N + 1];
        for (int v = 1; v <= N; v++) {
            students[v] = new V(v);
        }

        for (int i = 0; i < M; i++) {
            String[] orders = in.readLine().split(" ");
            int _u = Integer.parseInt(orders[0]);
            int _v = Integer.parseInt(orders[1]);
            V u = students[_u];
            V v = students[_v];
            u.outDegrees.add(_v);
            v.remainInDegrees++;
        }

        PriorityQueue<V> queue = new PriorityQueue<>();
        for (int q = 1; q <= N; q++) {
            if (students[q].remainInDegrees == 0) queue.add(students[q]);
        }

        int[] order = new int[N + 1];
        int idx = 0;
        while (!queue.isEmpty()) {
            V student = queue.poll();
            order[++idx] = student.num;
            for (Integer v : student.outDegrees) {
                if (--students[v].remainInDegrees == 0) queue.add(students[v]);
            }
        }
        for (int i = 1; i <= N; i++) {
            out.write(String.valueOf(order[i])+ " ");
        }
        out.flush();
    }

    static class V implements Comparable<V> {
        int num;
        Set<Integer> outDegrees;
        int remainInDegrees;

        public V(int num) {
            this.num = num;
            this.outDegrees = new HashSet<>();
        }

        @Override
        public int compareTo(V _v) {
            return Integer.compare(this.num, _v.num);
        }
    }
}
