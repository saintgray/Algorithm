package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1915 : 가장 큰 정사각형
//    ref url : https://www.acmicpc.net/problem/1915
public class 가장큰정사각형 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = p[0];
        M = p[1];
        Node[][] map = new Node[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = new Node(i, j, row[j]);
                map[i][j].colSum = j == 0 ? row[j] : map[i][j-1].colSum + row[j];
                map[i][j].rowSum = i == 0 ? row[j] : map[i-1][j].rowSum + row[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node node = map[i][j];
                if(node.val == 0) continue;
                // 이전 열의 최대 정사각형의 대각선 길이 - 1 이
                // 현재 열의 최대 정사각형 대각선 길이임이 보장됨
                int scanLength = (j == 0 || map[i][j - 1].longestDiagnoal == 0) ? 0 : Math.max(map[i][j - 1].longestDiagnoal - 1, 0);
                int n1 = node.n;
                int m1 = node.m;
                int n2 = n1 + scanLength;
                int m2 = m1 + scanLength;
                while (isBoundary(n2, m2)) {
                    int colSum = map[n2][m2].colSum - (m1 == 0 ? 0 : map[n2][m1-1].colSum);
                    int rowSum = map[n2][m2].rowSum - (n1 == 0 ? 0 : map[n1-1][m2].rowSum);
                    if(colSum != m2 - m1 + 1 || rowSum != n2 - n1 + 1) break;
                    node.longestDiagnoal = scanLength;
                    max = Math.max(max, node.longestDiagnoal);
                    scanLength++;
                    n2 = n1 + scanLength;
                    m2 = m1 + scanLength;
                }
            }
        }
        // 정사각형의 크기는 (max + 1)^2
        System.out.println(max == Integer.MIN_VALUE ? 0 : (int) Math.pow(max + 1, 2));
    }

    static boolean isBoundary(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    static class Node {
        int longestDiagnoal;
        int n;
        int m;
        int val;
        int colSum; // n 행의 0열에서 node 까지 1의 개수 누적합
        int rowSum; // m 열의 0행에서 node 까지 1의 개수 누적합

        public Node(int n, int m, int val) {
            this.n = n;
            this.m = m;
            this.val = val;
            this.longestDiagnoal = 0;
        }
    }
}