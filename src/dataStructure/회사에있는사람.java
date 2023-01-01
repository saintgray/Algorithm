package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//	7785 : 회사에 있는 사람
//	ref url : https://www.acmicpc.net/problem/7785
public class 회사에있는사람 {

    static final String LEAVE = "leave";
    static final String ENTER = "enter";
    static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        Map<String, String> map = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            String[] arr = in.readLine().split(" ");
            if (arr[1].equals(LEAVE)) {
                if (map.get(arr[0]) != null && ENTER.equals(map.get(arr[0])))
                    map.remove(arr[0]);
            } else {
                map.put(arr[0], arr[1]);
            }

        }
        map.forEach((key, value) -> {
            try {
                out.write(key.concat(NEW_LINE));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        out.flush();
    }
}
