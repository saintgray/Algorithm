package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//    13904 : 과제
//    ref url : https://www.acmicpc.net/problem/13904
//    tag : priority queue
public class 과제 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        PriorityQueue<Problem> queue = new PriorityQueue<>();
        int maxDeadline = 0;
        for (int i = 0; i < N; i++) {
            Problem p = new Problem(in.readLine().split(" "));
            maxDeadline = Math.max(maxDeadline, p.d);
            queue.add(p);
        }
        boolean[] checked = new boolean[maxDeadline + 1];
        int score = 0;
        while(!queue.isEmpty()) {
            Problem p = queue.poll();
            for (int i = p.d; i >= 1; i--) {
                if(checked[i]) continue;
//                System.out.printf("deadline : %d, reward : %d, solved day : %d\n", p.d, p.w, i);
                checked[i] = true;
                score += p.w;
                break;
            }
        }
        System.out.println(score);

    }

    static class Problem implements Comparable<Problem> {
        int d;
        int w;

        public Problem(String[] param) {
            this.d = Integer.parseInt(param[0]);
            this.w = Integer.parseInt(param[1]);
        }

        @Override
        public int compareTo(Problem p) {
            int compare = -1 * Integer.compare(this.w, p.w);
            if(compare == 0) return Integer.compare(this.d, p.d);
            return compare;
        }
    }
}