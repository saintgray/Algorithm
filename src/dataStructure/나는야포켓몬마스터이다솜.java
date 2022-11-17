package dataStructure;

import java.io.*;
import java.util.*;

//	1620 : 나는야 포켓몬 마스터 이다솜
//	ref url : https://www.acmicpc.net/problem/1620

public class 나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = params[0];
        int M = params[1];
        Map<String, Integer> monsterMap = new HashMap<>();
        List<String> monsterList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            String input = in.readLine();
            monsterMap.put(input, i);
            monsterList.add(input);
        }

        for (int i = 1; i <= M; i++) {
            String input = in.readLine();
            out.write(input.matches("\\d+") ? monsterList.get(Integer.parseInt(input) - 1) : String.valueOf(monsterMap.get(input)));
            out.newLine();
        }
        out.flush();
        in.close();
        out.close();
    }
}
