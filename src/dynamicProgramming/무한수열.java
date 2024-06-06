package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    1351 : 무한 수열
//    ref url : https://www.acmicpc.net/problem/1351
public class 무한수열 {

    static Map<Long, Long> dp = new HashMap();
    static long P;
    static long Q;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long[] params = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long N = params[0];
        P = params[1];
        Q = params[2];
        dp.put(0L, 1L);
        System.out.println(N == 0 ? 1 : find(N/P) + find(N/Q));
    }

    static long find(long i) {
        Long dpVal = dp.get(i);
        if(dpVal != null) return dpVal;
        Long a = find(i/P);
        Long b = find(i/Q);
        dp.put(i/P, a);
        dp.put(i/Q, b);
        return a + b;
    }
}
