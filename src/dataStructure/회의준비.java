package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

//    2610 : 회의준비
//    ref url : https://www.acmicpc.net/problem/2610
//    tag : floyd-warshall, disjoint set
public class 회의준비 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final int INF = 99;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        V[] arr = new V[N + 1];
        int[][] routeTime = new int[N + 1][N + 1];

        // init
        for (int i = 0; i < N; i++) {
            arr[i + 1] = new V(i + 1);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) routeTime[i][j] = INF;
            }
        }
        //

        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            String[] E = in.readLine().split(" ");
            int _u = Integer.parseInt(E[0]);
            int _v = Integer.parseInt(E[1]);
            V u = arr[_u];
            V v = arr[_v];
            // disjoint set
            V p1 = find(u);
            V p2 = find(v);
            union(p1, p2);
            // 최단 거리 갱신
            routeTime[_u][_v] = Math.min(routeTime[_u][_v], 1);
            routeTime[_v][_u] = Math.min(routeTime[_v][_u], 1);
        }
        for (int i = 1; i <= N; i++) {
            arr[i].parent = find(arr[i]);
        }

        // 모든 정점 사이의 최단거리 찾기
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    int time = routeTime[s][m] + routeTime[m][e];
                    if (routeTime[s][e] > time) {
                        routeTime[s][e] = time;
                        routeTime[e][s] = time;
                    }
                }
            }
        }

        Map<Integer, Set<Integer>> group = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            Set<Integer> set = Optional.ofNullable(group.get(arr[i].parent.no)).orElseGet(HashSet::new);
            set.add(arr[i].no);
            group.put(arr[i].parent.no, set);
        }
        // 위원회의 수
        int K = group.size();
        // 위원회별 대표가 될 수 있는 사람 1명
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (Map.Entry<Integer, Set<Integer>> entry : group.entrySet()) {
            int representative = 0;
            int min = Integer.MAX_VALUE;
            Set<Integer> set = entry.getValue();
            for (Integer no : set) {
                int to = no;
                int _max = Integer.MIN_VALUE;
                for (int i = 1; i <= N; i++) {
                    if (i == to || !set.contains(i)) continue;
                    _max = Math.max(_max, routeTime[i][to]);
                }
                if (min > _max) {
                    representative = to;
                    min = _max;
                }
            }
            q.add(representative);
        }

        System.out.println(K);
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    static void union(V p1, V p2) {
        if (p1 == p2) return;
        if (p1.no < p2.no) p2.parent = p1;
        else p1.parent = p2;
    }

    static V find(V v) {
        if (v.parent == v) return v;
        return (v.parent = find(v.parent));
    }

    static class V {
        int no;
        V parent;

        public V(int no) {
            this.no = no;
            this.parent = this;
        }
    }
}
