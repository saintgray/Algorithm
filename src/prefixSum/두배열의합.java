package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//    2143 : 두 배열의 합
//    ref url : https://www.acmicpc.net/problem/2143
public class 두배열의합 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    //  key : 부분 합
    //  value : 부분합이 나올 수 있는 경우 수
    static Map<Long, Long> fragASum = new HashMap<>();
    static Map<Long, Long> fragBSum = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        int n = Integer.parseInt(in.readLine());
        int[] arrA = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumA = new int[n + 1];
        for (int i = 0; i < arrA.length; i++) {
            sumA[i + 1] = sumA[i] + arrA[i];
        }
        int m = Integer.parseInt(in.readLine());
        int[] arrB = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumB = new int[m + 1];
        for (int i = 0; i < arrB.length; i++) {
            sumB[i + 1] = sumB[i] + arrB[i];
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n + 1 - i; j++) {
                long fragSum = sumA[i + j] - sumA[i - 1];
                fragASum.merge(fragSum, 1L, Long::sum);
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < m + 1 - i; j++) {
                long fragSum = sumB[i + j] - sumB[i - 1];
                fragBSum.merge(fragSum, 1L, Long::sum);
            }
        }
        // HashMap 내 key, value type 을 Long 으로 선언하는 이유
        // 1. key
        //  T=-1,000,000,000, A 의 모든 원소가 1,000,000 일 경우
        //  A 의 부분 합이 1,000,000,000 일 때 B 의 부분 합이 -3,000,000,000 인 경우의 수를 찾아야 함  (int 범위에서 벗어남)
        // 2. value
        // T=0, A,B 모든 원소가 0일 경우
        // 부분합의 총 경우의 수는 각각 500,500 개이고 총 부 배열 쌍의 수는
        // 500,500^2 = 250,500,250,000  (int 범위에서 벗어남)
        long cases = 0;
        for (Map.Entry<Long, Long> entry : fragASum.entrySet()) {
            Long sum = entry.getKey();
            Long a_cases = entry.getValue();
            Long b_cases = Optional.ofNullable(fragBSum.get(T - sum)).orElse(0L);
            cases += a_cases * b_cases;
        }
        System.out.println(cases);
    }
}
