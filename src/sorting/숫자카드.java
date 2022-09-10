package sorting;

import java.io.*;
import java.util.*;

//    10816 : 숫자카드
//    ref url : https://www.acmicpc.net/problem/10816

public class 숫자카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int deckSize = Integer.parseInt(in.readLine());
        Map<Integer, Integer> cntMap = new HashMap<>();
        int[] cards = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        for (int i = 0; i < deckSize; i++) {
            int n = cards[i];
            Integer cnt = cntMap.get(n);
            cntMap.put(n, cnt == null ? 1 : cnt + 1);
        }
        int sangGunDeckSize = Integer.parseInt(in.readLine());
        int[] sangGunCards = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        for (int i = 0; i < sangGunDeckSize; i++) {
            int n = sangGunCards[i];
            Integer count = cntMap.get(n);
            out.write((count == null ? 0 : count) + " ");
        }
        out.flush();
        in.close();
        out.close();
    }
}
