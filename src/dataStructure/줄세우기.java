package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

//    2605 : 줄세우기
//    ref url : https://www.acmicpc.net/problem/2605

public class 줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> result = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();

        int students = Integer.parseInt(in.readLine());
        int[] tickets = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < students; i++) {
            reSorting(tempStack, result, tickets[i], i + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : result)
            sb.append(String.format("%d ", num));
        System.out.println(sb);
        in.close();
    }

    static void reSorting(Stack<Integer> tempStack, Stack<Integer> result, int ticketNum, int studentNum) {
        for (int i = 0; i < ticketNum; i++) {
            if (!result.isEmpty())
                tempStack.push(result.pop());
        }
        result.push(studentNum);
        align(tempStack, result);
    }

    static void align(Stack<Integer> tempStack, Stack<Integer> result) {
        while (!tempStack.isEmpty()) {
            result.push(tempStack.pop());
        }
    }
}
