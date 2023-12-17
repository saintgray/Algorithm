package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    9063 : 대지
//    ref url : https://www.acmicpc.net/problem/9063
public class 대지 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            int[] xy = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            x[i] = xy[0];
            y[i] = xy[1];
        }
        Arrays.sort(x);
        Arrays.sort(y);
        System.out.println(Math.abs(x[x.length - 1] - x[0]) * Math.abs(y[y.length - 1] - y[0]));
    }
}




