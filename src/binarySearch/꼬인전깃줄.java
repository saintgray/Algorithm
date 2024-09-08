package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1365 : 꼬인 전깃줄
//    ref url : https://www.acmicpc.net/problem/1365
//    tag : longest increasing subsequence
public class 꼬인전깃줄 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int[] A = new int[N + 1]; // 길이가 i 인 최장 부분 수열 중 수열의 마지막 값들 중 최소 값
        Arrays.fill(A, Integer.MAX_VALUE);
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A[0] = 0;
        int k = 0;
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (num > A[k]) {
                k++;
                A[k] = num;
                continue;
            }
            // 전기줄 번호를 붙일 target index 를 찾고,
            // 최장 부분 수열 개수가 index 개인 부분 수열의 마지막 값을 전기줄 번호로 갱신한다.
            A[find(A, arr[i], k)] = num;
        }
        System.out.println(N - k);
    }

    static int find(int[] A, int num, int ed) {
        int _st = 0;
        int _ed = ed;
        while (_st < _ed) {
            int mid = (_st + _ed) / 2;
            if (A[mid] > num) {
                _ed = mid;
            } else {
                _st = mid + 1;
            }
        }
        return _ed;
    }
}
