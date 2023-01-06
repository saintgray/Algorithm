package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1921 : 연속합
//    ref url : https://www.acmicpc.net/problem/1921
public class 연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] memo = new int[N];
        memo[0] = arr[0];
        int max = memo[0];
        for (int i = 1; i < N; i++){
            memo[i] = Math.max(memo[i-1] + arr[i], arr[i]);
            max = Math.max(max, memo[i]);
        }
        System.out.println(max);
    }
}
