package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    문제
//    M 과 N 사이의 수 중 소수를 출력하는 프로그램을 작성하시오


public class 소수찾기2 {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int number : numbers){

            boolean isPrimeNumber = true;

            for(int i=1; i<=number; i++){
                if(i!=1 && i!=number && number%i ==0){
                    isPrimeNumber = false;
                    break;
                }
            }

            if(number ==1 || !isPrimeNumber)
                N--;
        }
        System.out.println(N);
    }
}
