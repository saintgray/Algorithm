package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1629 곱셈
// 문제 참조 : https://www.acmicpc.net/problem/1629
public class 곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long[] arr = Arrays.stream(in.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();
        long a = arr[0];
        long b = arr[1];
        long c = arr[2];
        System.out.println(getResult(a, b, c));
        // A^BmodC =
        // B 가 홀수일때 : (A * AmodC * A^B-1modC)modC
        // B 가 짝수일 때 : ((A^(B/2)modC * A^(B/2)%modC)modC
        // B/2 가 1이되는 순간 A^(B/2)modC = AmodC

    }

    private static long getResult(long a, long b, long c) {
        if (b == 1) return mod(a, c);
        long b2 = getResult(a, b / 2, c) % c;
        return b % 2 == 0 ? (mod(b2 * b2, c)) : mod(mod(b2 * b2, c) * a, c);
    }

    private static long mod(long A, long C) {
        return A % C;
    }
}
