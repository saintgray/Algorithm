package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//    5052 : 전화번호 목록
//    ref url : https://www.acmicpc.net/problem/5052
public class 전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(in.readLine());
        for (int i = 0; i < tc; i++) {
            int cnt = Integer.parseInt(in.readLine());
            Map<String, String> map = new HashMap<>();
            Set<Integer> lenghtSet = new HashSet<>();
            for (int j = 0; j < cnt; j++) {
                String input = in.readLine();
                map.put(input, input);
                lenghtSet.add(input.length());
            }

            boolean serialized = true;
            for (Integer length : lenghtSet) {
                if (map.entrySet().stream().anyMatch((entry) -> {
                    String num = entry.getValue();
                    return length < num.length() && map.get(num.substring(0, length)) != null;
                })) serialized = false;
            }
            out.write(serialized ? "YES\n" : "NO\n");
        }
        out.flush();
    }
}
