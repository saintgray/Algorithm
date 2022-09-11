package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//	4949 : 균형잡힌 세상
//	ref url : https://www.acmicpc.net/problem/4949

public class 균형잡힌세상 {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        StringBuilder result = new StringBuilder();
        while (!(input = in.readLine()).equals(".")) {
            Stack<Character> stack = new Stack<>();
            boolean balanced = true;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!(c == '[' || c == ']' || c == '(' || c == ')'))
                    continue;
                if (stack.empty()) {
                    stack.push(c);
                } else {
                    char peek = stack.peek();
                    if ((c == ']' && peek == '[') || (c == ')' && peek == '(')) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }
            result.append(stack.isEmpty() ? "yes\n" : "no\n");
        }
        System.out.println(result);
        in.close();
    }
}
