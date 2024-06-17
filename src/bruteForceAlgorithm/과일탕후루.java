package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    30804 : 과일 탕후루
//    ref url : https://www.acmicpc.net/problem/30804
//    ref algorithm : two pointer
//    hint : https://www.youtube.com/watch?v=leNYnCS_RFA
public class 과일탕후루 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int[] huru = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int st = 0;
        int ed = 0;

        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> huruCnt = new HashMap<>();
        while(ed < N && st < N) {
            if (huruCnt.size() < 2 || huruCnt.containsKey(huru[ed])) {
                Integer count = huruCnt.get(huru[ed]);
                if (count == null) huruCnt.put(huru[ed], 1);
                else huruCnt.merge(huru[ed], 1, Integer::sum);
                max=Math.max(ed-st+1, max);
                ed++;
            } else {
                int count = huruCnt.get(huru[st]);
                if (count == 1) huruCnt.remove(huru[st]);
                else huruCnt.merge(huru[st],-1, Integer::sum);
                st++;
                max=Math.max(ed-st+1, max);
            }
        }
        System.out.println(max);
    }
}
