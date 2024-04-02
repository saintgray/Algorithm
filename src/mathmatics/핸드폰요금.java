package mathmatics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1267 : 핸드폰 요금
// 문제 참조 : https://www.acmicpc.net/problem/1267
public class 핸드폰요금 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] times = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            int q = time / 30;
            p1 += (q * 10) + (10);
            q = time / 60;
            p2 += (q * 15) + (15);
        }
        System.out.println(String.format("%s %s",
                p1 > p2 ? "M" : p1 < p2 ? "Y" : "Y M",
                Math.min(p1, p2)
        ));
    }
}
