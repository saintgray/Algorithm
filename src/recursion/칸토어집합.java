package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    4779 : 칸토어 집합
//    ref url : https://www.acmicpc.net/problem/4779
public class 칸토어집합 {

    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = null;
        while ((input = in.readLine()) != null) {
            int N = Integer.parseInt(input);
            chars = new char[(int) Math.pow(3, N)];
            Arrays.fill(chars, '-');
            divide(0, chars.length - 1, chars.length / 3);
            for (char c : chars) {
                out.write(c);
            }
            out.flush();
            out.newLine();
        }
    }

    static void divide(int from, int to, int cutLength) {
        if (from == to)
            return;
        int mid = (from + to) / 2;
        int cutStIdx = mid - (cutLength - 1) / 2;
        int cutEdIdx = mid + (cutLength - 1) / 2;
        for (int i = cutStIdx; i <= cutEdIdx; i++) {
            chars[i] = ' ';
        }
        divide(from, cutStIdx - 1, (cutEdIdx - cutStIdx + 1) / 3);
        divide(cutEdIdx + 1, to, (cutEdIdx - cutStIdx + 1) / 3);
    }
}