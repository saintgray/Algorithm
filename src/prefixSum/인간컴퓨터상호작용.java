package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    16139 : 인간-컴퓨터 상호작용
//    ref url : https://www.acmicpc.net/problem/16139
public class 인간컴퓨터상호작용 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        char[] string = in.readLine().toCharArray();
        // abcdefghijklmnopqrstuvwxyz 26
        // ASCII a : 97, z : 122
        int[][] sum = new int[26][string.length + 1];
        for (int i = 0; i < string.length; i++) {
            char c = string[i];
            int cIdx = ((int) c) - 97;
            for (int _cIdx = 0; _cIdx < 26; _cIdx++) {
                char _c = (char) (_cIdx + 97);
                sum[_cIdx][i + 1] = sum[_cIdx][i] + (_c == c ? 1 : 0);
            }
        }
        int q = Integer.parseInt(in.readLine());
        while (q-- > 0) {
            String[] param = in.readLine().split(" ");
            int cIdx = ((int) param[0].charAt(0)) - 97;
            int st = Integer.parseInt(param[1]) + 1;
            int ed = Integer.parseInt(param[2]) + 1;
            out.write(String.valueOf(sum[cIdx][ed] - sum[cIdx][st - 1]));
            out.newLine();
        }
        out.flush();
    }
}
