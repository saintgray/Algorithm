package util;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class AlphabetSequence {

    public class Main {

        public static void main(String[] args) throws Exception {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for(int decimal = 27; decimal < 701; decimal++){
                // 27 : AA(26진수) 의 10진수 변환 , 701 : ZY(26진수) 의 10진수 변환
                out.write(String.format("%s\n",generateAlphabetSeq(decimal)));
            }
            out.flush();
            out.close();
        }

        static String generateAlphabetSeq(int decimal) throws Exception{
            int nextDecimal = decimal + 1;
            Stack<Integer> stack = new Stack<>();
            while(nextDecimal  > 26){
                int remain = nextDecimal % 26;
                stack.push(remain == 0 ? 26 : remain);
                nextDecimal = remain == 0 ? ((nextDecimal / 26) -1) : nextDecimal / 26;
            }
            stack.push(nextDecimal);
            StringBuilder nextCode = new StringBuilder();
            while(!stack.isEmpty())
                nextCode.append((char)(stack.pop() + 64));

            if(nextCode.length() >= 3)
                throw new Exception("자리수 초과");
            return nextCode.toString();
        }
    }
}
