package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//	7490 : 0 만들기
//	ref url : https://www.acmicpc.net/problem/7490
public class 영만들기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;

    static char[] operator = new char[]{' ', '+', '-'};

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(in.readLine());
        while (TC-- > 0) {
            N = Integer.parseInt(in.readLine());
            track(1, 1, new ArrayList<>());
            if (TC > 0) out.newLine();
        }
        out.flush();

    }

    static void track(int depth, int num, List<String> history) throws IOException {
        history.add(String.valueOf(num));
        if (depth == N) {
            String historyStr = String.join("", history);
            String result = historyStr.replaceAll(" ", "");
            int sum = 0;
            String n = "";
            char operator = ' ';
            for (int i = 0; i < result.length(); i++) {
                char c = result.charAt(i);
                if (isOperator(c)) {
                    if (operator == ' ') {
                        sum = Integer.parseInt(n);
                    } else {
                        sum = doCalc(operator, sum, Integer.parseInt(n));
                    }
                    operator = c;
                    n = "";
                } else {
                    n = n.concat(String.valueOf(c));
                    if (i == result.length() - 1) {
                        sum = doCalc(operator, sum, Integer.parseInt(n));
                    }
                }
            }
            if (sum == 0) {
                out.write(historyStr);
                out.newLine();
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            history.add(String.valueOf(operator[i]));
            track(depth + 1, num + 1, history);
            history.remove(history.size() - 1);
            history.remove(history.size() - 1);
        }
    }

    static int doCalc(char operator, int n1, int n2) {
        return operator == '+' ? n1 + n2 : n1 - n2;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-';
    }

}