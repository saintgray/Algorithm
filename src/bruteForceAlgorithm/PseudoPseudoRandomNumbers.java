package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    24776 : Pseudo Pseudo Random Numbers
//    ref url : https://www.acmicpc.net/problem/24776
public class PseudoPseudoRandomNumbers {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int[] num = new int[]{1,0};
    static int n;
    static int k;
    static int result;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = p[0];
        k = p[1];
        track(0, 1, 0);
        System.out.println(result);
    }

    static void track(int continousCnt, int prev, int depth) {
        if(continousCnt > k) return;
        if(depth == n) {
            result++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            int nextNum = num[i];
            track(prev == nextNum ? continousCnt + 1 : 1, nextNum, depth + 1);
        }
    }
}
