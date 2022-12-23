package geometry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//    5073 : 삼각형과세변
//    ref url : https://www.acmicpc.net/problem/5073
public class 삼각형과세변 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;
        while (!"0 0 0".equals((input) = in.readLine())) {
            List<Integer> params = Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int max = params.stream().max(Comparator.naturalOrder()).get();
            int cnt = (int) params.stream().distinct().count();
            int maxCnt = (int) params.stream().filter(e -> e == max).count();
            boolean isTriangle = cnt == 1 ||
                    (cnt == 2 && ((maxCnt == 2) || (maxCnt == 1 && params.stream().filter(e -> e != max).reduce(Integer::sum).get() > max))) ||
                    max < params.stream().filter(e -> e != max).reduce(Integer::sum).get();
            out.write(!isTriangle ? "Invalid" : cnt == 1 ? "Equilateral" : cnt == 2 ? "Isosceles" : "Scalene");
            out.newLine();
        }
        out.flush();
        in.close();
        out.close();
    }
}
