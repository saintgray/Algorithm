package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    16953 : A -> B
//    ref url : https://www.acmicpc.net/problem/16953
public class AtoB {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = params[0];
        int B = params[1];
        int cnt = -1;
        while (true) {
            cnt++;
            if (A >= B || (B % 2 > 0 && B % 10 > 1))
                break;
            B = B % 2 == 1 ? B / 10 : B / 2;
        }
        System.out.println(A != B ? -1 : cnt + 1);
    }
}
