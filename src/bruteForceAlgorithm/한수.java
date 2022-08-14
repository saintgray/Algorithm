package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    문제
//    어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다.
//    등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다.
//    N이 주어졌을 때, 1보다 크거나 같고,
//    N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 1,000보다 작거나 같은 자연수 N이 주어진다.
//
//    출력
//    첫째 줄에 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력한다.

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
