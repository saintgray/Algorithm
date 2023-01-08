package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1748 : 수 이어 쓰기 1
//    ref url : https://www.acmicpc.net/problem/1748
public class 수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int len = String.valueOf(N).length();
        int result = 0;
        for (int i = 1; i <= len; i++) {
            if (i == len) {
                int sum = len * (N - ((int) Math.pow(10, len-1)) + 1);
                result += sum;
            } else {
                result += i * (((int) (Math.pow(10, i))) - ((int) (Math.pow(10, i - 1))));
            }
        }
        System.out.println(result);
    }
}
