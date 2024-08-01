package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//    15681 : 트리와 쿼리
//    ref url : https://www.acmicpc.net/problem/15681
public class 트리와쿼리 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out =new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] nodes;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = p[0];
        int R = p[1];
        int Q = p[2];
        nodes = new V[N + 1];
        for (int n = 0; n < N - 1; n++) {
            int[] info = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            V u = Optional.ofNullable(nodes[info[0]]).orElseGet(() -> new V(info[0]));
            V v = Optional.ofNullable(nodes[info[1]]).orElseGet(() -> new V(info[1]));
            u.connected.add(v);
            v.connected.add(u);
            nodes[info[0]] = u;
            nodes[info[1]] = v;
        }
        // 루트 노드에 대해 모든 정점의 부모 노드를 초기화
        makeTree(nodes[R], -1);
        // 루트 노드에 대해 모든 서브트리의 총 점점의 개수를 조기화
        countSubTreeNodes(nodes[R]);
        for (int q = 0; q < Q; q++) {
            out.write(String.valueOf(nodes[Integer.parseInt(in.readLine())].size));
            out.newLine();
        }
        out.flush();
    }

    static void makeTree(V v, int parent) {
        v.parent = parent;
        v.checked = true;
        for (V _v : v.connected) {
            if(_v.checked) continue;
            _v.parent = v.n;
            makeTree(_v, _v.n);
        }
        v.checked = false;
    }

    static void countSubTreeNodes(V v) {
        v.checked = true;
        for (V _v : v.connected) {
            if(_v.checked) continue;
            countSubTreeNodes(_v);
            v.size += _v.size;
        }
        v.checked = false;
    }

    static class V {
        int n;
        int parent;
        int size;
        boolean checked;
        List<V> connected;

        public V(int n) {
            this.n = n;
            this.parent = -1;
            this.connected = new ArrayList<>();
            this.size = 1;
        }
    }
}

