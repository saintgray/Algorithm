package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//    문제
//    어떤 사람의 C언어 성적이 주어졌을 때,
//    평점은 몇 점인지 출력하는 프로그램을 작성하시오.
//
//    A+: 4.3, A0: 4.0, A-: 3.7
//    B+: 3.3, B0: 3.0, B-: 2.7
//    C+: 2.3, C0: 2.0, C-: 1.7
//    D+: 1.3, D0: 1.0, D-: 0.7
//    F: 0.0
//
//    입력
//    첫째 줄에 C언어 성적이 주어진다.
//    성적은 문제에서 설명한 13가지 중 하나이다.
//
//    출력
//    첫째 줄에 C언어 평점을 출력한다.

public class 학점계산 {

    private Map<String, String> scoreMap;

    public 학점계산() {
        this.scoreMap = new HashMap<>();
        scoreMap.put("A+","4.3");
        scoreMap.put("A0","4.0");
        scoreMap.put("A-","3.7");

        scoreMap.put("B+","3.3");
        scoreMap.put("B0","3.0");
        scoreMap.put("B-","2.7");

        scoreMap.put("C+","2.3");
        scoreMap.put("C0","2.0");
        scoreMap.put("C-","1.7");

        scoreMap.put("D+","1.3");
        scoreMap.put("D0","1.0");
        scoreMap.put("D-","0.7");

        scoreMap.put("F","0.0");
    }

    public Map<String, String> getScoreMap() {
        return scoreMap;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        학점계산 main = new 학점계산();
            System.out.println(main.getScoreMap().get(in.readLine()));
        }
}
