package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    15904 : UCPC는 무엇의 약자일까?
//    ref url : https://www.acmicpc.net/problem/15904
public class UCPC는무엇의약자일까 {
    static final char[] UCPC = {'U', 'C', 'P', 'C'};

    public static void main(String[] args) throws IOException {

        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        char[] chars = input.toCharArray();
        int index = 0;
        for (char c : chars) {
            if (c == UCPC[index])
                index++;
            if (index == UCPC.length)
                break;
        }
        System.out.println(index == UCPC.length ? "I love UCPC" : "I hate UCPC");
    }
}
