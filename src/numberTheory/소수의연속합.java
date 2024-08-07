package numberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1644 : 소수의 연속합
//    ref url : https://www.acmicpc.net/problem/1644
public class 소수의연속합 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(in.readLine());
        int[] primeNumbers = sieveOfEratosthenes(N);
        if (primeNumbers.length == 0) {
            System.out.println(0);
            return;
        }

        int st = 0;
        int ed = 0;
        int cases = 0;
        int sum = primeNumbers[0];
        try {
            while (st <= ed && ed < N) {
                if (sum == N) {
                    cases++;
                    sum += primeNumbers[++ed];
                    continue;
                }
                if (sum < N) {
                    sum += primeNumbers[++ed];
                } else {
                    sum -= primeNumbers[st++];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // 전부 탐색 완료
        }
        System.out.println(cases);
    }

    /**
     * sieveOfEratosthenes
     *
     * @param N 자연수
     * @return N 이하의 모든 소수 배열
     */
    static int[] sieveOfEratosthenes(int N) {
        int[] base = {2, 3, 5, 7};
        int primeNumbers = N;
        int n = (int) Math.ceil(Math.sqrt(N));
        Integer[] arr = new Integer[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        // 1은 소수가 아니므로 제외한다
        arr[0] = null;
        primeNumbers--;
        // 2, 3, 5, 7 의 배수를 제외한다
        for (int i = 0; i < base.length; i++) {
            int num = base[i];
            for (int _n = 2 * num; _n <= N; _n += num) {
                primeNumbers -= (arr[_n - 1] != null) ? 1 : 0;
                arr[_n - 1] = null;
            }
        }
        // 11 부터 N 의 제곱근까지 각 수의 배수를 제외한다
        for (int i = 11; i < n; i++) {
            for (int _n = i * 2; _n <= N; _n += i) {
                primeNumbers -= (arr[_n - 1] != null) ? 1 : 0;
                arr[_n - 1] = null;
            }
        }

        int idx = 0;
        int[] sieve = new int[primeNumbers];
        for (Integer num : arr) {
            if (num == null) continue;
            sieve[idx++] = num;
        }
        return sieve;
    }
}

