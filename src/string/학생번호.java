package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//    1235 : 학생 번호
//    ref url : https://www.acmicpc.net/problem/1235
public class 학생번호 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nums.add(in.readLine());
        }

        int min = Integer.MAX_VALUE;
        int len = nums.get(0).length();

        Set<String> set = new HashSet<>();
        int startIdx = 0;
        while(startIdx < len) {
            for (int i = 0; i < N; i++) {
                String num = nums.get(i).substring(startIdx, len);
                set.add(num);
            }
            if(set.size() == nums.size()) {
                min = len - startIdx;
            }
            startIdx++;
            set.clear();
        }
        System.out.println(min);
    }
}
