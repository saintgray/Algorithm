package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    4470 : 줄번호
//    ref url : https://www.acmicpc.net/problem/4470
public class 줄번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            out.write(String.format("%d. %s\n", i + 1, in.readLine()));
        }
        out.flush();
    }
}
