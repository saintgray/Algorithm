package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Arrays;

//    10999  구간 합 구하기2
//    ref url : https://www.acmicpc.net/problem/10999
//    tag : 느리게 갱신되는 세그먼트 트리
public class 구간합구하기2 {

    static long[] arr;
    static Node[] seg;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];  // 1 <= N <= 1,000,000
        int M = params[1];  // 1 <= M <= 10,000
        int K = params[2];  // 1 <= K <= 10,000
        arr = new long[N];
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(in.readLine());
        int H = (int) Math.ceil(Math.log(N) / Math.log(2)); // 세그먼트 트리 높이
        seg = new Node[(int) Math.pow(2, H + 1)]; // 포화 이진트리 크기 (최대 size : 2097152)
        for (int i = 0; i < seg.length; i++) {
            seg[i] = new Node(i);
        }
        // arr 배열의 구간합 세그먼트 트리 구현
        initNode(0, N - 1, 1);
        while (M > 0 || K > 0) {
            long[] input = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            int a = (int) input[0];
            if (a == 1) {
                M--;
                int b = (int) input[1];
                int c = (int) input[2];
                long d = input[3];
                updateNode(b - 1, c-1, d, 0, N - 1, 1);
            } else {
                K--;
                int b = (int) input[1];
                int c = (int) input[2];
                out.write(String.format("%s\n", getSummation(0, N - 1, b - 1, c - 1, 1)));
            }
        }
        out.flush();
    }

    static void initNode(int low, int high, int h) {
        if (low == high) {
            seg[h].sum = BigDecimal.valueOf(arr[low]);
            return;
        }
        int mid = (high + low) / 2;
        initNode(low, mid, 2* h);
        initNode(mid + 1, high,2 * h + 1);
        seg[h].sum = seg[h * 2].total(low, mid).add(seg[2 * h + 1].total(mid + 1, high));
    }

    static void lazyUpdate(int low, int high, int h) {
        if(seg[h].lazy_add != null) {
            seg[h].sum = seg[h].total(low,high);
            if(low != high) {
                seg[2*h].setLazyAdd(seg[h].lazy_add);
                seg[2*h+1].setLazyAdd(seg[h].lazy_add);
            }
            seg[h].lazy_add = null;
        }
    }

    static void updateNode(int idx1, int idx2, long add, int low, int high, int h) {
        // lazy value 갱신 및 초기화
        lazyUpdate(low, high, h);
        if (idx1 > high || idx2 < low)
            return;
        // update 구간에 low, high index 가 모두 포함되어 있을 경우
        if (idx1 <= low && high <= idx2) {
            seg[h].sum = seg[h].sum.add(BigDecimal.valueOf((high-low+1)*add));
            // 자식 node 가 있을 경우 lazy value 계산 (구간 합을 구할 시 더해지는 후처리 값)
            if(low != high) {
                seg[2*h].setLazyAdd(BigDecimal.valueOf(add));
                seg[2*h+1].setLazyAdd(BigDecimal.valueOf(add));
            }
            return;
        }
        int mid = (low + high) / 2;
        updateNode(idx1, idx2, add, low, mid, 2 * h);
        updateNode(idx1, idx2, add, mid + 1, high, 2 * h + 1);
        seg[h].sum = seg[2*h].sum.add(seg[2*h+1].sum);
    }

    static BigDecimal getSummation(int low, int high, int leftIndex, int rightIndex, int h) {
        lazyUpdate(low, high, h);
        if (high < leftIndex || low > rightIndex)
            return BigDecimal.ZERO;
        if (leftIndex <= low && high <= rightIndex) {
            return seg[h].sum;
        }
        int mid = (low + high) / 2;
        return getSummation(low, mid, leftIndex, rightIndex, 2 * h).add(getSummation(mid + 1, high, leftIndex, rightIndex, 2 * h + 1));
    }


    static class Node {
        int h;
        BigDecimal sum;
        BigDecimal lazy_add;

        public Node(int h) {
            this.h = h;
            this.sum = BigDecimal.ZERO;
        }

        BigDecimal total(int low, int high) {
            return this.sum.add(this.lazy_add == null ? BigDecimal.ZERO : this.lazy_add.multiply(BigDecimal.valueOf(high-low+1)));
        }

        void setLazyAdd(BigDecimal lazyValue){
            this.lazy_add = (this.lazy_add == null ? BigDecimal.ZERO : this.lazy_add).add(lazyValue);
        }
    }
}
