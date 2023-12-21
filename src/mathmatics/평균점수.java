package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    10039 : 평균 점수
//    ref url : https://www.acmicpc.net/problem/10039
public class 평균점수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] scores = new int[5];
        for (int i = 0; i < 5; i++) {
            int score = Integer.parseInt(in.readLine());
            score = Math.max(score, 40);
            scores[i] = score;
        }
        System.out.println(Arrays.stream(scores).reduce(Integer::sum).getAsInt() / 5);
    }
}
