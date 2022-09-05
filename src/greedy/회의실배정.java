package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//    1931 : 회의실 배정
//    https://www.acmicpc.net/problem/1931

public class 회의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        List<TimeInfo> timeInfos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int[] info = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(e -> Integer.parseInt(e)).toArray();
            timeInfos.add(new TimeInfo(info[0], info[1]));
        }
        timeInfos = timeInfos.stream().sorted(Comparator.comparing(TimeInfo::getStart)
                .thenComparing(TimeInfo::getEnd))
                .collect(Collectors.toList());
        for (int i = 0; i < timeInfos.size(); i++) {
            TimeInfo timeinfo = timeInfos.get(i);
            for (int j = i + 1; j < timeInfos.size(); j++) {
                TimeInfo comparing = timeInfos.get(j);
                if (comparing.getEnd() <= timeinfo.getEnd() &&
                        comparing.getStart() != timeinfo.getEnd()) {
                    timeInfos.remove(timeinfo);
                    i--;
                    break;
                } else {
                    if (comparing.getStart() >= timeinfo.getEnd()) {
                        break;
                    } else {
                        timeInfos.remove(comparing);
                        j--;
                    }
                }
            }
        }
        System.out.println(timeInfos.size());
        in.close();
    }

    static class TimeInfo {
        int start;
        int end;

        public TimeInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
