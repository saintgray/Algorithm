package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    4256 : 트리
//    ref url : https://www.acmicpc.net/problem/4256
//    tag : tree
class 트리 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] vtx;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            vtx = new V[N + 1];
            int[] preorder = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] inorder = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 1; i <= N; i++) {
                vtx[i] = new V(i);
            }
            for (int i = 0; i < N; i++) {
                vtx[preorder[i]].poSeq = i;
                vtx[inorder[i]].ioSeq = i;
            }
            // 전위 순회 결과로 얻을 수 있는 정보 : 루트 정점
            // 중위 순회 결과로 얻을 수 있는 정보 : 정점 별 좌/우 측 정점
            postOrder(vtx[preorder[0]], preorder, inorder, 0, N);
            out.newLine();
        }
        out.flush();
    }

    static void postOrder(V v, int[] preorder, int[] inorder, int st, int ed) throws IOException {
        // 왼쪽 자식을 출력한다
        // 정점 왼쪽에 위치한 정점 중, 전위 순회 순서가 v.poSeq + 1 인 정점을 찾아 순회
        // 정점 왼쪽에 위치한 정점이 없을 수 있으므로 범위 체크
        if (v.poSeq + 1 < preorder.length) {
            V leftTarget = vtx[preorder[v.poSeq + 1]];
            for (int i = st; i < v.ioSeq; i++) {
                if (vtx[inorder[i]] == leftTarget) {
                    postOrder(vtx[inorder[i]], preorder, inorder, st, v.ioSeq);
                }
            }
        }

        // 오른쪽 자식을 출력한다
        // 정점 오른쪽에 위치한 정점 중, 전위 순회 순서가 v.poSeq + (정점 좌측에 있는 정점 개수) + 1 인 정점을 찾아 순회
        // 정점 오른쪽에 위치한 정점이 없을 수 있으므로 범위 체크
        if (v.poSeq + (v.ioSeq - st) + 1 < preorder.length) {
            V rightTarget = vtx[preorder[v.poSeq + (v.ioSeq - st) + 1]];
            for (int i = v.ioSeq + 1; i < ed; i++) {
                if (vtx[inorder[i]] == rightTarget) {
                    postOrder(vtx[inorder[i]], preorder, inorder, v.ioSeq + 1, ed);
                }
            }
        }
        // 자기 자신을 출력한다
        out.write(v.n + " ");
    }

    static class V {
        int n;
        int poSeq;  // 전위순회순서
        int ioSeq;  // 중위순회순서

        public V(int n) {
            this.n = n;
        }
    }
}