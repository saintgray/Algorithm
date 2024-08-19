package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2473 : 세 용액
//    ref url : https://www.acmicpc.net/problem/2473
public class 세용액 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static Long minimum = Long.MAX_VALUE;
    static int[] arr;
    static int a;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        for (int st = 0; st < N - 2; st++) {
            for (int ed = st + 2; ed < N; ed++) {
                find(st, ed);
            }
        }
        System.out.printf("%d %d %d", a, b, c);
    }

    static void find(int st, int ed) {
        // 두 용액의 합
        long sumOfTwoAid = Long.sum(arr[st], arr[ed]);
        int mid;
        int _st = st;
        int _ed = ed;
        while (Math.abs(_st - _ed) >= 2) {
            mid = (_st + _ed) / 2;
            long midVal = arr[mid];
            // 세 용액의 합
            long sumOfTrioAid = midVal + sumOfTwoAid;
            // 절대값이 0에 가까우면 갱신
            if (Math.abs(minimum) >= Math.abs(sumOfTrioAid)) {
                minimum = sumOfTrioAid;
                a = arr[st];
                b = arr[mid];
                c = arr[ed];
            }
            if (sumOfTrioAid == 0) break;
            // 다음 탐색
            if (Long.compare(sumOfTrioAid, 0) == 1) {
                _ed = mid;
            } else {
                _st = mid;
            }
        }
    }
}