package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//    2745 : 진법변환
//    ref url : https://www.acmicpc.net/problem/2745
public class 진법변환 {
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            map.put(String.valueOf(i), ((int) i) - 55);
        }
        String[] params = in.readLine().split(" ");
        String str = params[0];
        int format = Integer.parseInt(params[1]);

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            int square = str.length() - i - 1;
            result += map.get(s) * Math.pow(format, square);
        }
        System.out.println(result);


    }
}
