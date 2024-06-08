package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    10942 : 팰린드롬?
//    ref url : https://www.acmicpc.net/problem/10942
public class 펠린드롬10942 {

    static Boolean[][] dp;
    static int[] string;
    static final String DELIMITER = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        dp = new Boolean[N][N]; // max 2000 * 2000
        string = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(in.readLine());
        while (M-- > 0) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int low = params[0] - 1;
            int high = params[1] - 1;
            Boolean dpVal = dp[low][high];
            if (dpVal == null) track(low, high);
            out.write(dp[low][high] ? '1' : '0');
            out.write(DELIMITER);
        }
        out.flush();
    }

    static Boolean track(int low, int high) {
        if (dp[low][high] != null) return dp[low][high];
        if (low == high) {
            dp[low][high] = true;
            return true;
        }
        int mid = (low + high) / 2;
        dp[mid + 1][high] = track(mid + 1, high);
        dp[low][mid] = track(low, mid);
        dp[low][high] = merge(low, high);
        return dp[low][high];
    }

    static boolean merge(int low, int high) {
        int st = low;
        int ed = high;
        while (ed > st) {
            if (string[st] != string[ed]) {
                return false;
            }
            st++;
            ed--;
        }
        return true;
    }
}
