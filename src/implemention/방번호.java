package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//    1475 : 방 번호
//    ref url : https://www.acmicpc.net/problem/1475
public class 방번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        for (int i : arr) {
            map.merge(i, 1, Integer::sum);
        }
        int sixSet = Optional.ofNullable(map.get(6)).orElse(0);
        int nineSet = Optional.ofNullable(map.get(9)).orElse(0);

        int set = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            Integer num = integerIntegerEntry.getKey();
            Integer cnt = integerIntegerEntry.getValue();
            if(num == 6 || num == 9)
                continue;
            set = Math.max(cnt, set);

        }
        int maxSetOfSixAndNine = Math.max(sixSet, nineSet);
        int minSetOfSixANdNine = Math.min(sixSet, nineSet);
        sixSet = sixSet - minSetOfSixANdNine;
        nineSet = nineSet -minSetOfSixANdNine;
        // 남아 있는 개수가 짝수개라면 남은 수 / 2 개 세트로 만들 수 있고
        // 홀수라면 (남은 수 / 2) + 1개 세트로 만들 수 있음
        int rest = Math.max(sixSet , nineSet);
        int sixNineSetResult = minSetOfSixANdNine +
                ((rest % 2 ==0) ? rest / 2 :( rest / 2) + 1);
        System.out.println(Math.max(sixNineSetResult, set));

    }
}
