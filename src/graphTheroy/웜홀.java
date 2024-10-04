package graphTheroy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    1865 : 웜홀
//    ref url : https://www.acmicpc.net/problem/1865
//    tag : bellman-ford
public class 웜홀 {
    // pseudo code of bellman-ford
    // for each u ∈ V
    //     d[u] ← ∞         정점 집합 V 의 모든 정점 u 에 대해 최단거리를 무한대로 초기화한다
    // d[r] ← 0             시작 정점 r 의 최단거리를 0으로 초기화
    // for i ← 1 to |V| - 1     간선 1개 ~ 정점의 개수 - 1 개까지
    //     for each (u,v) ∈ E   간선 집합 E 내 모든 간선에 대해 도착지점의 최단 거리가 갱신될 수 있을 때 도착 정점의 최단거리를 갱신한다
    //        if (d[u] + w(u,v) < d[v]) then {
    //            d[v] ← d[u] + w(u,v);
    //        }

    // 최대 (V - 1) 개의 간선을 사용해서 이를 수 있는 경로가 최단 경로가 되는 이유 (V = 정점개수)
    //   V-1 개보다 많은 간선을 사용하는 경로가 있다고 하자.
    //   정점의 개수는 총 V 개이므로 최소 1개의 사이클이 존재할 것이다.
    //   최단 경로 문제의 가정은 음의 사이클이 존재하지 않는 것을 가정으로 하므로
    //   V-1 개보다 많은 간선을 사용하는 경로에서 모든 사이클을 제외하면 경로가 더 짧아지거나 같다(사이클의 가중치가 0인경우)
    //   따라서 V-1 개 이하의 간선을 사용하는 최단 경로 중 궁극적인 최단 경로가 반드시 존재한다.
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int INF;

    // 벨만 포드의 의사코드를 사용한 문제로 음의 사이클이 존재하는지 판단하는 문제
    // 1 ~ (정점개수 - 1) 번 을 반복하여 한 정점에서 최단 거리를 찾는 점을 이용하여
    // 모든 정점의 최소 도달 시간을 ∞ 로 설정하고
    // 정점의 개수(=N)만큼 벨만 포드 의사코드를 실행한다.
    // 이때 N 번째 실행 시에도 최소 시간 갱신이 일어나는 경우가 음의 사이클이 존재하는 경우이다.
    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(in.readLine());
        solve:
        while (TC-- > 0) {
            String[] params = in.readLine().split(" ");
            int N = Integer.parseInt(params[0]); // (1 ≤ N ≤ 500)
            int M = Integer.parseInt(params[1]); // (1 ≤ M ≤ 2500)
            int W = Integer.parseInt(params[2]); // (1 ≤ W ≤ 200)
            // N - 1 개의 간선을 사용할 때 무한대 값 = 10000 * (N- 1) + 1
            INF = 10000 * (N - 1) + 1;
            V[] vtx = new V[N];
            for (int i = 0; i < N; i++) {
                vtx[i] = new V(i, INF);
            }
            // 도로의 간선정보
            E[] edges = new E[2 * M + W];
            int eIdx = 0;
            for (int i = 0; i < M; i++) {
                params = in.readLine().split(" ");
                int S = Integer.parseInt(params[0]);
                int E = Integer.parseInt(params[1]);
                int T = Integer.parseInt(params[2]);
                edges[eIdx++] = new E(vtx[S - 1], vtx[E - 1], T);
                edges[eIdx++] = new E(vtx[E - 1], vtx[S - 1], T);
            }
            for (int i = 0; i < W; i++) {
                params = in.readLine().split(" ");
                int S = Integer.parseInt(params[0]);
                int E = Integer.parseInt(params[1]);
                int T = -1 * Integer.parseInt(params[2]);   // 웜홀
                edges[eIdx++] = new E(vtx[S - 1], vtx[E - 1], T);
            }

            for (int j = 1; j <= N; j++) {
                for (E e : edges) {
                    if (e.from.t + e.t < e.to.t) {
                        e.to.t = e.from.t + e.t;
                        // N 번째 갱신 시 최소 시간 정보가 갱신되었다면 음의 싸이클이 존재하는 정점이다
                        if (j == N) {
                            out.write("YES");
                            out.newLine();
                            continue solve;
                        }
                    }
                }
            }
            // 어떤 정점도 음의 사이클이 존재하지 않음
            out.write("NO");
            out.newLine();
        }
        out.flush();
    }


    /* 정점 정보 */
    static class V {
        int no; // 정점 번호
        int t;  // 최단 시간

        public V(int no, int t) {
            this.no = no;
            this.t = t;
        }
    }

    /* 간선 정보 */
    static class E {
        V from;
        V to;
        int t;

        public E(V from, V to, int t) {
            this.from = from;
            this.to = to;
            this.t = t;
        }
    }
}
