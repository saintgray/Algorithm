package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    13305 : 주유소
//    ref url : https://www.acmicpc.net/problem/13305
public class 주유소 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        long[] d = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] prices = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long p = prices[0];
        long min = p * d[0];
        for (int i = 1; i < N-1; i++) {
            // 다음 도시를 갈때 이전 총 주유비를 계속 넣어 갈 경우와
            // 현재 도시에서 주유하고 출발할 경우 중 작은 쪽으로 선택
            long sum1 = min + (p * d[i]);
            long sum2 = min + (prices[i] * d[i]);
            if(sum1 > sum2) {
                p = prices[i];
                min = sum2;
            } else {
                min = sum1;
            }
        }
        System.out.println(min);
    }
}