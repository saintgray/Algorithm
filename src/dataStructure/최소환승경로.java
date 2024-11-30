package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//    2021 : 최소 환승 경로
//    ref url : https://www.acmicpc.net/problem/2021
public class 최소환승경로 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, L;

    public static void main(String[] args) throws IOException {
        String[] param = in.readLine().split(" ");
        N = Integer.parseInt(param[0]);
        L = Integer.parseInt(param[1]);
        S[] stations = new S[N + 1];
        최소환승경로.L[] lines = new L[L];
        for (int i = 1; i <= N; i++) {
            stations[i] = new S(i);
        }
        for (int i = 0; i < L; i++) {
            lines[i] = new L();
        }
        for (int i = 0; i < L; i++) {
            int[] line_info = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (line_info.length == 1) continue;
            for (int j = 0; j < line_info.length - 1; j++) {
                lines[i].stations.add(stations[line_info[j]]);
                stations[line_info[j]].connected.add(lines[i]);
            }
        }

        String[] startEnd = in.readLine().split(" ");
        int a = Integer.parseInt(startEnd[0]);
        int b = Integer.parseInt(startEnd[1]);
        if (a < 1 || a > N || b < 1 || b > N) throw new IOException("hear!!");
        S start = stations[Integer.parseInt(startEnd[0])];
        S end = stations[Integer.parseInt(startEnd[1])];

        PriorityQueue<Transfer> q = new PriorityQueue<>();
        for (최소환승경로.L line : start.connected) {
            q.add(new Transfer(line, 1));
        }
        while (!q.isEmpty()) {
            Transfer t = q.poll();
            if (t.line.checked) continue;
            t.line.checked = true;
            for (S s : t.line.stations) {
                if (s.checekd) continue;
                s.checekd = true;
                if (s.transfer > t.transfer) {
                    s.transfer = t.transfer;
                    for (최소환승경로.L line : s.connected) {
                        if (line.checked) continue;
                        q.add(new Transfer(line, t.transfer + 1));
                    }
                }
            }
        }
        System.out.println(end.transfer == Integer.MAX_VALUE ? "-1" : end.transfer - 1);
    }

    static class Transfer implements Comparable<Transfer> {
        최소환승경로.L line;
        int transfer;

        public Transfer(최소환승경로.L line, int transfer) {
            this.line = line;
            this.transfer = transfer;
        }

        @Override
        public int compareTo(Transfer t) {
            return Integer.compare(this.transfer, t.transfer);
        }
    }


    static class L {
        Set<S> stations;
        boolean checked;

        public L() {
            this.stations = new HashSet<>();
        }
    }

    static class S {
        int no;
        int transfer;
        boolean checekd;
        Set<최소환승경로.L> connected;

        public S(int no) {
            this.no = no;
            this.connected = new HashSet<>();
            this.transfer = Integer.MAX_VALUE;
        }
    }
}
