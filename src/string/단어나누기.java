package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1251 : 단어 나누기
//    ref url : https://www.acmicpc.net/problem/1251
public class 단어나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        String result = null;
        for (int idx1 = 1; idx1 < str.length(); idx1++) {
            for (int idx2 = idx1 + 1; idx2 < str.length(); idx2++) {
                String token1 = str.substring(0, idx1);
                String token2 = str.substring(idx1, idx2);
                String token3 = str.substring(idx2);
//                System.out.println(String.format("%s\n%s\n%s\n", token1, token2, token3));
                String[] arr = {token1, token2, token3};
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < arr.length; j++) {
                    String token = arr[j];
                    for (int k = token.length() - 1; k >= 0; k--) {
                        builder.append(token.charAt(k));
                    }
                }
//                System.out.println(String.format("string : %s", builder));
                result = result == null || result.compareTo(builder.toString()) >= 0 ? builder.toString() : result;
            }
        }
        System.out.println(result);
    }
}
