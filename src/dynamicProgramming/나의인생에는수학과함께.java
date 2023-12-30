package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//    17265 : 나의 인생에는 수학과 함께
//    ref url : https://www.acmicpc.net/problem/17265
// PS. 세로 2칸, 가로 2칸씩만 이동이 가능하다고는 안했다.
public class 나의인생에는수학과함께 {

    static int N = 0;
    static int[] dr = {2, 0, 1, 1};
    static int[] dc = {0, 2, 1, 1};
    // 연산할 Node 사이에 끼워넣을 연산자 Node 를 구하기 위한 이동한 거리(dr, dc의 index 값) 별 가중치
    static int[] calc_dr = {-1, 0, 0, -1};
    static int[] calc_dc = {0, -1, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        Node[][] path = new Node[N][N];
        for (int r = 0; r < N; r++) {
            char[] row = in.readLine().replaceAll(" ", "").toCharArray();
            for (int c = 0; c < N; c++) {
                path[r][c] = new Node(r, c, row[c]);
            }
        }
        // 출발점 까지 가는데의 최대값과 최소값
        // ASCII 보정 (-48)
        path[0][0].max = path[0][0].v - 48;
        path[0][0].min = path[0][0].v - 48;
        Queue<Node> queue = new LinkedList<>();
        queue.add(path[0][0]);
        while (!queue.isEmpty()) {
            Node n1 = queue.poll();
            for (int i = 0; i < 4; i++) {
                int _dr = n1.r + dr[i];
                int _dc = n1.c + dc[i];
                if (isBoundary(_dr, _dc)) {
                    Node n2 = path[_dr][_dc];
                    Node calcNode = path[_dr + calc_dr[i]][_dc + calc_dc[i]];
                    n2.min = Math.min(Math.min(calc(n1.min, calcNode, (n2.v -48)), calc(n1.max, calcNode, (n2.v -48))), n2.min);
                    n2.max = Math.max(Math.max(calc(n1.max, calcNode, (n2.v -48)), calc(n1.min, calcNode, (n2.v -48))), n2.max);
                    queue.add(n2);
                }
            }
        }
        System.out.printf("%d %d", path[N - 1][N - 1].max, path[N - 1][N - 1].min);
    }


    static boolean isBoundary(int _dr, int _dc) {
        return _dr >= 0 && _dr < N && _dc >= 0 && _dc < N;
    }

    static int calc(int num1, Node calcNode, int num2) {
        if (calcNode.v == '+')
            return num1 + num2;
        if (calcNode.v == '-')
            return num1 - num2;
        return num1 * num2;
    }


    static class Node {
        int r;
        int c;
        char v;
        int min;
        int max;
        public Node(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }
    }
}