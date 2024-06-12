package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    14888 : 연산자끼워넣기
//    ref url : https://www.acmicpc.net/problem/14888
public class 연산자끼워넣기 {
    static char[] operatorByIndex = new char[]{'+', '-', '*', '/'};

    static char[] operators;
    static int[] nums;
    static boolean[] checked;
    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        nums = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cnt = Arrays.stream(params).reduce(Integer::sum).getAsInt();
        operators = new char[cnt];
        checked = new boolean[cnt];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < params[i]; j++) {
                operators[index++] = operatorByIndex[i];
            }
        }
        track(1, nums[0]);
        System.out.println(max);
        System.out.println(min);

    }

    static void track(int numIndex, int prevNum) {
        if (numIndex == N) {
            max = Math.max(prevNum, max);
            min = Math.min(prevNum, min);
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            if (!checked[i]) {
                checked[i] = true;
                int calcNum = calc(operators[i], prevNum, nums[numIndex]);
                track(numIndex + 1, calcNum);
                checked[i] = false;
            }
        }
    }

    static int calc(char c, int n1, int n2) {
        switch (c) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                return n1 / n2;
            default: {
                System.out.println("bimbimbambam");
                return n1;
            }
        }
    }
}
