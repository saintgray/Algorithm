package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2166 : 다각형의 면적
//    ref url : https://www.acmicpc.net/problem/2166

public class 다각형의면적 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int pointsCnt = Integer.parseInt(in.readLine());
        double[][] points = new double[pointsCnt+1][];
        for (int i = 0; i < pointsCnt; i++)
            points[i] = Arrays.stream(in.readLine().split(" ")).mapToDouble(e -> Double.parseDouble(e)).toArray();
        points[pointsCnt] = points[0];
        double rightDiagnoalSum = 0;
        double leftDiagnoalSum = 0;
        for (int i = 0; i < pointsCnt; i++) {
            rightDiagnoalSum += points[i][0] * points[i+1][1];
            leftDiagnoalSum += points[i][1] * points[i+1][0];
        }
        System.out.println(String.format("%.1f", (Math.abs(leftDiagnoalSum - rightDiagnoalSum))/2));
        in.close();
    }
}

// 함께보기
// 다각형의 넓이 구하기 : https://ko.wikihow.com/%EB%8B%A4%EA%B0%81%ED%98%95-%EB%84%93%EC%9D%B4-%EA%B5%AC%ED%95%98%EA%B8%B0