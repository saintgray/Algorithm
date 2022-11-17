package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class AlphabetSequence {


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        // AA(10 진수 53) ~ zy(10 진수 2755) 시퀀스 발번 테스트
        while(true){
            String command = in.readLine();
            if(command.equalsIgnoreCase("q"))
                break;
            System.out.println(generateConstructJobSequence(command));
        }
    }

    static String generateConstructJobSequence(String sequence) throws Exception {

        if(sequence.equals("ZZ"))
            return "aa";
        if(sequence.startsWith("A"))
            return "BB";
        boolean lowCase = sequence.charAt(0) > 90 || sequence.charAt(1) > 90;
        sequence = sequence.toUpperCase();
        int first = sequence.charAt(0) <= 90 ? sequence.charAt(0) - 64 : sequence.charAt(0) - 70;
        int second = sequence.charAt(1) <= 90 ? sequence.charAt(1) - 64 : sequence.charAt(1) - 70;
        int nextDecimal = ((26 * first) + second) + 1;
        Stack<Integer> stack = new Stack<>();
        while (nextDecimal > 26) {
            int remain = nextDecimal % 26;
            stack.push(remain == 0 ? 26 : remain);
            nextDecimal = remain == 0 ? ((nextDecimal / 26) - 1) : nextDecimal / 26;
        }
        stack.push(nextDecimal);
        StringBuilder nextCode = new StringBuilder();
        while (!stack.isEmpty())
            nextCode.append((char) (stack.pop() + 64));
        return lowCase ? nextCode.toString().toLowerCase() : nextCode.toString().toUpperCase();
    }
}
