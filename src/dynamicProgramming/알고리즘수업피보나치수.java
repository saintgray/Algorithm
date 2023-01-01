package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//    24416 : 알고리즘 수업 - 피보나치수 1
//    ref url : https://www.acmicpc.net/problem/24416
public class 알고리즘수업피보나치수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> funcCountingMap = new HashMap<>();
        funcCountingMap.put("recursive",0);
        funcCountingMap.put("dp",0);
        int n = Integer.parseInt(in.readLine());


        int fib = 0; // 재귀호출로 f[n] 을 구하기까지의 호출 수
        for(int i=1; i<=n; i++)
            fib = fib(i);


        int fibonacci = 0; // dp 로 f[n] 을 구하기까지의 호출 수
        int[] arr = new int[n+1];
        fibonacci = fibonacci(n, arr, fibonacci);

        System.out.printf("%d %d\n",fib, fibonacci); // result
    }

    public static int fib(int n){
        if(n ==1 || n==2){
            return 1;
        }else{
            return (fib(n-1)+fib(n-2));
        }
    }

    public static int fibonacci(int n, int[] arr, int fibonacci){
        if(n==1 || n==2){
            return 1;
        }
        for(int i=1; i<=n; i++){
            if(i == 1 || i ==2){
                arr[i] = 1;
            }else{
                arr[i] = arr[i-1]+ arr[i-2];
                fibonacci++;
            }
        }
        return fibonacci;
    }
}
