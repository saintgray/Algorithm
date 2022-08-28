package mathmatics;

// 11050 : 이항 계수
// 문제 참조 : https://www.acmicpc.net/problem/11050

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 이항계수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long[] nAndK = Arrays.stream(in.readLine().split(" ")).mapToLong(e -> Long.parseLong(e)).toArray();
        long n = nAndK[0];
        long k = nAndK[1];
        System.out.println(factorial(n)/(factorial(k)*(factorial(n-k))));
    }

    public static long factorial(long num) {
        if(num ==0)
            return 1;
        long result = num;
        for (long i = num - 1; i > 0; i--) {
            result *= i;
        }
        return result;
    }
}


//    binomial coefficient : 이항계수
//    (a+b)^{n}
//    의 꼴의 다항식을 전개했을 때, a^{r}b^{n-r} (0 <= r < =n 인 정수) 의 계수를 의미하며, 다음과 같다.
//    nCr = n! / (r!*(n-r)!)
