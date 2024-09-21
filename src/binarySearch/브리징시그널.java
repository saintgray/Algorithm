package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    3066 : 브리징 시그널
//    ref url : https://www.acmicpc.net/problem/3066
//    tag : longest increasing subsequence
public class 브리징시그널 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            int[] circuit = new int[N + 1];
            for (int i = 0; i < N; i++) {
                circuit[i + 1] = Integer.parseInt(in.readLine());
            }

            int[] dp = new int[N + 1];
            dp[0] = 0;
            int lis = 0;
            for (int i = 1; i <= N; i++) {
                int port = circuit[i];
                if (port > dp[lis]) {
                    lis++;
                    dp[lis] = port;
                    continue;
                }
                dp[find(dp, port, lis)] = port;
            }
            out.write(String.valueOf(lis));
            out.newLine();
        }
        out.flush();
    }


    static int find(int[] dp, int port, int lis) {
        int st = 0;
        int ed = lis;
        while (st < ed) {
            int mid = (st + ed) / 2;
            if (dp[mid] > port) {
                ed = mid;
            } else {
                st = mid + 1;
            }
        }
        return ed;
    }
}
