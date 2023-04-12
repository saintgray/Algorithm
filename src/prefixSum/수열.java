package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2059 : 수열
//    ref url : https://www.acmicpc.net/problem/2059
public class 수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int K = params[1];
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumArr = new int[N];
        for (int i = 0; i < N; i++)
            sumArr[i] = arr[i] + (i == 0 ? 0 : sumArr[i-1]);

        int result = Integer.MIN_VALUE;
        for (int i = K; i <= sumArr.length; i++) {
            result = Math.max(result, sumArr[i - 1] - (i == K ? 0 : sumArr[i - K - 1]));
        }
        System.out.println(result);

    }
}
