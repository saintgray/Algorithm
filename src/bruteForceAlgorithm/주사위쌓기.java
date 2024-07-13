package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    2116 : 주사위 쌓기
//    ref url : https://www.acmicpc.net/problem/2116
public class 주사위쌓기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int max = Integer.MIN_VALUE;
    static List<Dice> diceList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            diceList.add(new Dice(p[0],p[1],p[2],p[3],p[4],p[5]));
        }

        Dice first = diceList.get(0);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int bottom = first.get(i);
            int top = first.getOtherSide(bottom);
            first.bottom = bottom;
            first.top = top;
            sum += first.max();
            for (int j = 1; j < N; j++) {
                Dice next = diceList.get(j);
                next.bottom = top;
                next.top = next.getOtherSide(next.bottom);
                top = next.top;
                sum += next.max();
            }
            max = Math.max(sum, max);
            sum = 0;
        }
        System.out.println(max);
    }

    static class Dice {
        int A;
        int B;
        int C;
        int D;
        int E;
        int F;

        int bottom;
        int top;

        public Dice(int a, int b, int c, int d, int e, int f) {
            this.A = a;
            this.B = b;
            this.C = c;
            this.D = d;
            this.E = e;
            this.F = f;
        }

        public int get(int idx) {
            return idx == 0 ? A :
                    idx == 1 ? B :
                            idx == 2 ? C :
                                    idx == 3 ? D :
                                            idx == 4 ? E : F;
        }

        int getOtherSide(int n) {
            return n == A ? this.F :
                    n == B ? this.D :
                            n == C ? this.E :
                                    n == D ? this.B :
                                            n== E ? this.C : this.A;
        }

        int max() {
            return Math.max(this.A == top || this.A == bottom ? 0 : A,
                    Math.max(this.B == top || this.B == bottom ? 0 : B,
                            Math.max(this.C == top || this.C == bottom ? 0 : C,
                                    Math.max(this.D == top || this.D == bottom ? 0 : D,
                                            Math.max(this.E == top || this.E == bottom ? 0 : E, this.F == top || this.F == bottom ? 0 : F)))));
        }
    }

}