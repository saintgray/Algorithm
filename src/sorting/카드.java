package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//    11652 : 카드
//    ref url : https://www.acmicpc.net/problem/11652
public class 카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Map<BigDecimal, BigDecimal> map = new HashMap<>();
        for (int i = 0; i < N ; i++) {
            map.merge(new BigDecimal(in.readLine()), BigDecimal.ONE, BigDecimal::add);
        }
        List<BigDecimal> sortedList = map.keySet()
                .stream()
                .sorted(Comparator.comparing(map::get))
                .collect(Collectors.toList());
        // max count number
        BigDecimal lastInt = sortedList.get(sortedList.size() -1);
        // max count
        final BigDecimal maxCount = map.get(lastInt);
        // resolve minimum number from numbers have same max count
        BigDecimal result = lastInt;
        for (int i = sortedList.size() - 1; i >= 0; i--) {
            BigDecimal num = sortedList.get(i);
            if (map.get(sortedList.get(i)).equals(maxCount)) {
                result = result.compareTo(num) > 0 ? num : result;
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}
