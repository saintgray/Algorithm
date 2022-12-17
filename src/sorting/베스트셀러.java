package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

//    1013 : 베스트셀러
//    ref url : https://www.acmicpc.net/problem/1302
public class 베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> sellCount = new HashMap<>();
        int total = Integer.parseInt(in.readLine());
        for (int i = 0; i < total; i++) {
            String title = in.readLine();
            Integer cnt = sellCount.get(title);
            sellCount.put(title, cnt == null ? 1 : cnt + 1);
        }
        TreeMap<Integer, List<String>> group = sellCount.keySet()
                .stream()
                .collect(Collectors.groupingBy(sellCount::get, () -> new TreeMap<>(Comparator.reverseOrder()), Collectors.toList()));
        Optional<String> bestSeller = group.firstEntry().getValue().stream().min(Comparator.naturalOrder());
        bestSeller.ifPresent(System.out::println);
    }
}
