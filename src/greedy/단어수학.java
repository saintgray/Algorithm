package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//    1339 : 단어수학
//    ref url : https://www.acmicpc.net/problem/1339
public class 단어수학 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        // 10 * 자리수 합
        Map<Character, Integer> count = new HashMap<>();
        int N = Integer.parseInt(in.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.readLine();
            char[] word = arr[i].toCharArray();
            for (int j = 0; j < word.length; j++) {
                count.merge(word[j], (int) Math.pow(10, word.length - j), Integer::sum);
            }
        }
        int[] weight = new int[count.size()];
        int max = 9;
        for (int i = 0; i < weight.length; i++) {
            weight[i] = max--;
        }
        // 문자가 위치한 10 * 자리수의 총 합이 가장 클것
        List<Character> chars =
                count.keySet().stream()
                        .sorted(Comparator.comparing(count::get, Comparator.reverseOrder()))
                        .collect(Collectors.toList());
        Map<Character, Integer> numMap = new HashMap<>();
        for (int i = 0; i < chars.size(); i++) {
            numMap.put(chars.get(i), weight[i]);
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            StringBuffer buf = new StringBuffer();
            char[] word = arr[i].toCharArray();
            for (int j = 0; j < word.length; j++) {
                buf.append(numMap.get(word[j]));
            }
            sum += Integer.parseInt(buf.toString());
        }
        System.out.println(sum);
    }
}
