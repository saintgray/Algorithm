package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 별찍기8 {
    public static final char STAR = '*';
    public static final char BLANK = ' ';
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(in.readLine());
        boolean lowerSide;
        for (int r = 0; r < (2 * N) - 1; r++) {
            lowerSide = r >= N;
            int starCnt = lowerSide ? (2 * N) - 1 - r : r + 1;
            int blankCnt = (2 * N) - (2 * starCnt);
            for (int i = 0; i < starCnt; i++) out.write(STAR);
            for (int i = 0; i < blankCnt; i++) out.write(BLANK);
            for (int i = 0; i < starCnt; i++) out.write(STAR);
            out.newLine();
        }
        out.flush();
    }
}
