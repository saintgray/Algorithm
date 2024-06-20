package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

//    1971 : 놀라운 문자열
//    ref url : https://www.acmicpc.net/problem/1971
public class 놀라운문자열 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String str = null;
        while (!"*".equals(str = in.readLine())) check(str);
        out.flush();
    }

    static void check(String str) throws IOException {
        int len = str.length();
        for (int pair = 0; pair <= len - 2; pair++) {
            Set<String> distinct = new HashSet<>();
            for (int idx = 0; idx < len; idx++) {
                if (idx + pair + 1 >= len) continue;
                String pairStr = new String(new char[]{str.charAt(idx), str.charAt(idx + pair + 1)});
                if (distinct.contains(pairStr)) {
                    out.write(str + " is NOT surprising.\n");
                    return;
                } else {
                    distinct.add(pairStr);
                }
            }
        }
        out.write(str + " is surprising.\n");
    }
}
