package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 24480 : 알고리즘 수업 - 깊이 우선 탐색 2
// ref : https://www.acmicpc.net/problem/24480
// tag : dfs
public class 알고리즘수업깊이우선탐색2 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, R;
    static V[] vtx;

    static int visitedSequence = 1;

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        N = Integer.parseInt(params[0]);
        M = Integer.parseInt(params[1]);
        R = Integer.parseInt(params[2]);
        vtx = new V[N+1];
        for (int i = 1; i <= N; i++) {
            vtx[i] = new V(i);
        }
        for (int i = 0; i < M; i++) {
            params = in.readLine().split(" ");
            vtx[Integer.parseInt(params[0])].connected.add(vtx[Integer.parseInt(params[1])]);
            vtx[Integer.parseInt(params[1])].connected.add(vtx[Integer.parseInt(params[0])]);
        }
        for (int i = 1; i <= N; i++) {
            vtx[i].connected.sort(Comparator.comparing(v -> v.no, Comparator.reverseOrder()));
        }
        dfs(vtx[R]);
        for (int i = 1; i <= N; i++) {
            out.write(String.valueOf(vtx[i].visitedSequence));
            out.newLine();
        }
        out.flush();
    }

    static void dfs(V v) {
        v.checked = true;
        v.visitedSequence = visitedSequence++;
        for (V _v : v.connected) {
            if(!_v.checked) dfs(_v);
        }
    }

    static class V {
        int no;
        boolean checked;
        int visitedSequence;
        List<V> connected;

        public V(int no) {
            this.no = no;
            this.connected = new ArrayList<>();
        }
    }
}