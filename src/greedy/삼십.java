package greedy;

//    문제
//    어느 날, 미르코는 우연히 길거리에서 양수 N을 보았다.
//    미르코는 30이란 수를 존경하기 때문에,
//    그는 길거리에서 찾은 수에 포함된 숫자들을 섞어
//    30의 배수가 되는 가장 큰 수를 만들고 싶어한다.
//    미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.
//
//    입력
//    N을 입력받는다.
//    N는 최대 10^5개의 숫자로 구성되어 있으며,
//    0으로 시작하지 않는다.
//
//    출력
//    미르코가 만들고 싶어하는 수가 존재한다면
//    그 수를 출력하라.
//    그 수가 존재하지 않는다면, -1을 출력하라.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class 삼십 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(in.readLine().split("")).sorted(Comparator.reverseOrder()).mapToInt(e-> Integer.parseInt(e)).toArray();
        if(input[input.length-1] != 0){
            System.out.println(-1);
        }else{
            int sum = 0;
            for(int n : input)
                sum+=n;
            if(sum%3==0){
                String result = "";
                for(int n : input)
                    result = result.concat(String.valueOf(n));
                System.out.println(result);
            }else{
                System.out.println(-1);
            }
        }
        in.close();
    }

    // 같이보기
    // 수학, 문자열, 그리디알고리즘, 정렬, 정수론
    // hint : 배수 판정법
}


