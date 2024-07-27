package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//    5639 : 이진 검색 트리
//    ref url : https://www.acmicpc.net/problem/5639
public class 이진검색트리 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Integer> inputs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String input = null;
        while (((input) = in.readLine()) != null && !"".equals(input)) {
            inputs.add(Integer.parseInt(input));
        }
        V anchor = new V(inputs.get(0));
        for (int i = 1; i < inputs.size(); i++) {
            find(anchor, inputs.get(i));
        }
        print(anchor);
        out.flush();
    }

    static void find(V v, int num) {
        if(num < v.n) {
            if(v.left == null) v.left = new V(num);
            else find(v.left, num);
        } else {
            if(v.right == null) v.right = new V(num);
            else find(v.right, num);
        }
    }

    static void print(V v) throws IOException {
        if(v.left != null) print(v.left);
        if(v.right != null) print(v.right);
        out.write(String.valueOf(v.n));
        out.newLine();
    }

    static class V {
        int n;
        V left;
        V right;

        public V(int n) {
            this.n = n;
        }
    }
}
