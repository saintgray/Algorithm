package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 좋은단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Stack<Character> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < N; i++) {
            char[] arr = in.readLine().toCharArray();
            for (char c1 : arr) {
                if (stack.isEmpty()) {
                    stack.push(c1);
                } else {
                    char c2 = stack.peek();
                    if (c1 == c2)
                        stack.pop();
                    else
                        stack.push(c1);
                }
            }
            result += stack.isEmpty() ? 1 : 0;
            stack.clear();
        }
        System.out.println(result);
    }
}
