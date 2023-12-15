package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    21921 : 블로그
//    ref url : https://www.acmicpc.net/problem/21921
public class 블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int X = params[1];
        int[] info = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumInfo = new int[N];
        sumInfo[0] = info[0];
        for (int i = 1; i < sumInfo.length; i++) {
            sumInfo[i] = sumInfo[i - 1] + info[i];
        }
        int max = sumInfo[X - 1];
        int cnt = 1;

        for (int endDay = X; endDay < sumInfo.length; endDay++) {
            int startDay = endDay - X;
            if (startDay >= 0) {
                int visitors = sumInfo[endDay] - sumInfo[startDay];
                if (visitors > max) {
                    cnt = 1;
                    max = visitors;
                } else if (visitors == max) {
                    cnt++;
                } else {
                    // no action
                }
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }

    }
}
