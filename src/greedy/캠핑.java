package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    4796 : 캠핑
//    ref url : https://www.acmicpc.net/problem/4796
public class 캠핑 {
    static final String FORMAT = "Case %d: %d\n";
    static final String EOF = "0 0 0";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCnt = 1;
        String input;
        while(!(input = in.readLine()).equals(EOF)){
            int[] params = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            int L = params[0];
            int P = params[1];
            int V = params[2];
            int rest = Math.min(L,  V % P);
            out.write(String.format(FORMAT, caseCnt++, ((V / P) * L) + rest));
        }
        out.flush();
    }
}
