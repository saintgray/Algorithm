package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 구간곱구하기 {

    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int K = params[2];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(in.readLine());
        // 세그먼트 트리 높이
        int H = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 세그먼트 트리 크기
        int size = (int) Math.pow(2, H + 1);
        // 구간곱을 MOD 로 나눈 값을 저장하는 segment tree
        int[] seg = new int[size];
        Arrays.fill(seg, 1);
        initTree(0, N - 1, 1, arr, seg);
        while (M > 0 || K > 0) {
            int[] query = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = query[0];
            int b = query[1];
            int c = query[2];
            if (a == 1) {
                // update segment node
                M--;
                update(b - 1, c, 0, N - 1, 1, seg);
            } else {
                K--;
                // write segment node
                out.write(String.format("%d\n", getResult(0, N - 1, b - 1, c - 1, 1, seg)));
            }
        }
        out.flush();
    }


    static void initTree(int low, int high, int h, int[] arr, int[] seg) {
        if (low == high) {
            seg[h] = arr[low] % MOD;
            return;
        }
        int mid = (low + high) / 2;
        initTree(low, mid, 2 * h, arr, seg);
        initTree(mid + 1, high, 2 * h + 1, arr, seg);
        seg[h] = (int) (((long) seg[2 * h] * (long) seg[2 * h + 1]) % MOD);
    }

    static void update(int targetIndex, int newVal, int low, int high, int h, int[] seg) {
        if (low == high && targetIndex == low) {
            seg[h] = newVal % MOD;
            return;
        }
        int mid = (low + high) / 2;
        boolean isLeftSide = targetIndex <= mid;
        if (isLeftSide) {
            high = mid;
            update(targetIndex, newVal, low, high, 2 * h, seg);
        } else {
            low = mid + 1;
            update(targetIndex, newVal, low, high, 2 * h + 1, seg);
        }
        seg[h] = (int) (((long) seg[2 * h] * (long) seg[2 * h + 1]) % MOD);
    }

    static int getResult(int low, int high, int startIndex, int endIndex, int h, int[] seg) {
        if (startIndex > high || endIndex < low)
            return 1;
        if (startIndex <= low && high <= endIndex)
            return seg[h];
        int mid = (low + high) / 2;
        return (int) (((long) getResult(low, mid, startIndex, endIndex, 2 * h, seg) * (long) getResult(mid + 1, high, startIndex, endIndex, 2 * h + 1, seg)) % MOD);
    }
}
