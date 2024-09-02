package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//    2109 : 순회강연
//    ref url : https://www.acmicpc.net/problem/2109
public class 순회강연 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int maxDeadline = 0;
        // 강연료가 높은 순, 같은 강연료일 시 강연 기한이 촉박한 순
        PriorityQueue<Deal> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String[] param = in.readLine().split(" ");
            int deadline = Integer.parseInt(param[1]);
            int pay = Integer.parseInt(param[0]);
            maxDeadline = Math.max(deadline, maxDeadline);
            q.add(new Deal(deadline, pay));
        }
        boolean[] reserved = new boolean[maxDeadline + 1];
        int total = 0;
        while (!q.isEmpty()) {
            Deal deal = q.poll();
            for (int i = deal.deadline; i >= 1; i--) {
                if (reserved[i]) continue;
                // 해당일에 예약이 되어 있지 않을 시
                reserved[i] = true;
                total += deal.pay;
                break;
            }
        }
        System.out.println(total);
    }

    static class Deal implements Comparable<Deal> {
        int deadline;
        int pay;

        public Deal(int deadline, int pay) {
            this.deadline = deadline;
            this.pay = pay;
        }

        @Override
        public int compareTo(Deal deal) {
            int compare = -1 * Integer.compare(this.pay, deal.pay);
            if (compare == 0) return Integer.compare(this.deadline, deal.deadline);
            else return compare;
        }

    }
}

