package numberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    문제
//    양수 A가 N의 진짜 약수가 되려면, N이 A의 배수이고, A가 1과 N이 아니어야 한다.
//    어떤 수 N의 진짜 약수가 모두 주어질 때, N을 구하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 N의 진짜 약수의 개수가 주어진다.
//    이 개수는 50보다 작거나 같은 자연수이다. // > 소수는 입력으로 들어오지 않는다.
//    둘째 줄에는 N의 진짜 약수가 주어진다.
//    1,000,000보다 작거나 같고, 2보다 크거나 같은 자연수이고, 중복되지 않는다.
//      >> N 은 2이상의 소수가 아닌 자연수이다.
//
//    출력
//    첫째 줄에 N을 출력한다. N은 항상 32비트 부호있는 정수로 표현할 수 있다.
public class 약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int divisorsNum = Integer.parseInt(in.readLine());
        long[] divisors = Arrays.stream(in.readLine().split(" ")).mapToLong(e -> Long.parseLong(e)).sorted().toArray();

        long maxDivisor = divisors[divisorsNum - 1];
        long lcm = divisors[0];
        // 진짜 약수의 최소값
        long minDivisor = divisors[0];
        for (int i = 0; i < divisors.length - 1; i++) {
            // 최대공약수
            long gcd = gcd(divisors[i], divisors[i + 1]);
            // 최소공배수
            lcm = (divisors[i] * divisors[i + 1]) / (gcd);
        }
        lcm = lcm == maxDivisor ?  minDivisor*lcm : lcm;
        System.out.println(lcm);
        in.close();
    }

    public static long gcd(long a, long b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
