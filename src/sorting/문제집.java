package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//    1766 : 문제집
//    ref url : https://www.acmicpc.net/problem/1766
//    ref alg : topology sort, priority queue
public class 문제집 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] questions;


    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        int N = Integer.parseInt(param[0]);
        int M = Integer.parseInt(param[1]);
        questions = new V[N + 1];
        for (int v = 1; v <= N; v++) {
            questions[v] = new V(v);
        }

        for (int i = 0; i < M; i++) {
            param = in.readLine().split(" ");
            int _u = Integer.parseInt(param[0]);
            int _v = Integer.parseInt(param[1]);
            V u = questions[_u];
            V v = questions[_v];
            u.outDegrees.add(_v);
            v.remainInDegrees++;
        }
        // 쉬운 문제부터 풀기 위한 우선 순위 큐 (문제 번호 = 난이도)
        PriorityQueue<V> queue = new PriorityQueue<>();
        for (int q = 1; q <= N; q++) {
            if (questions[q].remainInDegrees == 0) queue.add(questions[q]);
        }
        while (!queue.isEmpty()) {
            V easiest = queue.poll();  // = 먼저 풀어야 하는 문제 중 가장 쉬운 문제
            out.write(easiest.num + " ");
            for (Integer v : easiest.outDegrees) {
                if (--questions[v].remainInDegrees == 0) queue.add(questions[v]);
            }
        }
        out.flush();
    }

    // 문제집의 문제 (=정점)
    static class V implements Comparable<V> {
        int num;
        Set<Integer> outDegrees; // 정점의 진출간선 집합
        int remainInDegrees;    // 정점의 남은 진입간선 수

        public V(int num) {
            this.num = num;
            this.outDegrees = new HashSet<>();
        }

        @Override
        public int compareTo(V _v) {
            return Integer.compareUnsigned(this.num, _v.num);
        }
    }
}