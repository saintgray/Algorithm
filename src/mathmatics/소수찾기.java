package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


//    문제
//    주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
//
//    입력
//    첫 줄에 수의 개수 N이 주어진다. N은 100이하이다.
//    다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
//
//    출력
//    주어진 수들 중 소수의 개수를 출력한다.

public class 소수찾기 {

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

            if(number == 1 || !isPrimeNumber)
                N--;
        }
        System.out.println(N);
    }
}
