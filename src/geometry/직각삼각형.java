package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

//    문제
//    과거 이집트인들은 각 변들의 길이가 3, 4, 5인 삼각형이 직각 삼각형인것을 알아냈다.
//    주어진 세변의 길이로 삼각형이 직각인지 아닌지 구분하시오.
//
//    입력
//    입력은 여러개의 테스트케이스로 주어지며 마지막줄에는 0 0 0이 입력된다.
//    각 테스트케이스는 모두 30,000보다 작은 양의 정수로 주어지며,
//    각 입력은 변의 길이를 의미한다.
//
//    출력
//    각 입력에 대해 직각 삼각형이 맞다면 "right", 아니라면 "wrong"을 출력한다.
public class 직각삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        while ((input = in.readLine()) != null) {
            String[] inputArr = input.split(" ");
            Integer var1 = Integer.parseInt(inputArr[0]);
            Integer var2 = Integer.parseInt(inputArr[1]);
            Integer var3 = Integer.parseInt(inputArr[2]);

            Integer longest = Math.max(var1, var2);
            longest = Math.max(longest, var3);
            if (longest.intValue() != 0) {
                if (longest.intValue() == var1) {
                    System.out.println(getResult(var1, var2, var3));
                } else if (longest.intValue() == var2) {
                    System.out.println(getResult(var2, var1, var3));
                } else if (longest.intValue() == var3) {
                    System.out.println(getResult(var3, var1, var2));
                }
            }
        }
        in.close();
    }

    public static String getResult(Integer longest, Integer var1, Integer var2) {
        BigDecimal longestDist = new BigDecimal(longest);
        BigDecimal restDist1 = new BigDecimal(var1);
        BigDecimal restDist2 = new BigDecimal(var2);

        BigDecimal logestPow = (longestDist.multiply(longestDist));
        BigDecimal restPowAdd = (restDist1.multiply(restDist1))
                .add((restDist2.multiply(restDist2)));
        boolean isRightTriangle = logestPow.equals(restPowAdd);
        return isRightTriangle
                ? "right"
                : "wrong";
    }

}
