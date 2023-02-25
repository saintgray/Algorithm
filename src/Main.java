// 간단한 문제에 대한 제출용 Main Class 입니다

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(in.readLine());

        for (int i = 0; i < tc; i++) {
            List<Integer> list = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            list.sort(Comparator.reverseOrder());
            out.write(list.get(2) + "\n");
        }
        out.flush();
    }
}
