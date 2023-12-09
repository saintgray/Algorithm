package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    10821 : 정수의 개수
//    ref url : https://www.acmicpc.net/problem/10821
public class 정수의개수 {
    private static final char SEPERATOR = ',';
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] input = in.readLine().toCharArray();
        int result = 0;
        for (char c : input) {
            result += c == SEPERATOR ? 1 : 0;
        }
        System.out.println(result + 1);
    }
}
