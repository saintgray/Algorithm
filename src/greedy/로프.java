package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2217 : 로프
//    ref url : https://www.acmicpc.net/problem/2217
public class 로프 {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++)
            ropes[i] = Integer.parseInt(in.readLine());
        Arrays.sort(ropes);
        for (int i = 0; i < N; i++)
            max = Math.max(max, ropes[i] * (N-i));
        System.out.println(max);
    }
}
