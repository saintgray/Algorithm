package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    9465 : 스티커
//    ref url : https://www.acmicpc.net/problem/9465
public class 스티커 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        while(T-- >0) {
            int n = Integer.parseInt(in.readLine());
            int[][] arr = new int[2][n];
            Memory[] dp = new Memory[n];
            for (int i = 0; i < n; i++) {
                dp[i] = new Memory();
            }
            for (int i = 0; i < 2; i++) {
                arr[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            dp[0].max1 = arr[0][0];
            dp[0].max2 = arr[1][0];
            dp[0].max = Math.max(dp[0].max1, dp[0].max2);
            for (int _n = 1; _n < n; _n++) {
                // _n + 1 열까지의 스티커를 보았을 때 떼낸 스티커의 최대점수
                dp[_n].max1 = Math.max(dp[_n-1].max2 + arr[0][_n], dp[_n-1].max3 + arr[0][_n]);
                dp[_n].max2 = Math.max(dp[_n-1].max1 + arr[1][_n], dp[_n-1].max3 + arr[1][_n]);
                dp[_n].max3 = Math.max(Math.max(dp[_n-1].max1, dp[_n-1].max2), dp[_n-1].max3);
                dp[_n].max = Math.max(Math.max(dp[_n].max1, dp[_n].max2), dp[_n].max3);
            }
            out.write(String.valueOf(dp[n-1].max));
            out.newLine();
        }
        out.flush();
    }

    static class Memory {
        int max1;   // 1행 스티커를 떼냈을 때 최대 점수
        int max2;   // 2행 스티커를 떼냈을 때 최대 점수
        int max3;   // 하나도 안뗏을 때 최대 점수
        int max;   // 최대점수
    }
}


