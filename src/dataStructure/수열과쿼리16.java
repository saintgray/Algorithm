package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    14428 : 수열과쿼리16
//    ref url : https://www.acmicpc.net/problem/14428
public class 수열과쿼리16 {
    static int[] arr;
    static int[] seg;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(in.readLine());
        int H = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, H + 1);
        seg = new int[size];
        Arrays.fill(seg, Integer.MAX_VALUE);
        initTree(0, N - 1, 1);

        while (M-- > 0) {
            int[] query = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = query[0];
            int b = query[1];
            int c = query[2];
            if (a == 1) {
                // update segment node
                arr[b - 1] = c;
                update(b - 1, 0, N - 1, 1);
                System.out.println(Arrays.toString(arr));
            } else {
                // write segment node
                out.write(String.format("%d\n", getResult(0, N - 1, b - 1, c - 1, 1) + 1));
                System.out.println(Arrays.toString(seg));
            }
        }
        out.flush();
    }


    static void initTree(int low, int high, int h) {
        if (low == high) {
            seg[h] = low;
            return;
        }
        int mid = (low + high) / 2;
        initTree(low, mid, 2 * h);
        initTree(mid + 1, high, 2 * h + 1);
        seg[h] = Math.min(seg[h], getSegVal(h));    // 초기값 int 최대값 대응
    }

    static void update(int targetIndex, int low, int high, int h) {
        if (low == high) {
            seg[h] = targetIndex;
            return;
        }
        int mid = (low + high) / 2;
        boolean isLeftSide = targetIndex <= mid;
        if (isLeftSide) {
            high = mid;
            update(targetIndex, low, high, 2 * h);
        } else {
            low = mid + 1;
            update(targetIndex, low, high, 2 * h + 1);
        }
        seg[h] = getSegVal(h);
    }

    static int getResult(int low, int high, int startIndex, int endIndex, int h) {
        if (startIndex > high || endIndex < low)
            return Integer.MAX_VALUE;
        if(startIndex <= low && high <= endIndex)
            return seg[h];
        int mid = (low + high) / 2;
        int segValLeft = getResult(low, mid, startIndex, endIndex, 2 * h);
        int segValRight = getResult(mid + 1, high, startIndex, endIndex, 2 * h + 1);
        int valLeft = segValLeft >= arr.length ? Integer.MAX_VALUE : arr[segValLeft];
        int valRight = segValRight >= arr.length ? Integer.MAX_VALUE : arr[segValRight];
        return (valLeft < valRight ? segValLeft : valRight < valLeft ? segValRight : Math.min(segValRight, segValLeft));
    }

    static int getSegVal(int h) {
        int val1 = seg[2*h] >= arr.length ? Integer.MAX_VALUE : arr[seg[2*h]];
        int val2 = seg[2*h+1] >= arr.length ? Integer.MAX_VALUE : arr[seg[2*h+1]];
        return val1 < val2 ? seg[2*h] : val2 < val1 ? seg[2*h+1] : Math.min(seg[2*h], seg[2*h+1]);
    }
}