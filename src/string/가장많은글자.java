package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//    1371 : 가장 많은 글자
//    ref url : https://www.acmicpc.net/problem/1371
public class 가장많은글자 {
    public static void main(String[] args) throws IOException {
        int[] count = new int[26];
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;
        while ((input = in.readLine()) != null) {
            String line = input.replace(" ", "");
            if (line.length() == 0)
                continue;
            char[] chars = line.toCharArray();
            for (char c : chars)
                count[((int) c) - 97]++;
        }
        int maxCount = Arrays.stream(count).max().getAsInt();
        List<Character> maxList = new ArrayList<>();
        for(int i =0; i<count.length; i++){
            if(count[i] == maxCount)
                maxList.add((char) (i + 97));
        }
        maxList.sort(Comparator.naturalOrder());
        for(Character c: maxList)
            out.write(c);
        out.flush();
    }
}

// PS.
// 문제 설명에는 각 줄에는 알파벳은 적어도 하나 이상 있다 라고 되어있으나
// TC Data 중 개행 Line 이 들어가 있는 Case 가 있음
