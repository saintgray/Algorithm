package numberTheory;

//    문제
//    베르트랑 공준은 임의의 자연수 n에 대하여,
//    n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
//    이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
//    예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다.
//    (11, 13, 17, 19)
//    또, 14보다 크고, 28보다 작거나 같은 소수는 3개가 있다.
//    (17,19, 23)
//    자연수 n이 주어졌을 때,
//    n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
//
//    입력
//    입력은 여러 개의 테스트 케이스로 이루어져 있다.
//    각 케이스는 n을 포함하는 한 줄로 이루어져 있다.
//    입력의 마지막에는 0이 주어진다.
//
//    출력
//    각 테스트 케이스에 대해서,
//    n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력한다.
//
//    제한
//    1 ≤ n ≤ 123,456

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 베스트랑공준 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        while ((n = Integer.parseInt(in.readLine())) != 0) {
            boolean[] primeNumber = new boolean[2 * n + 1];
            primeNumber[2] = true;
            for (int i = 3; i < primeNumber.length; i += 2)
                primeNumber[i] = true;
            for (int i = 3; ; i += 2) {
                int j = i * i; // 제곱수에 대하여 판별
                if (j >= primeNumber.length) break;
                for (i *= 2; j < primeNumber.length; j += i) {
                    primeNumber[j] = false;
                }
                i /= 2;
            }
            int primeNumbers = 0;
            for (int i = n+1; i < primeNumber.length; i++) {
                if (primeNumber[i]) primeNumbers++;
            }
            System.out.println(primeNumbers);
        }
        in.close();
    }
}
