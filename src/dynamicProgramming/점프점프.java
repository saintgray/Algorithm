package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    11069 : 점프 점프
//    ref url : https://www.acmicpc.net/problem/11069
public class 점프점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(in.readLine());
        int[] maze = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Integer[] dp = new Integer[maze.length];
        if (dp.length == 1) {
            System.out.println(0);
        } else {
            dp[0] = 0;
            dp[1] = maze[0] == 1 ? 1 : null;
            // 점화
            for (int point = 0; point < maze.length; point++) {
                if (dp[point] == null)
                    continue;
                int num = maze[point];
                for (int i = 1; i <= num; i++) {
                    int jumped = i + point;
                    if (jumped < maze.length) {
                        Integer fastest = dp[jumped];
                        if (fastest == null) {
                            dp[jumped] = dp[point] + 1;
                        } else {
                            dp[jumped] = Math.min(dp[jumped], dp[point] + 1);
                        }
                    }
                }
            }
            Integer result = dp[dp.length - 1];
            System.out.println(result == null ? -1 : result);
        }
    }
}