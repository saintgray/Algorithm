package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//    9527 : 1의 개수 세기
//    ref url : https://www.acmicpc.net/problem/9527
//    tag : bit masking
public class 일의개수세기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // A[i] : 2^i -1 까지 십진수를 이진수로 표기 시 1의 총 개수
        long[] count = new long[59];
        count[0] = 0;
        for (int i = 1; i <= 58; i++) {
            count[i] = (1L << (i-1)) + (2 * count[i-1]);
        }
        // A[59] = 2^59 -1 = 576,460,752,303,423,488 - 1 로 입력 제한 (=1경) 고려한 범위
        String[] param = in.readLine().split(" ");
        long A = Long.parseLong(param[0]);
        long B = Long.parseLong(param[1]);
        // ANS : 0 ~ B 까지 1의 개수 - 0 ~ A-1 까지 1의 갯수
        System.out.println(count(B, count) - count(A - 1, count));
    }

    static long count(long num, long[] count) throws IOException {
        if(num == 0) return 0;
        long _count = 0;
        long _num = num;
        while (_num > 0) {
            // bit 길이
            int bl = new BigInteger(String.valueOf(_num)).bitLength();
            // 1. 비트 맨 앞자리 1의 개수
            _count += _num - (1L << (bl-1)) + 1;
            // 2. 0 ~ 2^n 까지 1의 개수
            _count += count[bl-1];
            // 3. 2^n + 1 ~ num 까지 첫 비트를 제외한 나머지 비트 중 1의 개수
            // 이 개수는 지표를 줄여가며 이전 비트의 공통 부분 집합에서 반복하여 더해간다
            _num -= 1L << (bl - 1);
        }
        return _count;
    }
}