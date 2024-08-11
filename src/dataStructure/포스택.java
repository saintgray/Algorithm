package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

//    25556 : 포스택
//    ref url : https://www.acmicpc.net/problem/25556
public class 포스택 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static Stack[] stacks = new Stack[4];

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(in.readLine());
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean canBeSorted = true;
        for (int i = 0; i < N; i++) {
            int n = p[i];
            boolean pushed = false;
            for (int j = 0; j < 4; j++) {
                Stack stack = (stacks[j] = Optional.ofNullable(stacks[j]).orElseGet(Stack::new));
                if (stack.isEmpty() || (int) stack.peek() < n) {
                    stack.push(n);
                    pushed = true;
                    break;
                }
            }
            if (!pushed) {
                canBeSorted = false;
                break;
            }
        }
        System.out.println(canBeSorted ? "YES" : "NO");
    }
}
