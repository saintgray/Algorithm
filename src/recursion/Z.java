package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1074 : Z
//    ref url : https://www.acmicpc.net/problem/1074
public class Z {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int seq;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = p[0];
        int cells = (int) Math.pow(2, N);
        r = p[1];
        c = p[2];
        track(0, 0, cells - 1, cells - 1, N);
        // 0,0 은 방문 순서에 포함되지 않으므로 최종 결과값에 -1
        System.out.println(seq - 1);
    }

    static void track(int r1, int c1, int r2, int c2, int n) {
        // 단위 2차원 배열(=2*2) 일 때 탈출
        if (n == 1) {
            if (r == r1 && c == c1) {
                seq += 1;
                return;
            }
            if (r == r1 && c == c2) {
                seq += 2;
                return;
            }
            if (r == r2 && c == c1) {
                seq += 3;
                return;
            }
            seq += 4;
            return;
        }
        int r_up = (r1 + r2) / 2;
        int r_down = ((r1 + r2) / 2) + 1;
        int c_left = (c1 + c2) / 2;
        int c_right = ((c1 + c2) / 2) + 1;
        int area = 0;
        if (isBoundary(r_down, c_right, r2, c2)) {
            area = 4;
        }
        if (isBoundary(r_down, c1, r2, c_left)) {
            area = 3;
        }
        if (isBoundary(r1, c_right, r_up, c2)) {
            area = 2;
        }
        if (isBoundary(r1, c1, r_up, c_left)) {
            area = 1;
        }

        // 최종 구역 이전까지 cell 의 합을 모두 더한다
        if (area > 1) seq += (area - 1) * (int) (Math.pow(2, n - 1) * Math.pow(2, n - 1));

        // 최종 구역만 다시 tracking
        if (area == 1) {
            track(r1, c1, r_up, c_left, n - 1);
            return;
        }
        if (area == 2) {
            track(r1, c_right, r_up, c2, n - 1);
            return;
        }
        if (area == 3) {
            track(r_down, c1, r2, c_left, n - 1);
            return;
        }
        if (area == 4) {
            track(r_down, c_right, r2, c2, n - 1);
        }
    }

    static boolean isBoundary(int r1, int c1, int r2, int c2) {
        return (r1 <= r && r <= r2 && c1 <= c && c <= c2);
    }

}


