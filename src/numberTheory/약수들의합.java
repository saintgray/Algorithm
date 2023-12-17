package numberTheory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//    9506 : 약수들의 합
//    ref url : https://www.acmicpc.net/problem/9506
public class 약수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = null;
        while(!isEOF(s = in.readLine())) {
            int N = Integer.parseInt(s);
            List<String> list = new ArrayList<>();
            int sum = 0;
            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    sum += i;
                    list.add(String.valueOf(i));
                }
            }
            out.write(sum != N ? String.format("%s is NOT perfect.", N) :
                    String.format("%s = %s", N, String.join(" + ", list)));
            out.newLine();

        }
        out.flush();
    }

    public static boolean isEOF(String s) {
        return s == null || s.equals("") || s.equals("-1");
    }
}
