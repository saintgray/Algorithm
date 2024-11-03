package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    2263 :트리의 순회
//    ref url : https://www.acmicpc.net/problem/2263
//    tag : tree
public class 트리의순회 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] vtx;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        vtx = new V[N + 1];
        int[] inorder = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] postorder = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= N; i++) {
            vtx[i] = new V(i);
        }
        for (int i = 0; i < N; i++) {
            vtx[inorder[i]].ioSeq = i;
            vtx[postorder[i]].poSeq = i;
        }
        // 후위 순회 결과로 얻을 수 있는 정보 : 루트 정점 (postorder[N-1])
        // 중위 순회 결과로 얻을 수 있는 정보 : 정점 별 좌/우 측 정점
        preorder(vtx[postorder[N-1]], postorder, inorder, 0, N);
        out.flush();
    }

    static void preorder(V v, int[] postorder, int[] inorder, int st, int ed) throws IOException {
        // 자기 자신을 출력한다
        out.write(v.n + " ");
        // 왼쪽 자식을 출력한다
        int leftIndex = v.poSeq - ((ed - v.ioSeq));
        if(0 <= leftIndex && leftIndex < postorder.length) {
            V leftTarget = vtx[postorder[leftIndex]];
            for (int i = st; i < v.ioSeq; i++) {
                if (vtx[inorder[i]] == leftTarget) {
                    // System.out.printf("%d 번 정점의 왼쪽 자식 : %d", v.n, leftTarget.n);
                    preorder(vtx[inorder[i]], postorder, inorder, st, v.ioSeq);
                }
            }
        }
        // 오른쪽 자식을 출력한다
        if(0 <= v.poSeq - 1 && v.poSeq - 1 < postorder.length) {
            V rightTarget = vtx[postorder[v.poSeq - 1]];
            for (int i = v.ioSeq + 1; i < ed; i++) {
                if (vtx[inorder[i]] == rightTarget) {
                    // System.out.printf("%d 번 정점의 오른쪽 자식 : %d", v.n, rightTarget.n);
                    preorder(vtx[inorder[i]], postorder, inorder, v.ioSeq + 1, ed);
                }
            }
        }
    }


    static class V {
        int n;
        int poSeq;  // 후위순회순서(postorder)
        int ioSeq;  // 중위순회순서(inorder)

        public V(int n) {
            this.n = n;
        }
    }
}
