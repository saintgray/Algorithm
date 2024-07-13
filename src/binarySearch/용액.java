package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2476 : 용액
//    ref url : https://www.acmicpc.net/problem/2476
public class 용액 {
    static final int ZERO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        long[] arr = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int st = 0;
        int ed = N - 1;
        long diff = Long.MAX_VALUE;
        int result1 = 0;
        int result2 = 0;
        while (ed > st) {
            long sum = arr[st] + arr[ed];
            long _diff = Math.abs(ZERO - sum);
            if (_diff <= diff) {
                result1 = (int) arr[st];
                result2 = (int) arr[ed];
                diff = _diff;
            }
            if (diff == 0) {
                break;
            }
            // 다음 start pointer 값의 합과 0 과의 차이
            // 이전 end pointer 값의 합과 0 과의 차이
            // 차이가 적은 곳으로 pointer 탐색
            if (Math.abs(Math.abs(arr[st] + arr[ed - 1] - ZERO)) < Math.abs(Math.abs(arr[ed] + arr[st + 1])) - ZERO) {
                ed--;
            } else {
                st++;
            }
        }
        System.out.printf("%d %d", result1, result2);
    }
}
