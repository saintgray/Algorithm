package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//    2822 : 점수 계산
//    ref url : https://www.acmicpc.net/problem/2822
public class 점수계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 8 ; i++)
            scores.add(Integer.parseInt(in.readLine()));
        List<Integer> finalScores = scores.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .subList(0, 5);
        System.out.println(finalScores.stream().reduce(Integer::sum).get());
        System.out.println(
                finalScores.stream()
                        .map(score -> String.valueOf(scores.indexOf(score) + 1))
                        .sorted(Comparator.comparing(Integer::parseInt, Comparator.naturalOrder()))
                        .collect(Collectors.joining(" "))
        );
    }
}
