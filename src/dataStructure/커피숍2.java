package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1275 : 커피숍2
//    ref url : https://www.acmicpc.net/problem/1275
public class 커피숍2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int Q = params[1];
        long[] arr = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        // 세그먼트 트리의 높이 H
        int H = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 포화 이진트리 크기
        int size = (int) Math.pow(2, H + 1);
        long[] seg = new long[size];

        // arr 배열의 구간합 세그먼트 트리 구현
        initNode(0, N - 1, arr, seg, 1);
        while (Q-- > 0) {
            long[] input = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            // 구간합출력
            int x = (int) input[0];
            int y = (int) input[1];
            // 값변경
            int a = (int) input[2];
            int b = (int) input[3];
            // write segment tree value
            out.write(String.format("%d\n",
                    getSummation(
                            0,
                            N-1,
                            Math.min(x-1, y-1),
                            Math.max(x-1, y-1),
                            1,
                            seg
                    )
            ));
            // update node
            long diff = b - arr[a - 1];
            arr[a - 1] = b;
            updateNode(a - 1, diff, 0, N - 1, 1, seg);
            // System.out.println(Arrays.toString(arr));
        }
        out.flush();
    }

    static void initNode(int low, int high, long[] arr, long[] seg, int h) {
        if (low == high) {
            seg[h] = arr[low];
            return;
        }
        int mid = (high + low) / 2;
        initNode(low, mid, arr, seg, h * 2);
        initNode(mid + 1, high, arr, seg, 2 * h + 1);
        seg[h] = seg[h * 2] + seg[2 * h + 1];
    }

    static void updateNode(int index, long diff, int low, int high, int h, long[] seg) {
        if (h >= seg.length || seg[h] == 0)  // 포화 이진트리를 위해 임시 할당된 값은 update 하지 않는다.
            return;
        long sum = seg[h];
        seg[h] = sum + diff;
        // 좌 우 판별
        int mid = (low + high) / 2;
        boolean isLeftSide = index <= mid;
        // 자식 Node Update
        if (isLeftSide) {
            high = mid;
            h = 2 * h;
            updateNode(index, diff, low, high, h, seg);
        } else {
            low = mid + 1;
            h = 2 * h + 1;
            updateNode(index, diff, low, high, h, seg);
        }
    }

    static long getSummation(int low, int high, int leftIndex, int rightIndex, int h, long[] seg) {
        if (high < leftIndex || low > rightIndex)
            return 0;
        if (leftIndex <= low && high <= rightIndex)
            return seg[h];
        int mid = (low + high) / 2;
        return getSummation(low, mid, leftIndex, rightIndex, 2 * h, seg) + getSummation(mid + 1, high, leftIndex, rightIndex, 2 * h + 1, seg);
    }

}
