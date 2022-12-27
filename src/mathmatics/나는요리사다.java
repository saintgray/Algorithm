package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

// 2935 : 나는 요리사다
// 문제 참조 : https://www.acmicpc.net/problem/2935
public class 나는요리사다 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < 5; i++) {
            int sum = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).reduce(Integer::sum).getAsInt();
            map.put(sum, i+1);
        }
        System.out.println(String.format("%d %d", map.lastEntry().getValue(), map.lastEntry().getKey()));
    }
}
