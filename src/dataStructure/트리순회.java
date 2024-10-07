package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Optional;

//    1991 : 트리 순회
//    ref url : https://www.acmicpc.net/problem/1991
public class 트리순회 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static V[] vtx;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        vtx = new V[26];
        for (int i = 0; i < N; i++) {
            String[] param = in.readLine().split(" ");
            int a = ((int) param[0].charAt(0)) - 65;
            int b = ((int) param[1].charAt(0)) - 65;
            int c = ((int) param[2].charAt(0)) - 65;
            vtx[a] = Optional.ofNullable(vtx[a]).orElse(vtx[a] = new V((char) (a + 65)));

            if ((char) (b + 65) != 46) {
                vtx[a].L = Optional.ofNullable(vtx[b]).orElse(vtx[b] = new V((char) (b + 65)));
                vtx[b].p = vtx[a];
            }
            if ((char) (c + 65) != 46) {
                vtx[a].R = Optional.ofNullable(vtx[c]).orElse(vtx[c] = new V((char) (c + 65)));
                vtx[c].p = vtx[a];
            }

        }

        V root = null;
        for (int i = 0; i < N; i++) {
            if (vtx[i].p == null) {
                root = vtx[i];
                break;
            }
        }
        preorder(root);
        out.newLine();
        inorder(root);
        out.newLine();
        postorder(root);
        out.flush();
    }

    static void preorder(V v) throws IOException {
        out.write(String.valueOf(v.c));
        if (v.L != null) {
            preorder(v.L);
        }
        if (v.R != null) {
            preorder(v.R);
        }
    }

    static void inorder(V v) throws IOException {
        if (v.L != null) {
            inorder(v.L);
        }
        out.write(String.valueOf(v.c));
        if (v.R != null) {
            inorder(v.R);
        }

    }

    static void postorder(V v) throws IOException {
        if (v.L != null) {
            postorder(v.L);
        }
        if (v.R != null) {
            postorder(v.R);
        }
        out.write(String.valueOf(v.c));
    }

    static class V {
        char c;
        V L;
        V R;
        V p;

        public V(char c) {
            this.c = c;
        }
    }
}