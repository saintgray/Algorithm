package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//    1781 : 컵라면
//    ref url : https://www.acmicpc.net/problem/1781
//    ref alg : priority queue, disjoint set, greedy
public class 컵라면 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Day[] days = new Day[N + 1];
        for (int i = 1; i <= N; i++) {
            days[i] = new Day();
            days[i].d = i;
            days[i].target = days[i];
        }

        PriorityQueue<Problem> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.add(new Problem(in.readLine()));
        }

        int totalRewards = 0;
        while (!queue.isEmpty()) {
            Problem p = queue.poll();
            // System.out.println(p);
            Day target = findNotReservedDay(p.deadline, days);
            // System.out.println(target);
            if (target == null) continue;
            target.reserved++;
            totalRewards += p.reward;
        }
        System.out.println(totalRewards);
    }

    static Day findNotReservedDay(int deadline, Day[] days) {
        if (days[deadline] == null || days[deadline].target == null) return null;
        if (days[deadline].target.reserved == 0) return days[deadline].target;
        return days[deadline].target = findNotReservedDay(days[deadline].target.d - days[deadline].target.reserved, days);
    }

    static class Day {
        int d;  // 날짜
        int reserved;
        Day target;

        @Override
        public String toString() {
            return String.format("%d day, reserved %d", this.d, this.reserved);
        }
    }

    static class Problem implements Comparable<Problem> {
        int deadline;
        int reward;

        public Problem(String input) {
            String[] params = input.split(" ");
            this.deadline = Integer.parseInt(params[0]);
            this.reward = Integer.parseInt(params[1]);
        }

        @Override
        public int compareTo(Problem p) {
            int compare = -1 * Integer.compare(this.reward, p.reward);
            if (compare == 0) return Integer.compare(this.deadline, p.deadline);
            return compare;
        }

        @Override
        public String toString() {
            return String.format("[Problem] deadline : %d, reward : %d", this.deadline, this.reward);
        }
    }
}
