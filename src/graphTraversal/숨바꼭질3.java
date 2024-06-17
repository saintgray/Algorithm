package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//    13549 : 숨바꼭질3
//    ref url : https://www.acmicpc.net/problem/13549
//    ref theory : 0-1 너비우선탐색
public class 숨바꼭질3 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;
    static Deque<Integer> dq = new LinkedList<>();
    static int[] distance;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = p[0];
        K = p[1];
        distance = new int[100000];
        Arrays.fill(distance, Integer.MAX_VALUE);
        dq.add(N);
        distance[N] = 0;
        while (!dq.isEmpty()) {
            int n = dq.poll();
            if (n >= distance.length) continue;
            if (distance[2 * n] > distance[n]) { distance[2 * n] = distance[n]; dq.addFirst(2 * n); }
            if (distance[n - 1] > distance[n] + 1) { distance[n - 1] = distance[n] + 1; dq.addLast(n - 1); }
            if (distance[n + 1] > distance[n] + 1) { distance[n + 1] = distance[n] + 1; dq.addLast(n + 1); }
        }
        System.out.println(distance[K]);
    }
}
