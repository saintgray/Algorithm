package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    17952 : 과제는 끝나지 않아
//    ref url : https://www.acmicpc.net/problem/17952
public class 과제는끝나지않아 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        Stack<Hw> stk = new Stack<>();

        int total = 0;
        for (int i = 0; i < N; i++) {
            String input = in.readLine();
            if("0".equals(input)) {
                if(!stk.isEmpty()) {
                    Hw last = stk.peek();
                    last.remain--;
                    if (last.remain == 0) {
                        total += last.fs;
                        stk.pop();
                    }
                }
            } else {
                Hw newHw = new Hw(input);
                newHw.remain--;
                if (newHw.remain == 0) {
                    total += newHw.fs;
                } else {
                    stk.push(newHw);
                }
            }

        }
        System.out.println(total);
    }

    public static class Hw {
        int fs;
        int t;
        int remain;

        public Hw(String input) {
            String[] param = input.split(" ");
            this.fs = Integer.parseInt(param[1]);
            this.t = Integer.parseInt(param[2]);
            this.remain = this.t;
        }
    }
}
