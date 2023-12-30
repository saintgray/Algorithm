package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 최소값 {
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
        int[] seg = new int[size];
        // init tree
        initNode(0, N - 1, arr, seg, 1);
        while (M-- > 0) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = input[0];
            int b = input[1];
            // write segment node
            resolve(0, N-1, a-1, b-1, 1, seg);
            out.write(String.format("%d\n", min));
            min = Integer.MAX_VALUE;
        }
        out.flush();
    }

    static void initNode(int low, int high, int[] arr, int[] seg, int h) {
        if (low == high) {
            seg[h] = Math.min(arr[low], arr[high]);
            return;
        }
        int mid = (high + low) / 2;
        initNode(low, mid, arr, seg, h * 2);
        initNode(mid + 1, high, arr, seg, 2 * h + 1);
        seg[h] = Math.min(seg[h * 2], seg[2 * h + 1]);
    }

    static void resolve(int low, int high, int leftIndex, int rightIndex, int h, int[] seg) {
        if (high < leftIndex || low > rightIndex)
            return;
        if (leftIndex <= low && high <= rightIndex) {
            min = Math.min(seg[h], min);
            return;
        }
        int mid = (low + high) / 2;
        resolve(low, mid, leftIndex, rightIndex, 2 * h, seg);
        resolve(mid + 1, high, leftIndex, rightIndex, 2 * h + 1, seg);
    }
}
