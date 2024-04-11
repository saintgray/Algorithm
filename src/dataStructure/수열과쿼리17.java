package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//	14438 : 수열과쿼리17
//	ref url : https://www.acmicpc.net/problem/14438
public class 수열과쿼리17 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(in.readLine());
        // 세그먼트 트리 높이
        int H = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 세그먼트 트리 크기
        int size = (int) Math.pow(2, H + 1);
        // 구간 내 최소값을 저장
        int[] seg = new int[size];
        Arrays.fill(seg, 1);
        initTree(0, N - 1, 1, arr, seg);
        while (M-- > 0) {
            int[] query = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = query[0];
            int b = query[1];
            int c = query[2];
            if (a == 1) {
                // update segment node
                update(b - 1, c, 0, N - 1, 1, seg);
            } else {
                // write segment node
                out.write(String.format("%d\n", getResult(0, N - 1, b - 1, c - 1, 1, seg)));
            }
        }
        out.flush();
    }


    static void initTree(int low, int high, int h, int[] arr, int[] seg) {
        if (low == high) {
            // low ~ high index 사이의 최소값 = arr[index] 값
            seg[h] = arr[low];
            return;
        }
        int mid = (low + high) / 2;
        // 분할정복 (low ~ mid 사이의 최소값(좌측), mid ~ high 사이의 최소값(우측))
        initTree(low, mid, 2 * h, arr, seg);
        initTree(mid + 1, high, 2 * h + 1, arr, seg);
        seg[h] = Math.min(seg[2*h],seg[2*h+1]);
    }

    static void update(int targetIndex, int newVal, int low, int high, int h, int[] seg) {
        if (low == high && targetIndex == low) {
            seg[h] = newVal;
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
        seg[h] = Math.min(seg[2*h],seg[2*h+1]);
    }

    static int getResult(int low, int high, int startIndex, int endIndex, int h, int[] seg) {
        if (startIndex > high || endIndex < low)
            return Integer.MAX_VALUE;
        if (startIndex <= low && high <= endIndex)
            return seg[h];
        int mid = (low + high) / 2;
        return Math.min(getResult(low, mid, startIndex, endIndex, 2 * h, seg), getResult(mid + 1, high, startIndex, endIndex, 2 * h + 1, seg));
    }
}
