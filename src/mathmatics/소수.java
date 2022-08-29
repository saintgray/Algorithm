package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//    2581 : 소수
//    
//    문제
//    자연수 M과 N이 주어질 때 M이상 N이하의 자연수 중
//    소수인 것을 모두 골라 이들 소수의 합과 최솟값을 찾는 프로그램을 작성하시오.
//    예를 들어 M=60, N=100인 경우
//    60이상 100이하의 자연수 중 소수는
//    61, 67, 71, 73, 79, 83, 89, 97 총 8개가 있으므로,
//    이들 소수의 합은 620이고, 최솟값은 61이 된다.
//    
//    입력
//    입력의 첫째 줄에 M이, 둘째 줄에 N이 주어진다.
//    
//    M과 N은 10,000이하의 자연수이며, M은 N보다 작거나 같다.
//    
//    출력
//    M이상 N이하의 자연수 중 소수인 것을 모두 찾아
//    첫째 줄에 그 합을, 둘째 줄에 그 중 최솟값을 출력한다.
//    단, M이상 N이하의 자연수 중 소수가 없을 경우는 첫째 줄에 -1을 출력한다.

public class 소수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(in.readLine());
        int N = Integer.parseInt(in.readLine());

        boolean[] primeNumber = new boolean[N + 1];
        if(primeNumber.length >=3)
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
        int sum = 0;
        int firstPrimeNum = 0;
        for (int i = M; i < primeNumber.length; i++) {
            if (firstPrimeNum == 0 && primeNumber[i])
                firstPrimeNum = i;
            if (primeNumber[i]) sum += i;
        }
        System.out.println(sum == 0 ? -1 : sum);
        if (firstPrimeNum != 0)
            System.out.println(firstPrimeNum);

        in.close();
    }
}
//    함께보기
//    - 수학
//    - 정수론
//    - 소수 판정
