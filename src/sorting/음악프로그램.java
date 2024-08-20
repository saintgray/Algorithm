package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//    2623 : 음악프로그램
//    ref url : https://www.acmicpc.net/problem/2623
//    ref alg : topology sort
public class 음악프로그램 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] singers;

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        int N = Integer.parseInt(param[0]);
        int M = Integer.parseInt(param[1]);
        singers = new V[N + 1];
        for (int v = 1; v <= N; v++) {
            singers[v] = new V(v);
        }

        for (int i = 0; i < M; i++) {
            String[] orders = in.readLine().split(" ");
            int size = Integer.parseInt(orders[0]);
            for (int j = 2; j <= size; j++) {
                int _u = Integer.parseInt(orders[j - 1]);
                int _v = Integer.parseInt(orders[j]);
                V u = singers[_u];
                V v = singers[_v];
                if (!u.outDegrees.contains(_v)) {
                    u.outDegrees.add(_v);
                    v.remainInDegrees++;
                }
            }
        }

        Queue<V> queue = new LinkedList<>();
        for (int q = 1; q <= N; q++) {
            if (singers[q].remainInDegrees == 0) queue.add(singers[q]);
        }

        int remainVisited = N;
        int[] order = new int[N + 1];
        int idx = 0;
        while (!queue.isEmpty()) {
            V singer = queue.poll();
            remainVisited--;
            order[++idx] = singer.num;
            for (Integer v : singer.outDegrees) {
                if (--singers[v].remainInDegrees == 0) queue.add(singers[v]);
            }
            if (remainVisited > 0 && queue.isEmpty()) {
                break;
            }
        }
        // 위상 정렬이 끝나지 않았는데 종료 처리된 경우 = 순환 그래프
        if (remainVisited > 0) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= N; i++) {
            out.write(String.valueOf(order[i]));
            out.newLine();
        }
        out.flush();
    }

    static class V {
        int num;
        Set<Integer> outDegrees; // 정점의 진출간선 집합
        int remainInDegrees;     // 정점의 남은 진입간선 수

        public V(int num) {
            this.num = num;
            this.outDegrees = new HashSet<>();
        }
    }
}
