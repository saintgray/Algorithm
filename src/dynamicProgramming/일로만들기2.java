package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

//    12852 : 1로 만들기 2
//    ref url : https://www.acmicpc.net/problem/12852
public class 일로만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        Integer[] memo = new Integer[N + 1];
        int[] pathMemo = new int[N + 1];

        memo[N] = 0;
        for (int i = N; i >= 0; i--) {
            if (i % 3 == 0) {
                if (memo[i / 3] == null) {
                    memo[i / 3] = memo[i] + 1;
                    pathMemo[i / 3] = i;
                } else {
                    if (memo[i] + 1 < memo[i / 3])
                        pathMemo[i / 3] = i;
                    memo[i / 3] = Math.min(memo[i] + 1, memo[i / 3]);
                }
            }
            if (i % 2 == 0) {

                if (memo[i / 2] == null) {
                    memo[i / 2] = memo[i] + 1;
                    pathMemo[i / 2] = i;
                } else {
                    if (memo[i] + 1 < memo[i / 2])
                        pathMemo[i / 2] = i;
                    memo[i / 2] = Math.min(memo[i] + 1, memo[i / 2]);
                }
            }
            if (i - 1 >= 0) {
                if (memo[i - 1] == null) {
                    memo[i - 1] = memo[i] + 1;
                    pathMemo[i - 1] = i;
                } else {
                    if (memo[i] + 1 < memo[i - 1])
                        pathMemo[i - 1] = i;
                    memo[i - 1] = Math.min(memo[i] + 1, memo[i - 1]);
                }
            }
        }

        out.write(String.format("%s\n", memo[1]));
        Stack<Integer> pathStack = new Stack<>();
        int index = 1;
        pathStack.push(1);
        while (index != N) {
            pathStack.push(pathMemo[index]);
            index = pathMemo[index];
        }
        while(!pathStack.isEmpty())
            out.write(String.format("%d ", pathStack.pop()));
        out.flush();
    }
}

