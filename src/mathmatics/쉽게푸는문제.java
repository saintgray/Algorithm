package mathmatics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1219 : 쉽게 푸는 문제
//    ref url : https://www.acmicpc.net/problem/1219
public class 쉽게푸는문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = params[0];
        int B = params[1];
        int[] sumArr = new int[B];
        int N = 1;
        int index = 0;
        while (index < sumArr.length) {
            for (int i = 0; i < N; i++) {
                sumArr[index] = index == 0 ? N : sumArr[index - 1] + N;
                if(index++ == sumArr.length -1)
                    break;
            }
            N++;
        }
        out.write(String.valueOf(sumArr[B-1] - (A == 1 ? 0 : sumArr[A-2])));
        out.flush();
    }
}
