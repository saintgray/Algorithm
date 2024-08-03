package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    12851 : 숨바꼭질2
//    ref url : https://www.acmicpc.net/problem/12851
public class 숨바꼭질2 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int min = Integer.MAX_VALUE;
    static int minCombination = 0;
    static final int MAX_LENGTH = 100001;
    static Integer[] time;
    static int s1;
    static int s2;

    public static void main(String[] args) throws IOException {
        time = new Integer[MAX_LENGTH];
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(java.lang.Integer::parseInt).toArray();
        s1 = p[0];
        s2 = p[1];
        if (s1 == s2) {
            System.out.printf("%d\n%d%n", 0, 1);
            return;
        }
        time[s1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s1);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            addQueue(queue, x, 2 * x);
            addQueue(queue, x, x - 1);
            addQueue(queue, x, x + 1);
        }
        System.out.println(min);
        System.out.println(minCombination);
    }

    static void addQueue(Queue<Integer> queue, int from, int to) {
        if (to >= 0 && to < time.length) {
            Integer fastest = time[to];
            if (fastest == null || time[from] + 1 <= fastest) {
                time[to] = time[from] + 1;
                queue.add(to);
                if (to == s2) {
                    minCombination = min == time[to] ? minCombination + 1 : 1;
                    min = Math.min(min, time[to]);
                }
            }
        }
    }
}