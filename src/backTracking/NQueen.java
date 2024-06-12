package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    9663 : N-Queen
//    ref url : https://www.acmicpc.net/problem/9663
public class NQueen {
    static int N;
    static int[] colOccupy;
    static int[] diagnoalOccupacy1;
    static int[] diagnoalOccupacy2;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        colOccupy = new int[N];
        diagnoalOccupacy1 = new int[2 * N];
        diagnoalOccupacy2 = new int[2 * N];
        track(0, 0);
        System.out.println(result);
    }

    static void track(int queenCount, int r) {
        if (queenCount == N) {
            result++;
            return;
        }
        for (int c = 0; c < N; c++) {
            if (colOccupy[c] == 0 && diagnoalOccupacy1[r + c] == 0 && diagnoalOccupacy2[N-c + r] == 0) {
                colOccupy[c]++;
                int cnt11 = c;              // 왼쪽 열 차이
                int cnt12 = N - 1 - c;      // 오른쪽 열 차이
                int cnt21 = r;              // 위 행 차이
                int cnt22 = N - 1 - r;      // 아래 행 차이
                int cnt31 = Math.min(cnt12, cnt21) + Math.min(cnt11, cnt22);    // 정방향 점유 개수
                int cnt32 = Math.min(cnt11, cnt21) + Math.min(cnt12, cnt22);    // 역방향 점유 개수
                diagnoalOccupacy1[r + c] += 1 + cnt31;
                diagnoalOccupacy2[N - c + r] += 1 + cnt32;
                track(queenCount + 1, r + 1);
                colOccupy[c]--;
                diagnoalOccupacy1[r + c] -= 1 + cnt31;
                diagnoalOccupacy2[N - c + r] -= 1 + cnt32;
            }
        }
    }
}