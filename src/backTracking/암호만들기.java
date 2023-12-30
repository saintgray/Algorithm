package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//	1759 : 암호 만들기
//	ref url : https://www.acmicpc.net/problem/1759
public class 암호만들기 {

    static Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static boolean[] visited;
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int L = 0;
    static int C = 0;

    public static void main(String[] args) throws IOException {
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        L = params[0];
        C = params[1];
        Character[] chars = Arrays.stream(in.readLine().split(" ")).map(e -> e.charAt(0)).sorted().toArray(Character[]::new);
        visited = new boolean[C];
        runTracking(chars, -1, 0, "");
        out.flush();
    }

    static void runTracking(Character[] chars, int index, int depth, String code) throws IOException {
        if (depth >= L) {
            int consonants = 0;
            boolean hasVowel = false;
            char[] codeChars = code.toCharArray();
            for (char codeChar : codeChars) {
                if (set.contains(codeChar)) {
                    hasVowel = true;
                } else {
                    consonants++;
                }
            }
            if (consonants >= 2 && hasVowel) {
                out.write(code);
                out.newLine();
            }
        } else {
            for (int i = index + 1; i < chars.length; i++) {
                if (!visited[i]) {
                    code = code.concat(String.valueOf(chars[i]));
                    visited[i] = true;
                    runTracking(chars, i, depth + 1, code);
                    visited[i] = false;
                    code = code.substring(0, depth);
                }
            }
        }
    }
}
