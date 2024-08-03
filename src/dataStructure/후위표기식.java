package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    1918 : 후위 표기식
//    ref url : https://www.acmicpc.net/problem/1918
public class 후위표기식 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer buf = new StringBuffer();


    public static void main(String[] args) throws IOException {
        char[] ipt = in.readLine().toCharArray();
        Stack<Operator> operators = new Stack<>();
        int depth = 0;
        for (int i = 0; i < ipt.length; i++) {
            char c = ipt[i];
            if (c == '(') {
                depth++;
                continue;
            }
            if (c == ')') {
                depth--;
                continue;
            }
            if (!isOperator(c)) {
                buf.append(c);
                continue;
            }
            if (operators.isEmpty()) {
                operators.push(new Operator(c, depth, i));
                continue;
            }

            Operator operator = new Operator(c, depth, i);
            int priority = comparePriority(operator, operators.peek());
            if (priority <= 0) {
                operators.push(operator);
            } else {
                while(!operators.isEmpty() && comparePriority(operator, operators.peek()) > 0) {
                    buf.append(operators.pop().op);
                }
                operators.push(operator);
            }
        }
        // 남아있는 연산자 작성
        flush(operators);
        System.out.println(buf.toString());

    }

    // 연산자 표기 우선순위
    // 1. 괄호 depth 가 깊은 연산자
    // 2. 깊이가 같을 시 곱셈 나눗셈인 연산자
    // 3. 연산자의 순위도 같으면 index 가 큰 연산자
    // -1 : 후위 표기 대상
    static int comparePriority(
            Operator cur,       // 현재 연산자
            Operator peek       // stack 의 마지막 연산자
    ) {
        if (cur.depth > peek.depth) {
            return -1;
        }
        else if (cur.depth < peek.depth) {
            return 1;
        }
        else {
            char op = cur.op;
            char compareOp = peek.op;
            if ((op == '*' || op == '/') && (compareOp == '+' || compareOp == '-')) {
                return -1;
            } else if ((op == '*' || op == '/') && (compareOp == '*' || compareOp == '/')) {
                return Integer.compareUnsigned(cur.index, peek.index);
            } else if ((op == '+' || op == '-') && (compareOp == '+' || compareOp == '-')) {
                return Integer.compareUnsigned(cur.index, peek.index);
            }
        }
        return 1;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static void flush(Stack<Operator> stack) {
        while (!stack.isEmpty()) {
            buf.append(stack.pop().op);
        }
    }

    static class Operator {
        char op;
        int depth;
        int index;

        public Operator(char op, int depth, int index) {
            this.op = op;
            this.depth = depth;
            this.index = index;
        }
    }
}