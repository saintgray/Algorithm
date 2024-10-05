package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1102 : 발전소
// ref url : https://www.acmicpc.net/problem/1102
// tag : dynamic programming using bitfield
public class 발전소 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] cost;
    static boolean[] target; // 갱신대상
    static Integer[] dp;    // 발전기 상태 비트를 만들기 위한 최소 비용 memory
    static int all; // 모든 발전기가 가동중인 상태

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        cost = new int[N][N];
        all = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        char[] stat = in.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            stat[i] = stat[i] == 'Y' ? '1' : '0';
        }
        int init = Integer.parseInt(String.valueOf(stat), 2);
        int bitCount = Integer.bitCount(all & init);
        int P = Integer.parseInt(in.readLine());
        // 최소 가동 요구 발전소 대수가 있는데 기 가동중인 발전기가 하나도 없는 경우
        if (P > 0 && bitCount == 0) {
            System.out.println(-1);
            return;
        }
        // 최소 필요한 가동 발전기 대수보다 기 가동중인 발전기 대수가 같거나 큰 경우
        if (bitCount >= P) {
            System.out.println(0);
            return;
        }

        dp = new Integer[1 << N];
        target = new boolean[1 << N];
        dp[init] = 0;
        target[init] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(init);
        track(queue);
        // P 개 이상의 발전기를 초기화 하는데 걸리는 비용의 최소값 제출
        int min = Integer.MAX_VALUE;
        for (int i = init; i < dp.length; i++) {
            if (dp[i] == null) continue;
            if (Integer.bitCount(i) >= P) min = Math.min(min, dp[i]);
        }
        System.out.println(min);
    }

    static void track(Queue<Integer> queue) {
        Queue<Integer> nextQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            int stat = queue.poll();
            String on = Long.toBinaryString(all & stat);
            String off = Long.toBinaryString(all ^ stat);
            // 비트 수 맞추기
            if (on.length() < N) {
                int diff = N - on.length();
                for (int i = 0; i < diff; i++) {
                    on = "0".concat(on);
                }
            }
            if (off.length() < N) {
                int diff = N - off.length();
                for (int i = 0; i < diff; i++) {
                    off = "0".concat(off);
                }
            }
            ////////////////
            Integer prevBitCost = dp[stat];
            for (int i = 0; i < off.length(); i++) {
                if (off.charAt(i) == '0') continue;
                int offGeneratorOn = 1 << (off.length() - i - 1); // 꺼진 발전기 하나만 켰을 때의 비트
                int afterOn = stat ^ offGeneratorOn;   // 꺼진 발전기를 켠 이후 전체 발전기 상태 비트
                for (int j = 0; j < on.length(); j++) {
                    if (on.charAt(j) == '0') continue;
                    // dp 값이 없거나 최소 비용이 갱신되야 할 경우 tracking
                    if (dp[afterOn] == null) {
                        dp[afterOn] = prevBitCost + cost[j][i];
                        if (!target[afterOn]) {
                            target[afterOn] = true;
                            nextQueue.add(afterOn);
                        }
                    } else {
                        if (dp[afterOn] > prevBitCost + cost[j][i]) {
                            dp[afterOn] = prevBitCost + cost[j][i];
                            if (!target[afterOn]) {
                                target[afterOn] = true;
                                nextQueue.add(afterOn);
                            }
                        }
                    }
                }
            }
        }
        // 탐색 대상이 있으면 재탐색
        if (!nextQueue.isEmpty()) track(nextQueue);
    }
}