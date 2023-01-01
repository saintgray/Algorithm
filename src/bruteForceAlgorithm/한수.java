package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1065 : 한수
//    ref url : https://www.acmicpc.net/problem/1065
public class 한수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(in.readLine());
        if (Math.log10(N) < 2) {
            System.out.println(N);
        } else {
            int cnt = 0;
            for (int i = 100; i <= N; i++) {
                int[] numArr = Arrays.stream(String.valueOf(i).split("")).mapToInt(e -> Integer.parseInt(e)).toArray();
                int diff = numArr[1] - numArr[0];
                boolean isHanSoo = true;
                for (int j = 2; j < numArr.length; j++) {
                    if (numArr[j] - numArr[j - 1] != diff) {
                        isHanSoo = false;
                        break;
                    }
                }
                if (isHanSoo)
                    cnt++;
            }
            System.out.println(99 + cnt);
        }
        in.close();
    }
}
