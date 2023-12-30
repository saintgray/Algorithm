package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//    5567 : 결혼식
//    ref url : https://www.acmicpc.net/problem/5567
public class 결혼식 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> set1 = map.get(params[0]);
            Set<Integer> set2 = map.get(params[1]);
            if (set1 == null)
                set1 = new HashSet<>();
            if (set2 == null)
                set2 = new HashSet<>();
            set1.add(params[1]);
            set2.add(params[0]);
            map.put(params[0], set1);
            map.put(params[1], set2);
        }
        Set<Integer> resultSet = new HashSet<>();
        addFriends(0, 1, resultSet, map);
        System.out.println(resultSet.size() - 1);   // 자기 자신 제외
    }

    private static void addFriends(int depth, int num, Set<Integer> resultSet, Map<Integer, Set<Integer>> map) {
        if (depth > 2) {
            return;
        }
        resultSet.add(num);
        Set<Integer> connected = map.get(num);
        if (connected != null) {
            for (Integer connectedNum : connected) {
                addFriends(depth + 1, connectedNum, resultSet, map);
            }
        }
    }
}
