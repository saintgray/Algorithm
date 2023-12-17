package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    5073 : 삼각형과 세 변
//    ref url : https://www.acmicpc.net/problem/5073
public class 삼각형과세변 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] abc = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(abc);
        int max = abc[abc.length - 1];
        max = (max >= abc[0] + abc[1]) ? abc[0] + abc[1] - 1 : max;
        System.out.println(max + abc[0] + abc[1]);
    }
}
