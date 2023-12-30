package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//    20291 : 파일정리
//    ref url : https://www.acmicpc.net/problem/20291
public class 파일정리 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> cntMap = new TreeMap(Comparator.naturalOrder());
        int N = Integer.parseInt(in.readLine());
        while(N-- > 0) {
            String str = in.readLine();
            String[] arr = str.split("\\.");
            cntMap.merge(arr[1], 1, Integer::sum);
        }
        cntMap.forEach((k, v) -> {
            try {
                out.write(String.format("%s %s\n", k, v));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        out.flush();
    }
}
