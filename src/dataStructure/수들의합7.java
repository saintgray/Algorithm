package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    2268 : 수들의 합 7
//    ref url : https://www.acmicpc.net/problem/2268
public class 수들의합7 {
    static long[] arr;
    static long[] seg;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        arr = new long[N];
        seg = new long[(int) Math.pow(2, (int) Math.ceil(Math.log(N) / Math.log(2)) + 1)];
        while (M-- > 0) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = (int) input[0];
            int b = (int) input[1];
            int c = input[2];

            if (a == 1) {
                long diff = c - arr[b - 1];
                arr[b - 1] = c;
                updateNode(b - 1, diff, 0, N - 1, 1);
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(seg));
            } else {
                out.write(String.format("%d\n", getSummation(0, N - 1, Math.min(b - 1, c - 1), Math.max(b - 1, c - 1), 1)));
            }
        }
        out.flush();
    }

    static void updateNode(int index, long diff, int low, int high, int h) {
        if (h >= seg.length)
            return;
        long sum = seg[h];
        seg[h] = sum + diff;
        int mid = (low + high) / 2;
        boolean isLeftSide = index <= mid;
        if (isLeftSide) {
            high = mid;
            h = 2 * h;
            updateNode(index, diff, low, high, h);
        } else {
            low = mid + 1;
            h = 2 * h + 1;
            updateNode(index, diff, low, high, h);
        }
    }

    static long getSummation(int low, int high, int leftIndex, int rightIndex, int h) {
        if (high < leftIndex || low > rightIndex)
            return 0;
        if (leftIndex <= low && high <= rightIndex)
            return seg[h];
        int mid = (low + high) / 2;
        return getSummation(low, mid, leftIndex, rightIndex, 2 * h) + getSummation(mid + 1, high, leftIndex, rightIndex, 2 * h + 1);
    }
}
