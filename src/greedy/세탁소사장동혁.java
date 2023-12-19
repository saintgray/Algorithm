package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    2720 : 세탁소사장동혁
//    ref url : https://www.acmicpc.net/problem/2720
public class 세탁소사장동혁 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(in.readLine());
        // 100 cent = 1$
        // 쿼터(Quarter, $0.25) = 25 cent
        // 다임(Dime, $0.10) = 10 cent
        // 니켈(Nickel, $0.05) = 5 cent
        // 페니(Penny, $0.01) = 1 cent
        for (int i = 0; i < T; i++) {
            int cent = Integer.parseInt(in.readLine());
            out.write(String.format("%s %s %s %s",
                    (cent / 25),
                    (cent %= 25) / 10,
                    ((cent %= 25) % 10) / 5,
                    ((cent % 25) % 10) % 5
            ));
            out.newLine();
        }
        out.flush();
    }
}
