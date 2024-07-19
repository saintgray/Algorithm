package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//    1263 : 시간관리
//    ref url : https://www.acmicpc.net/problem/1263
public class 시간관리 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        List<Work> works = new ArrayList<>();
        int maxDeadLine = 0;

        for (int i = 0; i < N; i++) {
            Work work = new Work(in.readLine().split(" "));
            works.add(work);
            maxDeadLine = Math.max(maxDeadLine, work.deadline);
        }

        works.sort(Comparator.comparing(obj -> ((Work) obj).deadline, Comparator.reverseOrder())
                .thenComparing(obj -> ((Work) obj).deadline - ((Work) obj).time, Comparator.reverseOrder()));

        int curTime = maxDeadLine;
        for (Work work : works) {
            int endTime = Math.min(curTime, work.deadline);
            curTime = endTime - work.time;
            if (curTime < 0) {
                System.out.println("-1");
                return;
            }
        }
        System.out.println(curTime);
    }

    static class Work {
        int time;
        int deadline;

        public Work(String[] info) {
            this.time = Integer.parseInt(info[0]);
            this.deadline = Integer.parseInt(info[1]);
        }
    }
}