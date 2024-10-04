package graphTheroy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//    2406 : 안정적인 네트워크
//    ref url : https://www.acmicpc.net/problem/2406
//    tag : kruskal algorithm
public class 안정적인네트워크 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] params = in.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        Com[] coms = new Com[n + 1];
        for (int i = 1; i <= n; i++) {
            coms[i] = new Com(i);
        }
        String[][] lines = new String[m][];
        for (int i = 0; i < m; i++) {
            lines[i] = in.readLine().split(" ");
        }
        int[][] cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String[] row = in.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                cost[i][j] = Integer.parseInt(row[j - 1]);
            }
        }
        // 1. 지사간 네트워크가 끊기는 경우
        // 2. 지사 컴퓨터가 고장나는 경우
        // 3. 본사와 지사간 네크워크가 끊기는 경우
        // 4. 본사 컴퓨터가 고장나는 경우
        // 본사와 모든 지사 컴퓨터는 연결되어 있으므로 1,2,3 은 고려대상이 아님
        // 즉, 지사끼리의 네트워크 구축 비용의 최소값을 구하면 됨
        // 단, 1개 지사밖에 없을 경우 3번 경우를 고려해야 하므로 예외처리

        // 지사가 1개 밖에 없을 경우
        if (n == 2) {
            System.out.printf("%d %d\n%d %d", cost[1][2], 1, 1, 2);
            return;
        }

        // 이미 연결되어 있는 지사 네트워크의 구축 비용 0으로 갱신
        for (int i = 0; i < lines.length; i++) {
            int u = Integer.parseInt(lines[i][0]);
            int v = Integer.parseInt(lines[i][1]);
            cost[u][v] = 0;
            cost[v][u] = 0;
        }

        PriorityQueue<E> q = new PriorityQueue<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                q.add(new E(coms[i], coms[j], cost[i][j]));
            }
        }
        int min = 0;    // 안정적인 네트워크 최소 구축 비용
        List<Pair> connect = new ArrayList<>();
        while (!q.isEmpty()) {
            E net = q.poll();
            Com u = net.from;
            Com v = net.to;
            if (find(u) != find(v)) {
                min += net.cost;
                union(u.parent, v.parent);
                if (net.cost > 0) connect.add(new Pair(u, v));
            }
        }

        // submit
        System.out.printf(String.format("%d %d\n", min, connect.size()));
        if (connect.isEmpty()) return;
        for (Pair pair : connect) {
            out.write(String.format("%d %d", pair.c1.no, pair.c2.no));
            out.newLine();
        }
        out.flush();
    }

    static Com find(Com c) {
        return c.parent == c ? c : (c.parent = find(c.parent));
    }

    static void union(Com c1, Com c2) {
        if (c1.parent.no < c2.parent.no) c2.parent = c1;
        else c1.parent = c2;
    }

    static class Com {
        int no;
        Com parent;

        public Com(int no) {
            this.no = no;
            this.parent = this;
        }
    }

    static class E implements Comparable<E> {
        Com from;
        Com to;
        int cost;

        public E(Com from, Com to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.cost, e.cost);
        }
    }

    static class Pair {
        Com c1;
        Com c2;

        public Pair(Com c1, Com c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }
}
