package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 최소값과최대값 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(in.readLine());
        // segment tree
        int H = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, H + 1);
        Node[] seg = new Node[size];
        // init tree
        initNode(0, N - 1, arr, seg, 1);
        while (M-- > 0) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = input[0];
            int b = input[1];
            // write segment node
            resolve(0, N-1, a-1, b-1, 1, seg);
            out.write(String.format("%d %d\n", min, max));
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }
        out.flush();
    }

    static void initNode(int low, int high, int[] arr, Node[] seg, int h) {
        if (low == high) {
            seg[h] = new Node(arr[low], arr[high]);
            return;
        }
        int mid = (high + low) / 2;
        initNode(low, mid, arr, seg, h * 2);
        initNode(mid + 1, high, arr, seg, 2 * h + 1);
        seg[h] = new Node(seg[h * 2], seg[2 * h + 1]);
    }

    static void resolve(int low, int high, int leftIndex, int rightIndex, int h, Node[] seg) {
        if (high < leftIndex || low > rightIndex)
            return;
        if (leftIndex <= low && high <= rightIndex) {
            Node node = seg[h];
            min = Math.min(node.min, min);
            max = Math.max(node.max, max);
            return;
        }
        int mid = (low + high) / 2;
        resolve(low, mid, leftIndex, rightIndex, 2 * h, seg);
        resolve(mid + 1, high, leftIndex, rightIndex, 2 * h + 1, seg);
    }

    static class Node {
        int max;
        int min;

        public Node(int a, int b) {
            this.max = Math.max(a, b);
            this.min = Math.min(a, b);
        }

        public Node(Node n1, Node n2) {
            this.max = Math.max(n1.max, n2.max);
            this.min = Math.min(n1.min, n2.min);
        }
    }
}
