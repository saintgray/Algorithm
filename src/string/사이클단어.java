package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//    1544 : 사이클 단어
//    ref url : https://www.acmicpc.net/problem/1544
public class 사이클단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        Integer N = Integer.parseInt(in.readLine());
        Map<Integer,Set<String>> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String input = in.readLine();
            int len = input.length();
            Set<String> set = Optional.ofNullable(map.get(len)).orElseGet(HashSet::new);
            if (set.isEmpty() || set.contains(input)) {
                set.add(input);
            } else {
                boolean contains = false;
                String _tempInput = input;
                for (int j = 0; j < _tempInput.length() - 1; j++) {
                    _tempInput = _tempInput.substring(1).concat(String.valueOf(_tempInput.charAt(0)));
                    if (set.contains(_tempInput)) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    set.add(input);
                }
            }
            map.put(len, set);
        }
        int result = 0;
        for(Integer key : map.keySet()) {
            result += map.get(key).size();
        }
        System.out.println(result);
    }
}
