package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    10986 : 나머지 합
//    ref url : https://www.acmicpc.net/problem/10986
public class 나머지합 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        long[] arr = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] count = new long[M];
        // 구간 합
        long lastSum = 0;
        for (int i = 0; i < N; i++) {
            lastSum += arr[i];
            count[(int) (lastSum % M)]++;
        }

        long result = 0;
        for (int i = 0; i < M; i++) {
            long remainCount = count[i]; // 누적합을 M 으로 나눈 나머지가 i 인 개수
            // 개수(n = remainCount) 에서 순서쌍 2개를 뽑는 조합의 수 -> n C 2
            // -> n*(n-1) / 2
            result += (remainCount * (remainCount - 1)) / 2;
        }
        // 나머지가 0인 개수중 구간 길이가 1인 Case 는 별도 Counting ( 자기 자신이 M 으로 나누어 떨어지는 경우 )
        System.out.println(result + count[0]);
    }
}
// IDEA
// 1. 모듈러 연산
// 2. 조합론 (순서쌍)
// 3. 누적 합
