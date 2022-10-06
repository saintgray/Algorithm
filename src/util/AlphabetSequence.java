package util;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class AlphabetSequence {


    public static void main(String[] args) throws Exception {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int decimal = 53; decimal < 2756; decimal++) {
            // AA(10 진수 53) ~ zy(10 진수 2755) 시퀀스 발번 테스트
            out.write(String.format("%s\n", generateConstructJobSequence(decimal)));
        }
            out.flush();
            out.close();
        }

        static String generateConstructJobSequence(int decimal) throws Exception {

            int nextDecimal = decimal + 1;
            Stack<Integer> stack = new Stack<>();
            while (nextDecimal > 52) {
                int remain = nextDecimal % 52;
                stack.push(remain == 0 ? 52 : remain);
                nextDecimal = remain == 0 ? ((nextDecimal / 52) - 1) : nextDecimal / 52;
            }
            stack.push(nextDecimal);

            if(stack.size() > 2)
                throw new Exception("자리수 초과");
            StringBuilder nextCode = new StringBuilder();
            while (!stack.isEmpty()) {
                int num = stack.pop() + 64;
                nextCode.append((char) (num <= 90 ? num : num + 6));
            }
            return nextCode.toString();
        }
}
