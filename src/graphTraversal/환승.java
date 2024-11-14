package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//    5214 : 환승
//    ref url : https://www.acmicpc.net/problem/5214
public class 환승 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, M;
    static HyperTube[] tubes;

    public static void main(String[] args) throws IOException {
        String[] params = in.readLine().split(" ");
        N = Integer.parseInt(params[0]);
        K = Integer.parseInt(params[1]);
        M = Integer.parseInt(params[2]);

        Station[] stations = new Station[N + 1];
        tubes = new HyperTube[M];
        for (int i = 1; i <= N; i++) {
            stations[i] = new Station(i);
        }
        for (int i = 0; i < M; i++) {
            tubes[i] = new HyperTube();
        }
        for (int i = 0; i < M; i++) {
            int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < K; j++) {
                tubes[i].station_connect.add(stations[arr[j]]);
                stations[arr[j]].tubes.add(tubes[i]);
            }
        }

        PriorityQueue<Status> q = new PriorityQueue<>();
        stations[1].stop_overs = 1;
        q.add(new Status(1, 1));
        while (!q.isEmpty()) {
            Status s = q.poll();
            Station st = stations[s.no];
            if (st.checked) continue;
            st.checked = true;
            for (HyperTube tube : st.tubes) {
                if (tube.checked) continue;
                tube.checked = true;
                for (Station _st : tube.station_connect) {
                    if (_st.stop_overs > s.stop_overs + 1) {
                        _st.tubes.remove(tube);
                        // _st.checked = true;
                        _st.stop_overs = s.stop_overs + 1;
                        q.add(new Status(_st.no, s.stop_overs + 1));
                    }
                }
            }
        }
        int result = stations[N].stop_overs;
        if (result == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(result);
    }

    static class Station implements Comparable<Station> {
        int no;
        int stop_overs;
        boolean checked;
        Set<HyperTube> tubes;

        public Station(int no) {
            this.no = no;
            this.tubes = new HashSet<>();
            this.stop_overs = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Station s) {
            return Integer.compare(this.stop_overs, s.stop_overs);
        }
    }

    static class HyperTube {
        Set<Station> station_connect;
        boolean checked;

        public HyperTube() {
            this.station_connect = new HashSet<>();
        }
    }

    static class Status implements Comparable<Status> {
        int no; // 방문한 역
        int stop_overs; // 역 방문까지 들른 역의 개수

        public Status(int no, int stop_overs) {
            this.no = no;
            this.stop_overs = stop_overs;
        }

        @Override
        public int compareTo(Status s) {
            return Integer.compare(this.stop_overs, s.stop_overs);
        }
    }
}
