package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

//    28278 : 스택2
//    ref url : https://www.acmicpc.net/problem/28278
public class 스택2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.flush();
        Stack<Integer> stk = new Stack();
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cmd = input[0];
            if (cmd == 1) {
                stk.add(input[1]);
            } else if (cmd == 2 || cmd == 5) {
                try {
                    out.write(cmd == 2 ? String.valueOf(stk.pop()) : String.valueOf(stk.peek()));
                } catch (Exception e) {
                    out.write("-1");
                }
                out.newLine();
            } else if(cmd == 3) {
                out.write(String.valueOf(stk.size()));
                out.newLine();
            } else {
                out.write(stk.isEmpty() ? "1" : "0");
                out.newLine();
            }
        }
        out.flush();
    }
}

// Q) Stack class 내 함수 및 LIFO 특성의 구현은 List interface
//    의 구현체로도 충분히 구현할 수 있다고 보여지는데
//    그러함에도 Stack 을 사용하는 경우는 대용량 데이터 입력값을 처리할떄
//    처리 속도의 차이가 있어 사용하는 것인지..?
