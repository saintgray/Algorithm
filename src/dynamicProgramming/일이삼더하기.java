package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    9095 : 1, 2, 3 더하기
//    ref url : https://www.acmicpc.net/problem/9095
public class 일이삼더하기 {
    static int cases = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(in.readLine());
        for (int i = 0; i < testCases; i++) {
            int n = Integer.parseInt(in.readLine());
            Stack<Integer> hist = new Stack<>();
            run(n, hist);
            System.out.println(cases);
            cases = 0;
        }
        in.close();
    }

    static void run(int n, Stack<Integer> hist) {
        int sum = sum(hist);
        if (sum == n) {
            cases++;
            return;
        } else {
            for (int i = 1; i <= 3; i++) {
                if (sum + i <= n) {
                    hist.push(i);
                    run(n, hist);
                    hist.pop();
                }
            }
        }
    }

    static int sum(Stack<Integer> hist) {
        int sum = 0;
        for (int n : hist)
            sum += n;
        return sum;
    }
}
