package string;

// 숫자의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.IntStream;

//문제
//N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 숫자의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄에 숫자 N개가 공백없이 주어진다.
//
//출력
//입력으로 주어진 숫자 N개의 합을 출력한다.
public class 숫자의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        char[] charArr = in.readLine().toCharArray();
        Optional<BigDecimal> sum = IntStream.range(0, charArr.length)
                .mapToObj(i -> new BigDecimal(String.valueOf(charArr[i])))
                .reduce(BigDecimal::add);
        System.out.println(sum.get());
        in.close();
    }
}
