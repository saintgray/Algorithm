package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//	5598 : 균형잡힌 세상
//	ref url : https://www.acmicpc.net/problem/5598
public class 카이사르암호 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] chars = in.readLine().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int ascii = ((int) chars[i]) - 3;
            out.write(String.valueOf((char) (ascii <= 64 ? 90 - (64 - ascii) : ascii)));
        }
        out.flush();
    }
}
