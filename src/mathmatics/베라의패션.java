package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    15439 : 베라의 패션
//    ref url : https://www.acmicpc.net/problem/15439
public class 베라의패션 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        System.out.println(N * (N - 1));
        // 상의에서 N 가지, 하의에서 N-1 가지의 곱 (같은 index 일 경우 같은 색상 전제)
    }
}
