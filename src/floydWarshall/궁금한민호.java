package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//    1507 : 궁금한 민호
//    ref url : https://www.acmicpc.net/problem/1507
public class 궁금한민호 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int INF;
    static int N;
    static int[][] time;
    static boolean[][] checked;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        INF = 2500 * (N - 1) + 1;
        V[] vtx = new V[N + 1];
        for (int i = 1; i <= N; i++) {
            vtx[i] = new V(i);
        }

        time = new int[N][N];
        int totalTime = 0;
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                time[i][j] = row[j];
            }
        }

        // 간선 소요 시간이 짧은 순으로 최단 거리 재구성
        PriorityQueue<E> roads = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                roads.add(new E(vtx[i + 1], vtx[j + 1], time[i][j]));
            }
        }

        int[][] resultTime = new int[N][N];
        boolean[][] checked = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) resultTime[i][j] = INF;
                else checked[i][j] = true;
            }
        }

        int min = 0;
        while (!roads.isEmpty()) {
            E road = roads.poll();
            V u = road.u;
            V v = road.v;
            if (u == v || checked[u.no - 1][v.no - 1]) continue;
            min += road.time;
            resultTime[u.no - 1][v.no - 1] = road.time;
            resultTime[v.no - 1][u.no - 1] = road.time;
            checked[u.no - 1][v.no - 1] = true;
            checked[v.no - 1][u.no - 1] = true;

            floydWarshall(resultTime, time, checked);
            int check = checkResult(time, resultTime);
            if (check == 1) {
                continue;
            }
            if (check < 0) {
                System.out.println(check);
                return;
            } else {
                System.out.println(min);
                return;
            }
        }
    }

    static int checkResult(int[][] origin, int[][] result) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (origin[i][j] == result[i][j]) continue;
                if (result[i][j] > origin[i][j]) {
                    return 1;   // 계속 진행
                }
                if (result[i][j] < origin[i][j]) {
                    return -1;  // 최단시간 구성 불가능
                }
            }
        }
        return 0;  // 더이상 다리를 놓지 않고 종료
    }

    static void floydWarshall(int[][] arr, int[][] origin, boolean[][] checked) {
        for (int m = 0; m < N; m++) {
            for (int st = 0; st < N; st++) {
                for (int ed = 0; ed < N; ed++) {
                    if (arr[st][m] + arr[m][ed] < arr[st][ed]) {
                        arr[st][ed] = arr[st][m] + arr[m][ed];
                        if (arr[st][ed] == origin[st][ed]) {
                            checked[st][ed] = true;
                            checked[st][m] = true;
                            checked[m][ed] = true;
                        }
                    }
                }
            }
        }
    }

    static class V {
        int no;

        public V(int no) {
            this.no = no;
        }
    }

    static class E implements Comparable<E> {
        V u;
        V v;
        int time;

        public E(V u, V v, int time) {
            this.u = u;
            this.v = v;
            this.time = time;
        }

        @Override
        public int compareTo(E e) {
            return Integer.compare(this.time, e.time);
        }
    }
}