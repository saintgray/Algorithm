package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    18406 : 럭키 스트레이트
//    ref url : https://www.acmicpc.net/problem/18406
public class 럭키스트레이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] score = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int result = 0;
        for (int i = 1; i <= score.length; i++) {
            result += i <= (score.length / 2) ? score[i - 1] : -score[i - 1];
        }
        System.out.println(result == 0 ? "LUCKY" : "READY");
    }
}
