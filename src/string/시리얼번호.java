package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;

//    1431 : 시리얼 번호
//    ref url : https://www.acmicpc.net/problem/1431
public class 시리얼번호 {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int cnt = Integer.parseInt(in.readLine());
        List<String> guitars = new ArrayList<>();
        for (int i = 0; i < cnt; i++)
            guitars.add(in.readLine());
        guitars.stream().sorted(Comparator.comparing(String::length)
                        .thenComparing((s1, s2) -> {
                            OptionalInt sum1Opt = Arrays.stream(s1.split("")).filter(e -> e.matches("\\d+")).mapToInt(Integer::parseInt).reduce(Integer::sum);
                            OptionalInt sum2Opt = Arrays.stream(s2.split("")).filter(e -> e.matches("\\d+")).mapToInt(Integer::parseInt).reduce(Integer::sum);
                            int sum1 = sum1Opt.orElse(0);
                            int sum2 = sum2Opt.orElse(0);
                            if (sum1 == sum2) {
                                for (int i = 0; i < s1.length(); i++) {
                                    if (s1.charAt(i) < s2.charAt(i)) {
                                        return -1;
                                    } else if (s1.charAt(i) > s2.charAt(i)) {
                                        return 1;
                                    }
                                }
                                return 0;
                            }
                            return Integer.compare(sum1, sum2);
                        }))
                .forEach(s -> builder.append(s).append("\n"));
        System.out.println(builder);
    }
}
