package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    17608 : 막대기
//    ref url : https://www.acmicpc.net/problem/17608
public class 막대기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(Integer.parseInt(in.readLine()));
        }
        int maxHeight = Integer.MIN_VALUE;
        int result = 0;
        while (!stack.isEmpty()) {
            int h = stack.pop();
            if (h > maxHeight) result++;
            maxHeight = Math.max(h, maxHeight);
        }
        System.out.println(result);
    }
}
