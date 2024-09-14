// 간단한 문제에 대한 제출용 Main Class 입니다

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;
// https://www.acmicpc.net/problem/4386
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(in.readLine());
        V[] stars = new V[N];
        for (int i = 0; i < N; i++) {
            String[] parms = in.readLine().split(" ");
            stars[i] = new V(Double.parseDouble(parms[0]), Double.parseDouble(parms[2]));
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                // stars[i].line.add(new E(stars[i], stars[j],))
            }
        }
    }

    static class V {
        double x;
        double y;
        boolean checked;
        PriorityQueue<E> line = new PriorityQueue<>();

        public V(double x, double y) {
            this.x = x;
            this.y = y;
            this.line = new PriorityQueue<>();
        }
    }
    static class E implements Comparable<E> {
        V u;
        V v;
        int d;

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.d, e.d);
        }
    }
}
