package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//    3273 : 두 수의 합
//    ref url : https://www.acmicpc.net/problem/3273
public class 두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        Set<Integer> set = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toSet());
        int result = 0;
        int x = Integer.parseInt(in.readLine());
        for (Integer a : set)
            if (x - a != a && set.contains(x - a))
                result++;
        System.out.println(result / 2);
    }
}
