package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1769 : 3의 배수
//    ref url : https://www.acmicpc.net/problem/1769
public class 삼의배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String N = in.readLine();
        int cycles = 0;
        while(N.length() != 1) {
            cycles++;
            int total = 0;
            for (int i = 0; i < N.length(); i++) {
                 total += N.charAt(i) - 48;
            }
            N = String.valueOf(total);
        }
        System.out.println(cycles);
        System.out.println(Integer.parseInt(N) % 3 == 0 ? "YES" : "NO");
    }
}
