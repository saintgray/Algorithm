package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//    1038 : 감소하는 수
//    ref url : https://www.acmicpc.net/problem/1038
public class 감소하는수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        // 1 ~ 9
        // 10
        // 20 21
        // 30 31 32
        // 40 41 42 43
        // ...
        // 90 91 ... 99
        // 210
        // 320 321
        // 410 420 421 430 431 432
        // ...
        // 9876543210 : 가장 큰 감소하는 수 (10자리수)
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> anchor = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            anchor.add(i);
        }
        list.add(anchor);
        for (int i = 1; i <= 10 ; i++) {
            List<Integer> nextAnchor = new ArrayList<>();
            for (int k = 1; k <= 9; k++) {  // 10자리수는 하나밖에 없는데 int 범위를 벗어나므로 제외
                int finalK = k;
                try {
                    nextAnchor.addAll(list.get(i-1)
                            .stream()
                            .filter(num -> {
                                String charAtZero = String.valueOf(num).substring(0, 1);
                                return Integer.parseInt(charAtZero) == 0 || Integer.parseInt(charAtZero) < finalK;
                            })
                            .map(num -> Integer.parseInt(String.valueOf(finalK).concat(String.valueOf(num))))
                            .collect(Collectors.toList())
                    );
                } catch (NumberFormatException e) {
                    break;
                }
            }
            list.add(nextAnchor);
        }
        int seq = Integer.parseInt(in.readLine());
        if (seq >= 1022) {
            System.out.println(seq == 1022 ? "9876543210" : "-1");
        } else {
            int count = 0;
            boolean exitFlag = false;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    if (count == seq) {
                        System.out.println(list.get(i).get(j));
                        exitFlag = true;
                    }
                    count++;
                    if (exitFlag)
                        break;
                }
                if (exitFlag)
                    break;
            }
        }
    }
}
