package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    2910 : 빈도정렬
//    ref url : https://www.acmicpc.net/problem/2910
public class 빈도정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        // 숫자별 나온 횟수
        Map<Integer, Integer> cntMap = new HashMap<>();
        // 숫자별 최초 등장한 Index
        Map<Integer, Integer> seqMap = new HashMap<>();
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = param[0];
        int C = param[1];
        int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            cntMap.merge(input[i], 1, Integer::sum);
            seqMap.merge(input[i], i, Math::min);
        }
        // 숫자별 나온 횟수, 횟수가 같으면 등장한 Index 순으로 숫자를 정렬한다.
        List<Integer> result = new ArrayList<>(cntMap.keySet());
        result.sort(Comparator.comparing(cntMap::get, Comparator.reverseOrder())
                .thenComparing(seqMap::get, Comparator.naturalOrder()));
        for (Integer num : result) {
            int cnt = cntMap.get(num);
            while (cnt-- > 0) {
                out.write(String.format("%s ",num));
            }
        }
        out.flush();
    }
}
