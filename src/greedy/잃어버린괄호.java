package greedy;

//    1541 : 잃어버린 괄호
//    ref url : https://www.acmicpc.net/problem/1541

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] exp = in.readLine().split("-");
        int result  = Arrays.stream(Arrays.stream(exp[0].split("\\+")).mapToInt(e -> Integer.parseInt(e)).toArray()).reduce((total,n) -> total+n).getAsInt();;
        for (int i = 1; i < exp.length; i++) {
            int sum = Arrays.stream(Arrays.stream(exp[i].split("\\+")).mapToInt(e -> Integer.parseInt(e)).toArray()).reduce((total,n) -> total+n).getAsInt();
            result -= sum;
        }
        System.out.println(result);
        in.close();
    }
}
