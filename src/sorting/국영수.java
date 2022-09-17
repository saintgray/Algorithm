package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//    10825 : 국영수
//    ref url : https://www.acmicpc.net/problem/10825

public class 국영수 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int students = Integer.parseInt(in.readLine());
        Score[] score = new Score[students];
        for (int i = 0; i < students; i++)
            score[i] = new Score(in.readLine().split(" "));

        Arrays.stream(score).sorted(Comparator.comparing(Score::getKor, Comparator.reverseOrder())
                        .thenComparing(Score::getEng, Comparator.naturalOrder())
                        .thenComparing(Score::getMath, Comparator.reverseOrder())
                        .thenComparing(Score::getName, Comparator.naturalOrder()))
                .forEach(e -> result.append(e.getName().concat("\n")));
        System.out.println(result);
        in.close();
    }

    static class Score {
        String name;
        int kor;
        int eng;
        int math;
        public Score(String[] param) {
            this.name = param[0];
            this.kor = Integer.parseInt(param[1]);
            this.eng = Integer.parseInt(param[2]);
            this.math = Integer.parseInt(param[3]);
        }
        public String getName() {return name;}
        public int getKor() {return kor;}
        public int getEng() {return eng;}
        public int getMath() {return math;}
    }
}