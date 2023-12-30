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
import java.util.Optional;
import java.util.stream.Collectors;

//    16165 : 걸그룹 마스터 준석이
//    ref url : https://www.acmicpc.net/problem/16165
public class 걸그룹마스터준석이 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        Map<String, String> a = new HashMap<>();
        Map<String, List<String>> b = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String group = in.readLine();
            int members = Integer.parseInt(in.readLine());
            for (int j = 0; j < members; j++) {
                String name = in.readLine();
                a.put(name, group);
                List<String> list = Optional.ofNullable(b.get(group)).orElseGet(ArrayList::new);
                list.add(name);
                b.put(group, list);
            }
        }

        for (int i = 0; i < M; i++) {
            String input = in.readLine();
            int c = Integer.parseInt(in.readLine());
            if (c == 1) {
                out.write(a.get(input));
            } else {
                out.write(b.get(input)
                        .stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.joining("\n")));
            }
            out.newLine();
        }
        out.flush();
    }
}
