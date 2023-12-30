package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    15894 : 수학은 체육과목 입니다
//    ref url : https://www.acmicpc.net/problem/15894
public class 수학은체육과목입니다 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(in.readLine());
        // 최상단 + 최하단 + 양변 + 중간단 변의 합
        System.out.println(1 + n + 2 * n + n - 1);
    }
}
