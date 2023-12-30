package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//    4358 : 생태학
//    ref url : https://www.acmicpc.net/problem/4358
public class 생태학 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> treeCountMap = new TreeMap<>(Comparator.naturalOrder());
        String input = null;
        int total = 0;
        while(!isEOF((input = in.readLine()))) {
            total++;
            treeCountMap.merge(input, 1, Integer::sum);
        }
        for (Map.Entry<String, Integer> entry : treeCountMap.entrySet()) {
            String name = entry.getKey();
            Integer count = entry.getValue();
            // out.write(String.format("%s %d\n", name, Math.round((count / all) * 1000) ));
            out.write(String.format("%s %.4f\n", name, Math.round((((double) count / total) * 100 * 10000)) / (double) 10000));
        }
        out.flush();
    }

    public static boolean isEOF(String input) {
        return input == null || input.length() == 0;
    }
}
