package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    5218 : 알파벳 거리
//    ref url : https://www.acmicpc.net/problem/5218
public class 알파벳거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            out.write("Distances: ");
            String[] xy = in.readLine().split(" ");
            char[] x = xy[0].toCharArray();
            char[] y = xy[1].toCharArray();
            for (int j = 0; j < x.length; j++) {
                int c1 = (int) x[j] - 64;
                int c2 = (int) y[j] - 64;
                int distance = c2 >= c1 ? c2 - c1 : (c2 +26) - c1;
                out.write(String.format("%s ", distance));
            }
            out.newLine();
        }
        out.flush();
    }
}
